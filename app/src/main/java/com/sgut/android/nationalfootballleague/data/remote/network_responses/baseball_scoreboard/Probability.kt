package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Probability(
    @SerialName("awayWinPercentage")
    val awayWinPercentage: Double? = 0.0,
    @SerialName("homeWinPercentage")
    val homeWinPercentage: Double? = 0.0,
    @SerialName("tiePercentage")
    val tiePercentage: Double? = 0.0
)