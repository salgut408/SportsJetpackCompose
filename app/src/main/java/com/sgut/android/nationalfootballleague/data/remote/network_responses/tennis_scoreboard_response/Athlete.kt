package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.AthleteTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Athlete(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("flag")
    val flag: Flag = Flag(),
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("guid")
    val guid: String = "",
    @SerialName("shortName")
    val shortName: String = ""
)

fun Athlete.asDomain(): AthleteTennisModel{
    return AthleteTennisModel(
        displayName = displayName,
        fullName = fullName,
        guid = guid,
        shortName = shortName,
        flag = flag.asDomain()
    )
}