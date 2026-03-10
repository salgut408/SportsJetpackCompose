package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.EventScoreboard
import kotlinx.serialization.Serializable

@Serializable
data class Seasonseries(
    @SerialName("type")
    val type: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("summary")
    val summary: String? = null,
    @SerialName("completed")
    val completed: Boolean? = null,
    @SerialName("totalCompetitions")
    val totalCompetitions: Int? = null,
    @SerialName("events")
    val events: List<EventScoreboard> = listOf(),
)
