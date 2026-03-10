package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Web(
    @SerialName("href")
    val href: String = "",
    @SerialName("self")
    val self: Self = Self(),
    @SerialName("short")
    val short: Short = Short()
)