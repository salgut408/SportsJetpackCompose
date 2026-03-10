package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleEventModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleEventNetwork(
    @SerialName("competitions")
    val competitions: List<ScheduleCompetitionNetwork> = listOf(),
    @SerialName("date")
    val date: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("season")
    val season: ScheduleSeasonNetwork = ScheduleSeasonNetwork(),
    @SerialName("seasonType")
    val seasonType: ScheduleSeasonTypeNetwork = ScheduleSeasonTypeNetwork(),
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("timeValid")
    val timeValid: Boolean = false
)

fun ScheduleEventNetwork.asDomain(): ScheduleEventModel {
    return ScheduleEventModel(
        competitions = competitions.map { it.asDomain() },
        date = date,
        id = id,
        name = name,
        shortName = shortName,
        seasonType = seasonType.asDomain()
    )
}