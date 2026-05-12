package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.StatusTennisModelX
import kotlinx.serialization.Serializable

@Serializable
data class StatusX(
    @SerialName("period")
    val period: Int = 0,
    @SerialName("type")
    val type: TypeX = TypeX()
)

fun StatusX.asDomain(): StatusTennisModelX {
    return StatusTennisModelX(
        period = period,
        type = type.asDomain()
    )
}