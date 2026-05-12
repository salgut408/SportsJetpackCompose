package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    @SerialName("alternateLabel")
    val alternateLabel: String = "",
    @SerialName("detail")
    val detail: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("label")
    val label: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("value")
    val value: String = ""
)