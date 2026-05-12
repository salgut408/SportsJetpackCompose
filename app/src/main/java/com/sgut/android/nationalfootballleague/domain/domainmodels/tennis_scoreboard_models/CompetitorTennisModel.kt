package com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models


data class CompetitorTennisModel(
    val athlete: AthleteTennisModel = AthleteTennisModel(),
    val curatedRank: CuratedRankTennisModel = CuratedRankTennisModel(),
    val homeAway: String = "",
     val id: String = "",
    val linescores: List<LinescoreTennisModel> = listOf(),
    val order: Int = 0,
    val possession: Boolean = false,
    val roster: RosterTennisModel = RosterTennisModel(),
    val statistics: List<Any> = listOf(),
    val type: String = "",
    val uid: String = "",
    val winner: Boolean = false
)