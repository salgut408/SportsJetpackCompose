package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.asDomain
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel
import com.sgut.android.nationalfootballleague.domain.repositories.GameDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Game details repository. Always fetches fresh — no caching, so live scores,
 * plays, and situation are current every time the screen is entered.
 *
 * (We previously had a TTL cache here but it fought against live games: it
 * could serve stale scores on re-entry. A field-level "sticky" cache for
 * pre-game-only data like the matchup predictor is the eventual plan, but
 * it needs on-disk persistence to be worth it — deferred for now.)
 */
class GameDetailsRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @Suppress("unused") private val sportsDataBase: SportsDataBase,
    private val ioDispatcher: CoroutineDispatcher,
) : GameDetailsRepository {

    override suspend fun getGameDetails(
        sport: String,
        league: String,
        event: String,
    ): GameDetailsModel = withContext(ioDispatcher) {
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
        body.asDomain()
    }
}