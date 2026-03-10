package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleAthleteModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleAthleteNetwork(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("lastName")
    val lastName: String = "",
    @SerialName("record")
    val record: String = "",
    @SerialName("saves")
    val saves: String = "",
    @SerialName("shortName")
    val shortName: String = ""
)

fun ScheduleAthleteNetwork.asDomain(): ScheduleAthleteModel {
    return ScheduleAthleteModel(
        displayName = displayName,
        id = id,
        lastName = lastName,
        record = record,
        saves = saves,
        shortName = shortName,
    )
}