package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamModel
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("color")
    val color: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("location")
    val location: String = "",
    @SerialName("logo")
    val logo: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("recordSummary")
    val recordSummary: String = "",
    @SerialName("seasonSummary")
    val seasonSummary: String = "",
    @SerialName("standingSummary")
    val standingSummary: String = ""
)

fun Team.asDomain(): TeamModel {
    return TeamModel(
        abbreviation = abbreviation,
        color = color,
        displayName = displayName,
        id = id,
        location = location,
        logo = logo,
        name = name,
        recordSummary = recordSummary,
        seasonSummary = seasonSummary,
        standingSummary = standingSummary
    )
}