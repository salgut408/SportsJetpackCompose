package com.sgut.android.nationalfootballleague.data.repository

import android.util.Log
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.remote.network_responses.standings.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StandingsModel
import com.sgut.android.nationalfootballleague.domain.repositories.StandingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class StandingsRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    private val sportsDataBase: SportsDataBase,
    private val ioDispatcher: CoroutineDispatcher
) : StandingsRepository {

    override suspend fun getStandings(
        sport: String,
        league: String,
        type: String
    ): StandingsModel = withContext(ioDispatcher) {
        try {
            val response = sportsApi.getStandings(sport, league, type)
            val result = response.body()
            Timber.d("STANDINGS : $result")
            result?.asDomain() ?: StandingsModel("BLANK")

        } catch (e: Exception) {
            Timber.e(e, "Failed to fetch standings for sport: $sport, league: $league, type: $type")
            StandingsModel("ERROR")
        }
    }
}