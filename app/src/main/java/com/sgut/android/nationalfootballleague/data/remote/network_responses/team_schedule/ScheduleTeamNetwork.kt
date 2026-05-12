package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleTeamModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTeamNetwork(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("location")
    val location: String = "",
    @SerialName("logos")
    val logos: List<ScheduleLogoNetwork> = listOf(),
    @SerialName("shortDisplayName")
    val shortDisplayName: String = ""
)

fun ScheduleTeamNetwork.asDomain(): ScheduleTeamModel {
    return ScheduleTeamModel(
        abbreviation = abbreviation,
        displayName = displayName,
        id = id,
        logos = logos.getOrNull(0)?.href ?: "",
        shortDisplayName = shortDisplayName
    )
}