package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AthleteX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("headshot")
    val headshot: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = ""
)