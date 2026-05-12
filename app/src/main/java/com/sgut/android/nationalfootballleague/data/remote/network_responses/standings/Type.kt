package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.TypeModel
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("hasStandings")
    val hasStandings: Boolean = false,
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("startDate")
    val startDate: String = ""
)

fun Type.asDomain(): TypeModel {
    return TypeModel(
        abbreviation = abbreviation,
        endDate = endDate,
        hasStandings = hasStandings,
        id = id,
        name = name,
        startDate = startDate
    )
}