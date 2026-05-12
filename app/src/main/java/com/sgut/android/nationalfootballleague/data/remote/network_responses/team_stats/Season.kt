package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.SeasonModel
import kotlinx.serialization.Serializable

@Serializable
data class Season(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("type")
    val type: Int = 0,
    @SerialName("year")
    val year: Int = 0
)

fun Season.asDomain(): SeasonModel {
    return SeasonModel(
        displayName = displayName,
        name = name,
        type = type,
        year = year
    )
}