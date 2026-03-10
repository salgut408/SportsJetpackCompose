package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    @SerialName("links")
    val links: List<LinkX> = listOf(),
    @SerialName("numberAvailable")
    val numberAvailable: Int = 0,
    @SerialName("summary")
    val summary: String = ""
)