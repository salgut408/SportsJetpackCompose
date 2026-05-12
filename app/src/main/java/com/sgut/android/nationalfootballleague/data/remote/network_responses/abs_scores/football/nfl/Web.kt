package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Web(
    @SerialName("href")
    val href: String = "",
    @SerialName("short")
    val short: Short = Short()
)