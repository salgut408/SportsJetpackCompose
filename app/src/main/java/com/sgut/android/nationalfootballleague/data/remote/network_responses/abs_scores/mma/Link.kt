package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.mma


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    @SerialName("href")
    val href: String = "",
    @SerialName("isExternal")
    val isExternal: Boolean = false,
    @SerialName("isPremium")
    val isPremium: Boolean = false,
    @SerialName("language")
    val language: String = "",
    @SerialName("rel")
    val rel: List<String> = listOf(),
    @SerialName("shortText")
    val shortText: String = "",
    @SerialName("text")
    val text: String = ""
)