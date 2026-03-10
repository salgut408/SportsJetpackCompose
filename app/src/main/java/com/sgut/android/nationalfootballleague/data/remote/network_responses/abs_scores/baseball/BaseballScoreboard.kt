package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.LeagueCommon
import kotlinx.serialization.Serializable

@Serializable
data class BaseballScoreboard(
    @SerialName("events")
    val events: List<Event> = listOf(),
    @SerialName("leagues")
    val leagues: List<LeagueCommon> = listOf(),
)