package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StandingsResponseModel
import kotlinx.serialization.Serializable

@Serializable
data class Standings(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("entries")
    val entries: List<Entry> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("links")
    val links: List<LinkXX> = listOf(),
    @SerialName("name")
    val name: String = "",
    @SerialName("season")
    val season: Int = 0,
    @SerialName("seasonType")
    val seasonType: Int = 0
)

fun Standings.asDomain(): StandingsResponseModel {
    return StandingsResponseModel(
        displayName = displayName,
        entries = entries.map { it.asDomain() },
        id = id,
        name = name,
        season = season,
        seasonType = seasonType,
    )
}