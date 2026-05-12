package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Headline(
    @SerialName("description")
    val description: String = "",
    @SerialName("shortLinkText")
    val shortLinkText: String = "",
    @SerialName("type")
    val type: String = ""
)