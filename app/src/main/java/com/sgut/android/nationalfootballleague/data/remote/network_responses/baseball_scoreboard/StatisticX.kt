package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatisticX(
    @SerialName("abbreviation")
    val abbreviation: String? = "",
    @SerialName("displayValue")
    val displayValue: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("rankDisplayValue")
    val rankDisplayValue: String? = ""
)