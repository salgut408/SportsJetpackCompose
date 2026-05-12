package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleScoreNetwork(
    @SerialName("displayValue")
    val displayValue: String = "",
    @SerialName("value")
    val value: Double = 0.0
)