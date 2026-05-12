package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.wnba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statistic(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayValue")
    val displayValue: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("rankDisplayValue")
    val rankDisplayValue: String = ""
)