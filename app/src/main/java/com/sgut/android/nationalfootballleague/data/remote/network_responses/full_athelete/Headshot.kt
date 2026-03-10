package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Headshot(
    @SerialName("alt")
    val alt: String = "",
    @SerialName("href")
    val href: String = ""
)