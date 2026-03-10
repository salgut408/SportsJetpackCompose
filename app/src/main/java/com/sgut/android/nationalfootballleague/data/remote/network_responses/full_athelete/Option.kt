package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Option(
    @SerialName("displayValue")
    val displayValue: String = "",
    @SerialName("value")
    val value: String = ""
)