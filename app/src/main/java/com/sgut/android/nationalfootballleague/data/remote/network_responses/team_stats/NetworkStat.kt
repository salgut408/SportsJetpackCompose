package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamStatsModel
import kotlinx.serialization.Serializable

@Serializable
data class NetworkStat(
    @SerialName("requestedSeason")
    val requestedSeason: RequestedSeason = RequestedSeason(),
    @SerialName("results")
    val results: Results = Results(),
    @SerialName("season")
    val season: Season = Season(),
    @SerialName("status")
    val status: String = "",
    @SerialName("team")
    val team: Team = Team()
)

fun NetworkStat.asDomain(): TeamStatsModel {
    return TeamStatsModel(
        results = results.asDomain(),
        season = season.asDomain(),
        team = team.asDomain()
    )
}