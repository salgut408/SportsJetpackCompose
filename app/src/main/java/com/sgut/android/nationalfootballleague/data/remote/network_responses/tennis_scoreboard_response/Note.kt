package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.NoteTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("text")
    val text: String = "",
    @SerialName("type")
    val type: String = ""
)

fun Note.asDomain(): NoteTennisModel{
    return NoteTennisModel(
        text = text,
        type = type
    )
}