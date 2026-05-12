package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkXX(
    @SerialName("href")
    val href: String = "",
    @SerialName("isExternal")
    val isExternal: Boolean = false,
    @SerialName("isPremium")
    val isPremium: Boolean = false,
    @SerialName("rel")
    val rel: List<String> = listOf(),
    @SerialName("text")
    val text: String = ""
)