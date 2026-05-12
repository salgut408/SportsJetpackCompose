package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("hasGroups")
    val hasGroups: Boolean = false,
    @SerialName("hasLegs")
    val hasLegs: Boolean = false,
    @SerialName("hasStandings")
    val hasStandings: Boolean = false,
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("type")
    val type: Int = 0,
    @SerialName("week")
    val week: Week = Week(),

    @SerialName("year")
    val year: Int = 0
)