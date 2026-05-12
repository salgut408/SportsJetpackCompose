package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.EntryModel
import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    @SerialName("stats")
    val stats: List<Stat> = listOf(),
    @SerialName("team")
    val team: Team = Team()
)

fun Entry.asDomain(): EntryModel {
    return EntryModel(
        stats = stats.map { it.asDomain() },
        team = team.asDomain()
    )
}