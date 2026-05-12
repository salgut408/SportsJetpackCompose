package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.StatsModel
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("categories")
    val categories: List<Category> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = ""
)

fun Stats.asDomain(): StatsModel {
    return StatsModel(
        abbreviation = abbreviation,
        categories = categories.map { it.asDomain() },
        id = id,
        name = name
    )
}