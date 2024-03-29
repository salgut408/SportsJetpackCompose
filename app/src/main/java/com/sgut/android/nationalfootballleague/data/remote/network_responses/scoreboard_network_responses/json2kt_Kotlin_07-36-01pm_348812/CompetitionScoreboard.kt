package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.SituationScoreboard
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitionModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardFormatModel


data class CompetitionScoreboard(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("uid")
    val uid: String? = null,
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("startDate")
    val startDate: String? = null,
    @SerializedName("attendance")
    val attendance: Int? = null,
    @SerializedName("status")
    val status: StatusScoreboard? = StatusScoreboard(),
    @SerializedName("venue")
    val venue: VenueScoreboard = VenueScoreboard(),
    @SerializedName("format")
    val format: FormatScoreboard? = FormatScoreboard(),
    @SerializedName("competitors")
    val competitors: List<CompetitorScoreboard> = listOf(),
    @SerializedName("details")
    val details: List<DetailsScoreboard> = listOf(),
    @SerializedName("headlines")
    val headlines: List<HeadlinesScoreboard> = listOf(),
    @SerializedName("situation")
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
        venue = venue.asDomain(),
        situation = situation ?: SituationScoreboard()
    )
}

