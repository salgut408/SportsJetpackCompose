package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleAddressModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleAddressNetwork(
    @SerialName("city")
    val city: String = "",
    @SerialName("state")
    val state: String = "",
    @SerialName("zipCode")
    val zipCode: String = ""
)

fun ScheduleAddressNetwork.asDomain(): ScheduleAddressModel {
    return ScheduleAddressModel(
        city = city,
        state = state,
        zipCode = zipCode,
    )
}