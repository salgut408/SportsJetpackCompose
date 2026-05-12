package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_schedule


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleCompetitorModel
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleCompetitorNetwork(
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("order")
    val order: Int = 0,
    @SerialName("record")
    val record: List<ScheduleRecordNetwork>? = listOf(),
    @SerialName("score")
    val score: ScheduleScoreNetwork? = ScheduleScoreNetwork(),
    @SerialName("team")
    val team: ScheduleTeamNetwork = ScheduleTeamNetwork(),
    @SerialName("type")
    val type: String = "",
    @SerialName("winner")
    val winner: Boolean? = false
)

fun ScheduleCompetitorNetwork.asDomain(): ScheduleCompetitorModel {
    return ScheduleCompetitorModel(
        homeAway = homeAway,
        id = id,
        team = team.asDomain(),
        winner = winner
    )
}