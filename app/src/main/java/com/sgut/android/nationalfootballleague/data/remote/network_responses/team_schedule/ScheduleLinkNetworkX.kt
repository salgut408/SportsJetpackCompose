package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleLinkNetworkX(
    @SerialName("href")
    val href: String = "",
    @SerialName("rel")
    val rel: List<String> = listOf()
)