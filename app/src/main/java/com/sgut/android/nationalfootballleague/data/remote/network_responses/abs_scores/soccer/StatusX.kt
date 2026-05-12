package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusX(
    @SerialName("clock")
    val clock: Double = 0.0,
    @SerialName("displayClock")
    val displayClock: String = "",
    @SerialName("type")
    val type: Type = Type()
)