package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("slug")
    val slug: String = "",
    @SerialName("type")
    val type: Int = 0,
    @SerialName("year")
    val year: Int = 0
)