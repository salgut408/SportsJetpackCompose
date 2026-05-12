package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.LogosModel



data class DefaultLeagueModel(
    val abbreviation: String ="",
    val id: String = "",
    val logos: List<LogosModel> = listOf(),
    val name: String ="",
    val slug: String ="",
    val uid: String ="",
)