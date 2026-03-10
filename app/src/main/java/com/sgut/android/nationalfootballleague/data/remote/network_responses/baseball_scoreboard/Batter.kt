package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Batter(
    @SerialName("athlete")
    val athlete: AthleteXXX? = AthleteXXX(),
    @SerialName("period")
    val period: Int? = 0,
    @SerialName("playerId")
    val playerId: Int? = 0,
    @SerialName("summary")
    val summary: String? = ""
)