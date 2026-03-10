package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleSeasonTypeModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleSeasonTypeNetwork(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("type")
    val type: Int = 0
)

fun ScheduleSeasonTypeNetwork.asDomain(): ScheduleSeasonTypeModel {
    return ScheduleSeasonTypeModel(
        abbreviation = abbreviation,
        id = id,
         name = name,
        type = type
    )
}