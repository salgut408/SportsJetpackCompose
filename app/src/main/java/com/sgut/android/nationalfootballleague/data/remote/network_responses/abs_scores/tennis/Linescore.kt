package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Linescore(
    @SerialName("tiebreak")
    val tiebreak: Int = 0,
    @SerialName("value")
    val value: Double = 0.0,
    @SerialName("winner")
    val winner: Boolean = false
)