package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaderXXX(
    @SerialName("athlete")
    val athlete: AthleteXX? = AthleteXX(),
    @SerialName("displayValue")
    val displayValue: String? = "",
    @SerialName("team")
    val team: TeamX? = TeamX(),
    @SerialName("value")
    val value: Double? = 0.0
)