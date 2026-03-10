package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.SplitModel
import kotlinx.serialization.Serializable

@Serializable
data class Split(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("categories")
    val categories: List<Category> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = ""
)
fun Split.asDomain(): SplitModel {
    return SplitModel(
        abbreviation = abbreviation,
        categories = categories.map { it.asDomain() },
        id = id,
        name = name
    )
}