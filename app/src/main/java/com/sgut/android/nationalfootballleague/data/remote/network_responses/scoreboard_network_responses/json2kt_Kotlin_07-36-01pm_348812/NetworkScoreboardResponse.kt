package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel
import kotlinx.serialization.Serializable


@Serializable
data class NetworkScoreboardResponse(
    @SerialName("leagues")
    val leagues: List<LeaguesScoreboard> = listOf(),
    @SerialName("season")
    val season: SeasonScoreboard? = SeasonScoreboard(),
    @SerialName("day")
    val day: DayScoreboard? = DayScoreboard(),
    @SerialName("events")
    val events: List<EventScoreboard> = listOf(),
    )





fun NetworkScoreboardResponse.asDomain(): BasicScoreboardModel {
    return BasicScoreboardModel(
        league = leagues.first().asDomain(),
        day = day?.date ,
        events = events.map { it.asDomain() },
    )
}