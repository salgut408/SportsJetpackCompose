package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.SituationScoreboard
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.NoteScoreboard
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitionModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardFormatModel
import kotlinx.serialization.Serializable


@Serializable
data class CompetitionScoreboard(
    @SerialName("id")
    val id: String? = null,
    @SerialName("uid")
    val uid: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("attendance")
    val attendance: Int? = null,
    @SerialName("status")
    val status: StatusScoreboard? = StatusScoreboard(),
    @SerialName("venue")
    val venue: VenueScoreboard = VenueScoreboard(),
    @SerialName("format")
    val format: FormatScoreboard? = FormatScoreboard(),
    @SerialName("competitors")
    val competitors: List<CompetitorScoreboard> = listOf(),
    @SerialName("details")
    val details: List<DetailsScoreboard> = listOf(),
    @SerialName("headlines")
    val headlines: List<HeadlinesScoreboard> = listOf(),
    @SerialName("notes")
    val notes: List<NoteScoreboard> = listOf(),
    @SerialName("situation")
    val situation: SituationScoreboard? = SituationScoreboard(),


    )

fun CompetitionScoreboard.asDomain(): ScoreboardCompetitionModel {
    return ScoreboardCompetitionModel(
        id = id ?: "",
        uid = uid ?: "",
        date = date ?: "",
        startDate = startDate ?: "",
        attendance = attendance ?: 0,
        status = status?.asDomain(),
        format = format?.asDomain() ?: ScoreboardFormatModel(),
        competitors = competitors.map { it.asDomain() },
        details = details.map { it.asDomain() },
        headlines = headlines.map { it.asDomain() },
        notes = notes.map { it.asDomain() },
        venue = venue.asDomain(),
        situation = situation ?: SituationScoreboard()
    )
}

