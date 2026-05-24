package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardNoteModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteScoreboard(
    @SerialName("type")
    val type: String = "",
    @SerialName("headline")
    val headline: String = "",
)

fun NoteScoreboard.asDomain(): ScoreboardNoteModel = ScoreboardNoteModel(
    type = type,
    headline = headline,
)