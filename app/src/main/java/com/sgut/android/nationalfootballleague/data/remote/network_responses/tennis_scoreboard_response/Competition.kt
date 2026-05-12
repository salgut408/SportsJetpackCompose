package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TennisCompetitionModel
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("competitors")
    val competitors: List<Competitor> = listOf(),
    @SerialName("date")
    val date: String = "",
    @SerialName("format")
    val format: Format = Format(),
    @SerialName("id")
    val id: String = "",
    @SerialName("major")
    val major: Boolean = false,
    @SerialName("notes")
    val notes: List<Note> = listOf(),
    @SerialName("recent")
    val recent: Boolean = false,
    @SerialName("round")
    val round: Round = Round(),
    @SerialName("situation")
    val situation: Situation = Situation(),
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("status")
    val status: Status = Status(),
    @SerialName("timeValid")
    val timeValid: Boolean = false,
    @SerialName("tournamentId")
    val tournamentId: Int = 0,
    @SerialName("type")
    val type: TypeX = TypeX(),
    @SerialName("uid")
    val uid: String = "",
    @SerialName("venue")
    val venue: Venue = Venue(),
    @SerialName("wasSuspended")
    val wasSuspended: Boolean = false
)

fun Competition.asDomain(): TennisCompetitionModel {
    return TennisCompetitionModel(
        competitors = competitors.map { it.asDomain() },
        date = date,
        format = format.asDomain(),
        id = id,
        major = major,
        notes = notes.map { it.asDomain() },
        recent = recent,
        round = round.asDomain(),
        situation = situation.asDomain(),
        startDate = startDate,
        status = status.asDomain(),
        timeValid = timeValid,
        tournamentId = tournamentId,
        type = type.asDomain(),
        uid = uid,
        venue = venue.asDomain(),
        wasSuspended = wasSuspended,

    )
}