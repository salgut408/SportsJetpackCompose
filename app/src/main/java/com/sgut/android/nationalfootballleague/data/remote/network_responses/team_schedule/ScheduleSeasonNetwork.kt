package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleSeasonNetwork(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("year")
    val year: Int = 0
)