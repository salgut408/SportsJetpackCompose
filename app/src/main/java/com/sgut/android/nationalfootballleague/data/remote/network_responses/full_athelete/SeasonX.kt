package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("type")
    val type: Type = Type(),
    @SerialName("types")
    val types: Types = Types(),
    @SerialName("year")
    val year: Int = 0
)