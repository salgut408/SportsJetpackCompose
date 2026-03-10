package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AthleteX(
    @SerialName("displayJersey")
    val displayJersey: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("guid")
    val guid: String = "",
    @SerialName("headshot")
    val headshot: Headshot = Headshot(),
    @SerialName("id")
    val id: String = "",
    @SerialName("jersey")
    val jersey: String = "",
    @SerialName("position")
    val position: PositionX = PositionX(),
    @SerialName("uid")
    val uid: String = ""
)