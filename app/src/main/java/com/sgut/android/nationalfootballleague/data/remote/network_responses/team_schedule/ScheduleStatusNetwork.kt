package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleStatusModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleStatusNetwork(
    @SerialName("clock")
    val clock: Double = 0.0,
    @SerialName("displayClock")
    val displayClock: String = "",
    @SerialName("featuredAthletes")
    val featuredAthletes: List<ScheduleFeaturedAthleteNetwork>? = listOf(),
    @SerialName("halfInning")
    val halfInning: Int = 0,
    @SerialName("period")
    val period: Int = 0,
    @SerialName("periodPrefix")
    val periodPrefix: String = "",
    @SerialName("type")
    val type: ScheduleTypeNetwork = ScheduleTypeNetwork()
)

fun ScheduleStatusNetwork.asDomain(): ScheduleStatusModel {
    return ScheduleStatusModel(
        clock = clock,
        displayClock = displayClock,
        featuredAthletes = featuredAthletes?.map { it.asDomain() },
        halfInning = halfInning,
        period = period,
        periodPrefix = periodPrefix,
        type = type.asDomain()
    )
}