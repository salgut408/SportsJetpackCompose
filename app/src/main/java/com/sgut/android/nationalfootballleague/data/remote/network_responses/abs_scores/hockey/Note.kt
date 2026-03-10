package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.hockey


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("headline")
    val headline: String = "",
    @SerialName("type")
    val type: String = ""
)