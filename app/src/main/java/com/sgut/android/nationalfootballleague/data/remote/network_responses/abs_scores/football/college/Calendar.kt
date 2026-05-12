package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Calendar(
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("entries")
    val entries: List<Entry> = listOf(),
    @SerialName("label")
    val label: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("value")
    val value: String = ""
)