package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DueUp(
    @SerialName("athlete")
    val athlete: AthleteXXXX? = AthleteXXXX(),
    @SerialName("batOrder")
    val batOrder: Int? = 0,
    @SerialName("period")
    val period: Int? = 0,
    @SerialName("playerId")
    val playerId: Int? = 0,
    @SerialName("summary")
    val summary: String? = ""
)