package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.RoundTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Round(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = ""
)

fun Round.asDomain(): RoundTennisModel{
    return RoundTennisModel(
        displayName = displayName,
        id = id
    )
}