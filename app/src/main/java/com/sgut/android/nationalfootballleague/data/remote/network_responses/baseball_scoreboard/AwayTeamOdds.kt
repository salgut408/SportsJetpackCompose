package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AwayTeamOdds(
    @SerialName("averageScore")
    val averageScore: Double? = 0.0,
    @SerialName("favorite")
    val favorite: Boolean? = false,
    @SerialName("moneyLine")
    val moneyLine: Int? = 0,
    @SerialName("spreadOdds")
    val spreadOdds: Double? = 0.0,
    @SerialName("spreadRecord")
    val spreadRecord: SpreadRecord? = SpreadRecord(),
    @SerialName("team")
    val team: TeamXXXXXX? = TeamXXXXXX(),
    @SerialName("underdog")
    val underdog: Boolean? = false,
    @SerialName("winPercentage")
    val winPercentage: Double? = 0.0
)