package com.sgut.android.nationalfootballleague.data.repository

import android.util.Log
import com.sgut.android.nationalfootballleague.asDomainModel
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.*
import com.sgut.android.nationalfootballleague.domain.repositories.TeamsListsRepository
import com.sgut.android.nationalfootballleague.toDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

// interacts and gets sports id, league id

class TeamsListRepositoryImpl @Inject constructor(
    val sportsApi: SportsApi,
    val sportsDataBase: SportsDataBase,
    val ioDispatcher: CoroutineDispatcher
) : TeamsListsRepository {

    override suspend fun getFullSportLeagueAndTeamsList(
        sport: String,
        league: String,
    ): FullTeamsListsModel =
        try {
            withContext(ioDispatcher) {
                if (sport == "tennis") {
                    return@withContext FullTeamsListsModel()
                }
                return@withContext sportsApi.getTeamsListForLeague(sport, league).body()?.toDomain() ?: FullTeamsListsModel()
            }
        } catch (e: Exception) {
            FullTeamsListsModel(SportModel()).apply {
             Timber.e("getFullSportLeagueAndTeamsList ERROR $e")

            }
        }


    override suspend fun getSport(sport: String, league: String): SportModel {
        return sportsApi.getTeamsListForLeague(sport, league).body()?.sports?.get(0)?.toDomain() ?: SportModel()
    }

    override suspend fun getLeague(sport: String, league: String): LeagueModel {
        return sportsApi.getTeamsListForLeague(sport, league).body()?.sports?.get(0)?.leagues?.get(0)?.toDomain() ?: LeagueModel()
    }

    override suspend fun getTeamsList(sport: String, league: String): List<TeamModel> {
        return sportsApi.getTeamsListForLeague(sport, league).body()?.sports?.get(0)?.leagues?.get(0)?.teams?.map { it.teamSingle.asDomainModel() } ?: listOf()
    }

    override suspend fun storeTeamsInSportsDatabaseFullInfoTable(teams: List<TeamModel>, sport: String, league: String, leagueAbrv: String) {
        withContext(ioDispatcher) {
            sportsDataBase.getTeamsDao().addAllTeams(teams.map { it.asDbObj(sport, league, leagueAbrv) })
        }
    }

}


sealed class Result<out T : Any>{
    data class Success<out T : Any>(val data: T): Result<T>()
    data class Error(val message: String): Result<Nothing>()
}