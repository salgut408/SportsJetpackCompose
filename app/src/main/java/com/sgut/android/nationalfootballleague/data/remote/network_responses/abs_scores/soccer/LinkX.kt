package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkX(
    @SerialName("href")
    val href: String = "",
    @SerialName("isExternal")
    val isExternal: Boolean = false,
    @SerialName("isHidden")
    val isHidden: Boolean = false,
    @SerialName("isPremium")
    val isPremium: Boolean = false,
    @SerialName("rel")
    val rel: List<String> = listOf(),
    @SerialName("text")
    val text: String = ""
)