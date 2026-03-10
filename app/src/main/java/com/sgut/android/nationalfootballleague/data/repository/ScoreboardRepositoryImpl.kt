package com.sgut.android.nationalfootballleague.data.repository

import android.util.Log
import com.sgut.android.nationalfootballleague.asDomain
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.DefaultScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard.BaseballScoreBoardNetwork
import com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TennisScoreboardModel
import com.sgut.android.nationalfootballleague.domain.repositories.ScoreboardRepository
import com.sgut.android.nationalfootballleague.utils.printToLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ScoreboardRepositoryImpl @Inject constructor(
    val sportsApi: SportsApi,
    val sportsDataBase: SportsDataBase,
    val ioDispatcher: CoroutineDispatcher,
) : ScoreboardRepository {

    override suspend fun getGeneralScoreboard(
        sport: String,
        league: String,
    ): BasicScoreboardModel =
        try {
            withContext(ioDispatcher) {
                val response = sportsApi.getGeneralScoreboard(sport, league)
                if (response.isSuccessful) {
                    return@withContext response.body()?.asDomain()!!
                } else {
                    Timber.e("getScoreboard-FAIL, ${response.toString()}")
                    BasicScoreboardModel()
                }
            }
        }
        catch (e: Exception) {
            Timber.e("getScoreboard fail , ${e.stackTraceToString()}")
            BasicScoreboardModel()
        }


    override suspend fun getCollegeBasketballScoreboard(
        sport: String,
        league: String,
        limit: String,
    ): BasicScoreboardModel =
        withContext(ioDispatcher) {
            val response = sportsApi.getCollegeBasketballScoreboard(sport, league, limit)
            if (response.isSuccessful) {
                return@withContext response.body()?.asDomain()!!
            } else {
                Timber.e("scrbrdRepmarch-FAIL, ${response.errorBody().toString()}")
                BasicScoreboardModel()
            }
        }


    override suspend fun getBaseballScoreboard(
        sport: String,
        league: String,
    ): BaseballScoreBoardNetwork =
        withContext(ioDispatcher) {
            val response = sportsApi.getBaseballScoreboard(sport, league)
            if (response.isSuccessful) {
                return@withContext response.body() ?: BaseballScoreBoardNetwork()
            }
            BaseballScoreBoardNetwork()
        }

    override suspend fun getTennisScoreBoard(sport: String, league: String): TennisScoreboardModel =
        try {
            withContext(ioDispatcher) {
                val tennis = sportsApi.getTennisScoreboard(sport, league).body()?.asDomain()!!
                tennis.printToLog("TENNIS REPO")
                return@withContext tennis
            }
        }
        catch (e: Exception) {
            Timber.e("REPO-TENNIS, ${e.stackTraceToString()}")
            TennisScoreboardModel()
        }

    override suspend fun getAbstractScoreBoard(sport: String, league: String): ScoreboardData {
        return withContext(ioDispatcher) {
            try {
                val scores = sportsApi.getAbstractScoreboard(sport, league).body()
//                Timber.d("SAL_GUT GET ABSTRACT SCORE REPO SCORES: $scores")
                scores ?: DefaultScoreboardData()
            } catch (e: Exception) {
                Timber.e("ABSTRACT ERROR ${e.stackTraceToString()}")
                DefaultScoreboardData()
            }
        }
    }


    override suspend fun getGeneralScoreboardByDate(
        sport: String,
        league: String,
        date: String,
    ): BasicScoreboardModel =
        withContext(ioDispatcher) {
            val result = sportsApi.getGeneralScoreboardWithDate(sport, league, date)
            if (result.isSuccessful) {
                return@withContext result.body()?.asDomain()!!
            } else {
                Timber.e("getGeneralScoreboardByDate-FAIL")
                BasicScoreboardModel()
            }
        }
}