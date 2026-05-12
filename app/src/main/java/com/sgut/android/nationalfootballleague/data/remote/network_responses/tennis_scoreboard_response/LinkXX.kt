package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkXX(
    @SerialName("href")
    val href: String = "",
    @SerialName("isExternal")
    val isExternal: Boolean = false,
    @SerialName("isHidden")
    val isHidden: Boolean = false,
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