package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Link(
    @SerialName("href")
    val href: String = "",
    @SerialName("isHidden")
    val isHidden: Boolean = false,
    @SerialName("rel")
    val rel: List<String> = listOf()
)