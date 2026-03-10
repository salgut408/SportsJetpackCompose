package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.StatusTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    @SerialName("period")
    val period: Int = 0,
    @SerialName("type")
    val type: Type = Type()
)

fun Status.asDomain(): StatusTennisModel{
    return StatusTennisModel(
        period = period,
        type = type.asDomain()
    )
}