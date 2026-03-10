package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TennisScoreboardModel
import kotlinx.serialization.Serializable

@Serializable
data class TennisScoresNetwork(
    @SerialName("day")
    val day: Day = Day(),
    @SerialName("events")
    val events: List<Event> = listOf(),
    @SerialName("leagues")
    val leagues: List<League> = listOf(),
    @SerialName("season")
    val season: SeasonXX = SeasonXX()
)

fun TennisScoresNetwork.asDomain(): TennisScoreboardModel{
    return TennisScoreboardModel(
        day = day.asDomain(),
        events = events.map { it.asDomain() },
        league = leagues.first().asDomain(),
        season = season.asDomain()
    )
}
