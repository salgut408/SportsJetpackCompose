package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleSeasonModelX
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleSeasonNetworkX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("half")
    val half: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("type")
    val type: Int = 0,
    @SerialName("year")
    val year: Int = 0
)

fun ScheduleSeasonNetworkX.asDomain(): ScheduleSeasonModelX {
    return ScheduleSeasonModelX(
        displayName = displayName,
        name = name,
        year = year
    )
}