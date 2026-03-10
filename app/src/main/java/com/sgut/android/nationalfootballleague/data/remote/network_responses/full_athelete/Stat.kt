package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("displayValue")
    val displayValue: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("value")
    val value: Double = 0.0
)