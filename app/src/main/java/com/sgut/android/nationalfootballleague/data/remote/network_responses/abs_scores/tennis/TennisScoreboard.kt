package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TennisScoreboard(
    @SerialName("events")
    val events: List<Event> = listOf(),
    @SerialName("leagues")
    val leagues: List<League> = listOf(),
)