package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponseNetwork(
    @SerialName("allstarsgame")
    val allstarsgame: String = "",
    @SerialName("events")
    val events: List<ScheduleEventNetwork> = listOf(),
    @SerialName("requestedSeason")
    val requestedSeason: ScheduleRequestedSeasonNetwork = ScheduleRequestedSeasonNetwork(),
    @SerialName("season")
    val season: ScheduleSeasonNetworkX = ScheduleSeasonNetworkX(),
    @SerialName("status")
    val status: String = "",
    @SerialName("team")
    val team: ScheduleTeamNetworkXX = ScheduleTeamNetworkXX(),
    @SerialName("timestamp")
    val timestamp: String = ""
)

fun ScheduleResponseNetwork.asDomain(): ScheduleDomainModel {
    return ScheduleDomainModel(
        events = events.map { it.asDomain() },
        season = season.asDomain(),
        team = team.asDomain()
    )
}