package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleCompetitionModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleCompetitionNetwork(
    @SerialName("attendance")
    val attendance: Int = 0,
    @SerialName("boxscoreAvailable")
    val boxscoreAvailable: Boolean = false,
//    @SerialName("broadcasts")
//    val broadcasts: List<Any> = listOf(),
    @SerialName("competitors")
    val competitors: List<ScheduleCompetitorNetwork> = listOf(),
    @SerialName("date")
    val date: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("neutralSite")
    val neutralSite: Boolean = false,
//    @SerialName("notes")
//    val notes: List<Any> = listOf(),
    @SerialName("status")
    val status: ScheduleStatusNetwork = ScheduleStatusNetwork(),
    @SerialName("tickets")
    val tickets: List<ScheduleTicketNetwork>? = listOf(),
    @SerialName("ticketsAvailable")
    val ticketsAvailable: Boolean = false,
    @SerialName("timeValid")
    val timeValid: Boolean = false,
    @SerialName("type")
    val type: ScheduleTypeNetworkX = ScheduleTypeNetworkX(),
    @SerialName("venue")
    val venue: ScheduleVenueNetwork = ScheduleVenueNetwork()
)

fun ScheduleCompetitionNetwork.asDomain(): ScheduleCompetitionModel {
    return ScheduleCompetitionModel(
        attendance = attendance,
        competitors = competitors.map { it.asDomain() },
        date = date,
        id = id,
        venue = venue.asDomain(),
        status = status.asDomain()
    )
}