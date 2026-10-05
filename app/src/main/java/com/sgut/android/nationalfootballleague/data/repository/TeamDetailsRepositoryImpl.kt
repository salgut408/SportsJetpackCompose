package com.sgut.android.nationalfootballleague.data.repository

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
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Team-details repository. Always fetches fresh — no caching.
 *
 * The previous impl had a same-shape bug in all three endpoints: the success
 * path made an API call inside the `try`, then the function fell through and
 * made a SECOND call as a "fallback" at the bottom — so a successful fetch
 * cost two network round trips. This rewrite collapses each endpoint to a
 * single call and throws on non-2xx so the ViewModel can surface a real error.
 */
class TeamDetailsRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @Suppress("unused") private val sportsDataBase: SportsDataBase,
    private val ioDispatcher: CoroutineDispatcher,
) : TeamDetailsRepository {

    override suspend fun getSpecificTeam(
        sport: String,
        league: String,
        team: String,
    ): FullTeamDetailWithRosterModel = withContext(ioDispatcher) {
        val response = sportsApi.getSpecificTeam(sport, league, team)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "getSpecificTeam failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        body.asDomainModel().fullTeam
    }

    /**
     * Game-details flow uses this to map a team's athletes into the shape its
     * UI expects. Failures return an empty list — this is a non-critical
     * augmentation, not a primary load.
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
        val response = sportsApi.getTeamSchedule(sport, league, teamId)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "getTeamSchedule failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        body.asDomain()
    }

    override suspend fun getTeamStats(
        sport: String,
        league: String,
        team: String,
    ): TeamStatsModel = withContext(ioDispatcher) {
        val response = sportsApi.getStats(sport, league, team)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "getTeamStats failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        body.asDomain()
    }
}