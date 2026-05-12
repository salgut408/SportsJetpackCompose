package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTicketNetwork(
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: String = "",
//    @SerialName("links")
//    val links: List<ScheduleLinkNetworkX> = listOf(),
    @SerialName("maxPrice")
    val maxPrice: Double = 0.0,
    @SerialName("numberAvailable")
    val numberAvailable: Int = 0,
    @SerialName("startingPrice")
    val startingPrice: Double = 0.0,
    @SerialName("summary")
    val summary: String = "",
    @SerialName("totalPostings")
    val totalPostings: Int = 0
)