package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.PreviousWinnerTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class PreviousWinner(
    @SerialName("athletes")
    val athletes: List<AthleteXX> = listOf(),
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("headshot")
    val headshot: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("type")
    val type: TypeX = TypeX()
)


fun PreviousWinner.asDomain(): PreviousWinnerTennisModel {
    return PreviousWinnerTennisModel(
        athletes = athletes.map { it.asDomain() },
        displayName = displayName,
        headshot = headshot,
        shortDisplayName = shortDisplayName,
        type = type.asDomain()
    )
}