package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.SeasonSeriesCompetitorModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.SeasonSeriesEventModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.SeasonSeriesModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Seasonseries(
    @SerialName("type") val type: String = "",
    @SerialName("title") val title: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("summary") val summary: String = "",
    @SerialName("completed") val completed: Boolean = false,
    @SerialName("totalCompetitions") val totalCompetitions: Int = 0,
    @SerialName("seriesScore") val seriesScore: String = "",
    @SerialName("events") val events: List<SeasonseriesEvent> = listOf(),
)

@Serializable
data class SeasonseriesEvent(
    @SerialName("id") val id: String = "",
    @SerialName("uid") val uid: String = "",
    @SerialName("date") val date: String = "",
    @SerialName("status") val status: String = "",
    @SerialName("competitors") val competitors: List<SeasonseriesCompetitor> = listOf(),
)

@Serializable
data class SeasonseriesCompetitor(
    @SerialName("homeAway") val homeAway: String = "",
    @SerialName("winner") val winner: Boolean = false,
    @SerialName("score") val score: String = "",
    @SerialName("team") val team: SeasonseriesTeam = SeasonseriesTeam(),
)

@Serializable
data class SeasonseriesTeam(
    @SerialName("id") val id: String = "",
    @SerialName("abbreviation") val abbreviation: String = "",
    @SerialName("displayName") val displayName: String = "",
)

fun Seasonseries.asDomain(): SeasonSeriesModel = SeasonSeriesModel(
    type = type,
    title = title,
    description = description,
    summary = summary,
    completed = completed,
    totalCompetitions = totalCompetitions,
    seriesScore = seriesScore,
    events = events.map { it.asDomain() },
)

fun SeasonseriesEvent.asDomain(): SeasonSeriesEventModel = SeasonSeriesEventModel(
    id = id,
    uid = uid,
    date = date,
    status = status,
    competitors = competitors.map { it.asDomain() },
)

fun SeasonseriesCompetitor.asDomain(): SeasonSeriesCompetitorModel = SeasonSeriesCompetitorModel(
    homeAway = homeAway,
    winner = winner,
    score = score,
    teamId = team.id,
    teamAbbreviation = team.abbreviation,
    teamDisplayName = team.displayName,
)