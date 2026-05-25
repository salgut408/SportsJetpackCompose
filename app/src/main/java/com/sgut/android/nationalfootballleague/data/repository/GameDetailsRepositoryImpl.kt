package com.sgut.android.nationalfootballleague.data.repository

import androidx.annotation.VisibleForTesting
import com.sgut.android.nationalfootballleague.StatusState
import com.sgut.android.nationalfootballleague.asDomain
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel
import com.sgut.android.nationalfootballleague.domain.repositories.GameDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Game details repository with an in-memory TTL cache keyed by sport/league/event.
 *
 * Caching rationale (per CLAUDE.md): ESPN game detail responses are heavy and the
 * appropriate freshness depends on the game's state.
 *   - Live games (IN)       → short TTL so live scores stay fresh.
 *   - Upcoming games (PRE)  → medium TTL: data changes slowly before kickoff.
 *   - Final games (POST)    → long TTL: results don't change after the game ends.
 *
 * The cache lives on the repository instance, so the binding in AppModule must be
 * `@Singleton`; otherwise Hilt creates a new repo per injection and the cache is
 * never reused. The clock is overridable via [nowMs] for tests; the production
 * default reads `System.currentTimeMillis()`.
 */
class GameDetailsRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @Suppress("unused") private val sportsDataBase: SportsDataBase, // reserved for future on-disk cache layer
    private val ioDispatcher: CoroutineDispatcher,
) : GameDetailsRepository {

    @VisibleForTesting
    internal var nowMs: () -> Long = { System.currentTimeMillis() }

    private val cache = mutableMapOf<CacheKey, CachedEntry>()
    private val cacheMutex = Mutex()

    override suspend fun getGameDetails(
        sport: String,
        league: String,
        event: String,
    ): GameDetailsModel = withContext(ioDispatcher) {
        val key = CacheKey(sport, league, event)
        val now = nowMs()

        cacheMutex.withLock {
            val cached = cache[key]
            if (cached != null && now - cached.fetchedAtMs < ttlForStatus(cached.model)) {
                return@withContext cached.model
            }
        }

        // Single network call; previously the same endpoint was hit three times
        // (success check, "re-fetch" on success, and a redundant fallback) which
        // tripled load on ESPN and tripled latency.
        val response = sportsApi.getGameDetails(sport, league, event)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "Game details request failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        val model = body.asDomain()

        cacheMutex.withLock {
            cache[key] = CachedEntry(model = model, fetchedAtMs = now)
        }

        model
    }

    private fun ttlForStatus(model: GameDetailsModel): Long {
        val statusState = model.header
            ?.competitions
            ?.firstOrNull()
            ?.status
            ?.type
            ?.statusState
        return when (statusState) {
            StatusState.IN -> TTL_LIVE_MS
            StatusState.POST -> TTL_FINAL_MS
            StatusState.PRE -> TTL_UPCOMING_MS
            null -> TTL_DEFAULT_MS
        }
    }

    @VisibleForTesting
    internal data class CacheKey(val sport: String, val league: String, val event: String)

    @VisibleForTesting
    internal data class CachedEntry(val model: GameDetailsModel, val fetchedAtMs: Long)

    companion object {
        // Tuned conservatively — adjust based on user feedback / network load.
        @VisibleForTesting internal const val TTL_LIVE_MS = 15_000L              // 15s
        @VisibleForTesting internal const val TTL_UPCOMING_MS = 5 * 60_000L      // 5 min
        @VisibleForTesting internal const val TTL_FINAL_MS = 24L * 60 * 60_000L  // 24h
        @VisibleForTesting internal const val TTL_DEFAULT_MS = 30_000L           // unknown status fallback
    }
}