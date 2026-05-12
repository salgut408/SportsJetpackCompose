package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.StatusDomainModel



data class DefaultScoreboardEventModel(
    val id: String = "",
    val uid: String = "",
    val date: String = "",
     val name: String = "",
    val shortName: String = "",
     val competitions: List<ScoreboardCompetitionModel> = listOf(),// need model to b ScoreboardCompetitionModel
    val status: StatusDomainModel = StatusDomainModel(),
    )
