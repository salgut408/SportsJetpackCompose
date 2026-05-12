package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeaderX(
    @SerialName("athlete")
    val athlete: Athlete? = Athlete(),
    @SerialName("displayValue")
    val displayValue: String? = "",
    @SerialName("team")
    val team: TeamX? = TeamX(),
    @SerialName("value")
    val value: Double? = 0.0
)