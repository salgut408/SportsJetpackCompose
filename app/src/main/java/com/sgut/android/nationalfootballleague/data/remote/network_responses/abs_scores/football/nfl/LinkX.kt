package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkX(
    @SerialName("href")
    val href: String = "",
    @SerialName("rel")
    val rel: List<String> = listOf()
)