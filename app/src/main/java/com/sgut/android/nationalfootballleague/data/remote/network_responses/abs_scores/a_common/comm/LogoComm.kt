package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoComm(
    @SerialName("alt")
    val alt: String = "",
    @SerialName("height")
    val height: Int = 0,
    @SerialName("href")
    val href: String = "",
    @SerialName("width")
    val width: Int = 0
)