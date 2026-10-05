package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.remote.network_responses.standings.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StandingsModel
import com.sgut.android.nationalfootballleague.domain.repositories.StandingsRepository
import com.sgut.android.nationalfootballleague.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StandingsRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : StandingsRepository {

    override suspend fun getStandings(
        sport: String,
        league: String,
        type: String,
    ): StandingsModel = withContext(ioDispatcher) {
        val response = sportsApi.getStandings(sport, league, type)
        if (!response.isSuccessful) {
            error("Standings request failed: HTTP ${response.code()} for $sport/$league/$type")
        }
        response.body()?.asDomain() ?: StandingsModel()
    }
}