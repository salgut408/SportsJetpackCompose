package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.DayTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerialName("date")
    val date: String = ""
)

fun Day.asDomain(): DayTennisModel{
    return DayTennisModel(
        date = date
    )
}
