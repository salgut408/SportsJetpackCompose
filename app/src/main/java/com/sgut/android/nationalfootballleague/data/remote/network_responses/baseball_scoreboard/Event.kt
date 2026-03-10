package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("competitions")
    val competitions: List<Competition>? = listOf(),
    @SerialName("date")
    val date: String? = "",
    @SerialName("id")
    val id: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("season")
    val season: Season? = Season(),
    @SerialName("shortName")
    val shortName: String? = "",
    @SerialName("status")
    val status: StatusX? = StatusX(),
    @SerialName("uid")
    val uid: String? = "",
    @SerialName("weather")
    val weather: Weather? = Weather()
)