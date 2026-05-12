package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.racing


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Calendar(
    @SerialName("endDate")
    val endDate: String = "",

    @SerialName("label")
    val label: String = "",
    @SerialName("startDate")
    val startDate: String = ""
)