package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleTeamModelXX
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTeamNetworkXX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("clubhouse")
    val clubhouse: String = "",
    @SerialName("color")
    val color: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("groups")
    val groups: ScheduleGroupsNetwork = ScheduleGroupsNetwork(),
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
    val seasonSummary: String = ""
)

fun ScheduleTeamNetworkXX.asDomain(): ScheduleTeamModelXX {
    return ScheduleTeamModelXX(
        abbreviation = abbreviation,
        color = color,
        displayName = displayName,
        id = id,
        location = location,
        logo = logo,
        name = name,
        recordSummary = recordSummary,
        seasonSummary = seasonSummary
    )
}