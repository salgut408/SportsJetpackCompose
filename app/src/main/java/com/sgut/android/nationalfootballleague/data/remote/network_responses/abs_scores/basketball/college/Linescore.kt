package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Linescore(
    @SerialName("value")
    val value: Double = 0.0
)