package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard

import com.sgut.android.nationalfootballleague.Linescore
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameLeadersModel4



data class ScoreboardCompetitorsModel(
    val id: String = "",
    val uid: String = "",
    val type: String = "",
    val order: Int = 0,
    val homeAway: String = "",
    val score: String = "",
    val winner: Boolean = false,
    val records: List<ScoreboardRecordModel>,
    val team: ScoreboardTeamModel = ScoreboardTeamModel(),
    val statistics: List<ScoreboardStatisticModel>,
    val leaders: List<GameLeadersModel4> = listOf(),
    val linescores: List<Linescore>? = listOf(),


//    val probables : List<ProbablesModel>
)
data class LinescoreModel(
    val value: Double? = 0.0
)