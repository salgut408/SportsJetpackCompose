package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleFeaturedAthleteModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleFeaturedAthleteNetwork(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("athlete")
    val athlete: ScheduleAthleteNetwork = ScheduleAthleteNetwork(),
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("playerId")
    val playerId: Int = 0,
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("team")
    val team: ScheduleTeamNetworkX = ScheduleTeamNetworkX()
)

fun ScheduleFeaturedAthleteNetwork.asDomain(): ScheduleFeaturedAthleteModel {
    return ScheduleFeaturedAthleteModel(
        abbreviation = abbreviation,
        athlete = athlete.asDomain(),
        displayName = displayName,
        name = name,
        playerId = playerId,
        shortDisplayName = shortDisplayName,
        team = team.asDomain()
    )
}