package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
//TODO MAKE MODEL
@Serializable
data class BaseballScoreBoardNetwork(
    @SerialName("day")
    val day: Day? = Day(),
    @SerialName("events")
    val events: List<Event>? = listOf(),
    @SerialName("leagues")
    val leagues: List<League>? = listOf(),
    @SerialName("season")
    val season: SeasonXX? = SeasonXX()
)