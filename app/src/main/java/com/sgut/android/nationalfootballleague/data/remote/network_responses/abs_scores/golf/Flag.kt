package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flag(
    @SerialName("alt")
    val alt: String = "",
    @SerialName("href")
    val href: String = "",
    @SerialName("rel")
    val rel: List<String> = listOf()
)