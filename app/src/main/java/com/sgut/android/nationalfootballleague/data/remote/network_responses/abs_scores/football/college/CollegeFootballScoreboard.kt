package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollegeFootballScoreboard(
    @SerialName("events")
    val events: List<Event> = listOf(),
    @SerialName("leagues")
    val leagues: List<League> = listOf(),

)