package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpreadRecord(
    @SerialName("losses")
    val losses: Int? = 0,
    @SerialName("pushes")
    val pushes: Int? = 0,
    @SerialName("summary")
    val summary: String? = "",
    @SerialName("wins")
    val wins: Int? = 0
)