package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTypeNetworkX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("text")
    val text: String = "",
    @SerialName("type")
    val type: String = ""
)