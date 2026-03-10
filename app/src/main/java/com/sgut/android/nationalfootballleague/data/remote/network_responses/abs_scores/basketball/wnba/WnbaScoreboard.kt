package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.wnba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WnbaScoreboard(
    @SerialName("events")
    val events: List<Event> = listOf(),
    @SerialName("leagues")
    val leagues: List<League> = listOf(),
)