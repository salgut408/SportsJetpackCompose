package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.SeasonTennisModelXX
import kotlinx.serialization.Serializable

@Serializable
data class SeasonXX(
    @SerialName("type")
    val type: Int = 0,
    @SerialName("year")
    val year: Int = 0
)

fun SeasonXX.asDomain(): SeasonTennisModelXX{
    return SeasonTennisModelXX(
        type = type,
        year = year
    )
}