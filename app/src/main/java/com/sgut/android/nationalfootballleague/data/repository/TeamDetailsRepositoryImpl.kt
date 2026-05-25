package com.sgut.android.nationalfootballleague.data.repository

import androidx.annotation.VisibleForTesting
import com.sgut.android.nationalfootballleague.asDomainModel
import com.sgut.android.nationalfootballleague.asGameDetailsAthlete
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleDomainModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamStatsModel
import com.sgut.android.nationalfootballleague.domain.repositories.TeamDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Team-details repository with per-endpoint in-memory TTL caches.
 *
 * The previous impl had three same-shape bugs (one per endpoint): the success
 * path made an API call inside the `try`, then the function fell through and
 * made a SECOND call as a "fallback" at the bottom — so a successful fetch
 * cost two network round trips. On error, it logged and *then* still made the
 * fallback call. This rewrite collapses each endpoint to a single call and
 * throws on non-2xx so the ViewModel can surface a real error state.
 *
 * Caching rationale: team data changes slowly (roster moves, schedule edits,
 * stat updates). TTLs are conservative — we'd rather refetch a bit too often
 * than serve stale data after a trade or injury list change. Tune from real
 * usage data.
 *
 * `@Singleton` on the provider in AppModule is load-bearing: without it the
 * cache lives only for the lifetime of one VM. With it, navigating away from
 * a team and back inside the TTL window costs zero network calls.
 */
class TeamDetailsRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @Suppress("unused") private val sportsDataBase: SportsDataBase,
    private val ioDispatcher: CoroutineDispatcher,
) : TeamDetailsRepository {

    @VisibleForTesting
    internal var nowMs: () -> Long = { System.currentTimeMillis() }

    private val teamCache = mutableMapOf<TeamKey, Cached<FullTeamDetailWithRosterModel>>()
    private val scheduleCache = mutableMapOf<TeamKey, Cached<ScheduleDomainModel>>()
    private val statsCache = mutableMapOf<TeamKey, Cached<TeamStatsModel>>()
    private val cacheMutex = Mutex()

    override suspend fun getSpecificTeam(
        sport: String,
        league: String,
        team: String,
    ): FullTeamDetailWithRosterModel = withContext(ioDispatcher) {
        val key = TeamKey(sport, league, team)
        cached(teamCache, key, TTL_TEAM_MS)?.let { return@withContext it }

        val response = sportsApi.getSpecificTeam(sport, league, team)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "getSpecificTeam failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        val model = body.asDomainModel().fullTeam
        put(teamCache, key, model)
        model
    }

    /**
     * Game-details flow uses this to map a team's athletes into the shape its
     * UI expects. Goes directly to the network DTO (rather than reusing
     * [getSpecificTeam]'s domain-model cache) because `asGameDetailsAthlete`
     * is defined on the network-side `Athletes` type. Failures return an
     * empty list — this is a non-critical augmentation, not a primary load.
     */
    override suspend fun getSpecificTeamRosterInGameDetails(
        sport: String,
        league: String,
        team: String,
    ): List<GameDetailsAthleteDetailsModel> = withContext(ioDispatcher) {
        runCatching {
            val response = sportsApi.getSpecificTeam(sport, league, team)
            val athletes = response.body()?.fullTeam?.athletes.orEmpty()
            athletes.map { it.asGameDetailsAthlete() }
        }.getOrDefault(emptyList())
    }

    override suspend fun getTeamSchedule(
        sport: String,
        league: String,
        teamId: String,
    ): ScheduleDomainModel = withContext(ioDispatcher) {
        val key = TeamKey(sport, league, teamId)
        cached(scheduleCache, key, TTL_SCHEDULE_MS)?.let { return@withContext it }

        val response = sportsApi.getTeamSchedule(sport, league, teamId)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "getTeamSchedule failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        val model = body.asDomain()
        put(scheduleCache, key, model)
        model
    }

    override suspend fun getTeamStats(
        sport: String,
        league: String,
        team: String,
    ): TeamStatsModel = withContext(ioDispatcher) {
        val key = TeamKey(sport, league, team)
        cached(statsCache, key, TTL_STATS_MS)?.let { return@withContext it }

        val response = sportsApi.getStats(sport, league, team)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "getTeamStats failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        val model = body.asDomain()
        put(statsCache, key, model)
        model
    }

    // ---- cache plumbing ----

    private suspend fun <V> cached(map: MutableMap<TeamKey, Cached<V>>, key: TeamKey, ttl: Long): V? {
        val now = nowMs()
        return cacheMutex.withLock {
            map[key]?.takeIf { now - it.fetchedAtMs < ttl }?.value
        }
    }

    private suspend fun <V> put(map: MutableMap<TeamKey, Cached<V>>, key: TeamKey, value: V) {
        cacheMutex.withLock { map[key] = Cached(value, nowMs()) }
    }

    @VisibleForTesting
    internal data class TeamKey(val sport: String, val league: String, val team: String)

    @VisibleForTesting
    internal data class Cached<V>(val value: V, val fetchedAtMs: Long)

    companion object {
        // Conservative TTLs — teams/rosters/stats don't change that often.
        @VisibleForTesting internal const val TTL_TEAM_MS = 10L * 60_000     // 10 min
        @VisibleForTesting internal const val TTL_SCHEDULE_MS = 5L * 60_000  // 5 min
        @VisibleForTesting internal const val TTL_STATS_MS = 10L * 60_000    // 10 min
    }
}
