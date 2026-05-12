package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.AthleteTennisModelXX
import kotlinx.serialization.Serializable

@Serializable
data class AthleteXX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("headshot")
    val headshot: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = ""
)

fun AthleteXX.asDomain(): AthleteTennisModelXX {
    return AthleteTennisModelXX(
        displayName = displayName,
        headshot = headshot,
        shortDisplayName = shortDisplayName
    )
}