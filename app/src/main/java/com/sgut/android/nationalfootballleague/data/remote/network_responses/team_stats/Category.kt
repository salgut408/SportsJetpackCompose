package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.CategoryModel
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("stats")
    val stats: List<Stat> = listOf()
)

fun Category.asDomain(): CategoryModel {
    return CategoryModel(
        abbreviation = abbreviation,
        displayName = displayName,
        name = name,
        stats = stats.map { it.asDomain() }
    )
}