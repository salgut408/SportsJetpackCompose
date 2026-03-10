package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StandingsModel
import kotlinx.serialization.Serializable

@Serializable
data class StandingsNetworkResponse(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("children")
    val children: List<Children> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("links")
    val links: List<LinkXX> = listOf(),
    @SerialName("name")
    val name: String = "",
    @SerialName("seasons")
    val seasons: List<Season> = listOf(),
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("uid")
    val uid: String = ""
)

fun StandingsNetworkResponse.asDomain(): StandingsModel {
    return StandingsModel(
        abbreviation = abbreviation,
        children = children.map { it.asDomain() },
        name = name,
        id = id,
        uid = uid,
        shortName = shortName,
        seasons = seasons.map { it.asDomain() }

    )
}