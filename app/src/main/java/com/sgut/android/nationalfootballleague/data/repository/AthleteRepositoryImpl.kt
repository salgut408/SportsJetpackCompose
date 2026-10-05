package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.FullAthleteModel
import com.sgut.android.nationalfootballleague.domain.repositories.AthleteRepository
import com.sgut.android.nationalfootballleague.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Fetches a single athlete's full detail from
 * `common/v3/sports/{sport}/{league}/athletes/{athleteId}`.
 *
 * Always fetches fresh (consistent with the other detail repos after we
 * dropped caching). Throws on non-2xx so the ViewModel can surface an error
 * state rather than rendering an empty default model.
 */
class AthleteRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : AthleteRepository {

    override suspend fun getAthlete(
        sport: String,
        league: String,
        athleteId: String,
    ): FullAthleteModel = withContext(ioDispatcher) {
        val response = sportsApi.getAthleteInfo(sport, league, athleteId)
        val body = response.body()
        if (!response.isSuccessful || body == null) {
            throw IllegalStateException(
                "getAthlete failed: HTTP ${response.code()} ${response.message()}"
            )
        }
        body.asDomain()
    }
}
