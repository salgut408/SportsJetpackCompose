package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Logo(
    @SerialName("height")
    val height: Int = 0,
    @SerialName("href")
    val href: String = "",
    @SerialName("rel")
    val rel: List<String> = listOf(),
    @SerialName("width")
    val width: Int = 0
)