package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.RegulationTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Regulation(
    @SerialName("periods")
    val periods: Int = 0
)

fun Regulation.asDomain(): RegulationTennisModel{
    return RegulationTennisModel(
        periods = periods
    )
}