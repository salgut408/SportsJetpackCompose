package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.LinescoreTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Linescore(
    @SerialName("tiebreak")
    val tiebreak: Int = 0,
    @SerialName("value")
    val value: Double = 0.0,
    @SerialName("winner")
    val winner: Boolean = false
)

fun Linescore.asDomain(): LinescoreTennisModel{
    return LinescoreTennisModel(
        tiebreak = tiebreak,
        value = value,
        winner = winner
    )
}