package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.SituationTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Situation(
    @SerialName("onFirst")
    val onFirst: Boolean = false,
    @SerialName("onSecond")
    val onSecond: Boolean = false,
    @SerialName("onThird")
    val onThird: Boolean = false
)

fun Situation.asDomain(): SituationTennisModel{
    return SituationTennisModel(
        onFirst = onFirst,
        onSecond = onSecond,
        onThird = onThird
    )
}