package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("competitions")
    val competitions: List<Competition> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("season")
    val season: Season = Season(),
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("uid")
    val uid: String = "",
)