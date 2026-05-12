package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Broadcast(
    @SerialName("market")
    val market: String? = "",
    @SerialName("names")
    val names: List<String?>? = listOf()
)