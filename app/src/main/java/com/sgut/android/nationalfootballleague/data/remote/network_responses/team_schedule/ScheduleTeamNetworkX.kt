package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleTeamModelX
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTeamNetworkX(
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = ""
)

fun ScheduleTeamNetworkX.asDomain(): ScheduleTeamModelX {
    return ScheduleTeamModelX(
        id = id,
        name = name
    )
}