package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    @SerialName("clock")
    val clock: Double = 0.0,
    @SerialName("displayClock")
    val displayClock: String = "",
    @SerialName("period")
    val period: Int = 0,
    @SerialName("type")
    val type: TypeX = TypeX()
)