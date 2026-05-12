package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Odd(
    @SerialName("details")
    val details: String = "",
    @SerialName("overUnder")
    val overUnder: Double = 0.0,
    @SerialName("provider")
    val provider: Provider = Provider()
)