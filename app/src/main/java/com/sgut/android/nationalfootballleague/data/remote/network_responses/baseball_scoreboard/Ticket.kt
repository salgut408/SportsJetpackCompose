package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    @SerialName("links")
    val links: List<LinkXXXXXXXX>? = listOf(),
    @SerialName("numberAvailable")
    val numberAvailable: Int? = 0,
    @SerialName("summary")
    val summary: String? = ""
)