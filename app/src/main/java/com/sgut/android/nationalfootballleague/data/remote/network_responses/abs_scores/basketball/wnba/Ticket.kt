package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.wnba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    @SerialName("links")
    val links: List<LinkXX> = listOf(),
    @SerialName("numberAvailable")
    val numberAvailable: Int = 0,
    @SerialName("summary")
    val summary: String = ""
)