package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestedSeason(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("qualifiedPostSeason")
    val qualifiedPostSeason: Boolean = false,
    @SerialName("type")
    val type: Int = 0,
    @SerialName("year")
    val year: Int = 0
)