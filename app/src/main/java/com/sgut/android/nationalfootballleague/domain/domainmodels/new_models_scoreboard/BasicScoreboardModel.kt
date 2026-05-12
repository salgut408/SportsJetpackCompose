package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard





data class BasicScoreboardModel(
    val league: DefaultLeagueModel = DefaultLeagueModel(),
    val day: String? = "",
    val events: List<DefaultScoreboardEventModel> = listOf(),
    )






