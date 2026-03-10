package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleTypeModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTypeNetwork(
    @SerialName("completed")
    val completed: Boolean = false,
    @SerialName("description")
    val description: String = "",
    @SerialName("detail")
    val detail: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDetail")
    val shortDetail: String = "",
    @SerialName("state")
    val state: String = ""
)

fun ScheduleTypeNetwork.asDomain(): ScheduleTypeModel {
    return ScheduleTypeModel(
        completed = completed,
        description = description,
        detail = detail,
        id = id,
        shortDetail = shortDetail,
        state = state
    )
}