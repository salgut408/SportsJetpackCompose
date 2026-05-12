package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.AthleteTennisModelX
import kotlinx.serialization.Serializable

@Serializable
data class AthleteX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("guid")
    val guid: String = "",

    @SerialName("shortName")
    val shortName: String = ""
)

fun AthleteX.asDomain(): AthleteTennisModelX{
    return AthleteTennisModelX(
        displayName = displayName,
        fullName = fullName,
        guid = guid,
        shortName = shortName
    )
}