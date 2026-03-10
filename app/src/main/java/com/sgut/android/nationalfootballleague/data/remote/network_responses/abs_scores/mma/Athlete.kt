package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.mma


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Athlete(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("flag")
    val flag: Flag = Flag(),
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("shortName")
    val shortName: String = ""
)