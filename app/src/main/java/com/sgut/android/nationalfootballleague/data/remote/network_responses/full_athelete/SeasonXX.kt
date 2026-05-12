package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeasonXX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("type")
    val type: TypeX = TypeX(),
    @SerialName("types")
    val types: TypesX = TypesX(),
    @SerialName("year")
    val year: Int = 0
)