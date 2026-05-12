package com.sgut.android.nationalfootballleague.domain.repositories

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard.BaseballScoreBoardNetwork
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TennisScoreboardModel

interface ScoreboardRepository {

    suspend fun getGeneralScoreboard(
        sport: String,
        league: String,
    ): BasicScoreboardModel

    suspend fun getGeneralScoreboardByDate(
        sport: String,
        league: String,
        date: String
    ): BasicScoreboardModel

    suspend fun getCollegeBasketballScoreboard(
        sport: String,
        league: String,
        limit: String
    ): BasicScoreboardModel

    suspend fun getBaseballScoreboard(
        sport: String,
        league: String,
//        TODO MAKE MODEL
    ): BaseballScoreBoardNetwork

    suspend fun getTennisScoreBoard(
        sport: String,
        league: String
    ): TennisScoreboardModel

    suspend fun getAbstractScoreBoard(
        sport: String,
        league: String
    ): ScoreboardData


}