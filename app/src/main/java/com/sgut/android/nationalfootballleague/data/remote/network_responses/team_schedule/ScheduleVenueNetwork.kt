package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleVenueModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleVenueNetwork(
    @SerialName("address")
    val address: ScheduleAddressNetwork = ScheduleAddressNetwork(),
    @SerialName("fullName")
    val fullName: String = ""
)

fun ScheduleVenueNetwork.asDomain(): ScheduleVenueModel {
    return ScheduleVenueModel(
        address = address.asDomain(),
        fullName = fullName
    )
}