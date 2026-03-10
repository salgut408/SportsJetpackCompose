package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.SeasonModel
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("types")
    val types: List<Type> = listOf(),
    @SerialName("year")
    val year: Int = 0
)

fun Season.asDomain(): SeasonModel {
    return SeasonModel(
        displayName = displayName,
        endDate = endDate,
        startDate = startDate,
        types = types.map { it.asDomain() },
        year = year

    )
}