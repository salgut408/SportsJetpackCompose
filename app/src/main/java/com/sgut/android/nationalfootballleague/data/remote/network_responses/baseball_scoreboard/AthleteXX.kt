package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AthleteXX(
    @SerialName("active")
    val active: Boolean? = false,
    @SerialName("displayName")
    val displayName: String? = "",
    @SerialName("fullName")
    val fullName: String? = "",
    @SerialName("headshot")
    val headshot: String? = "",
    @SerialName("id")
    val id: String? = "",
    @SerialName("jersey")
    val jersey: String? = "",
    @SerialName("position")
    val position: Position? = Position(),
    @SerialName("shortName")
    val shortName: String? = "",
    @SerialName("team")
    val team: TeamX? = TeamX()
)