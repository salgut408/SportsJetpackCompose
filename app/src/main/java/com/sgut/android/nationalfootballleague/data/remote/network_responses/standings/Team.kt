package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.TeamModel
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("isActive")
    val isActive: Boolean = false,
    @SerialName("links")
    val links: List<LinkXX> = listOf(),
    @SerialName("location")
    val location: String = "",
    @SerialName("logos")
    val logos: List<Logo> = listOf(),
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("uid")
    val uid: String = ""
)

fun Team.asDomain(): TeamModel {
    return TeamModel(
        abbreviation = abbreviation,
        displayName = displayName,
        id = id,
        location = location,
        logos = logos.map { it.asDomain() },
        name = name,
        shortDisplayName = shortDisplayName,
        uid = uid

    )
}