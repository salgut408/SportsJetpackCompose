package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("conditionId")
    val conditionId: String = "",
    @SerialName("displayValue")
    val displayValue: String = "",
    @SerialName("highTemperature")
    val highTemperature: Int = 0,
    @SerialName("temperature")
    val temperature: Int = 0
)