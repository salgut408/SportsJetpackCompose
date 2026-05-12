package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.college


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
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("uid")
    val uid: String = "",
)