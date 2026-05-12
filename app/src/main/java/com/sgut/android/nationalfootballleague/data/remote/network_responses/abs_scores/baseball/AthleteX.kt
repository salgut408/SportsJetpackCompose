package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AthleteX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("headshot")
    val headshot: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("jersey")
    val jersey: String = "",
    @SerialName("position")
    val position: String = "",
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("team")
    val team: TeamX = TeamX()
)