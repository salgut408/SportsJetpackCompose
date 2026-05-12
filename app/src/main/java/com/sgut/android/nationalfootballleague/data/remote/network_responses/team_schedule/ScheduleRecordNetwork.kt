package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleRecordNetwork(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("displayValue")
    val displayValue: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("type")
    val type: String = ""
)