package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleGroupsNetwork(
    @SerialName("id")
    val id: String = ""
)