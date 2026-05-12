package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.RosterTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Roster(
    @SerialName("athletes")
    val athletes: List<AthleteX> = listOf(),
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = ""
)

fun Roster.asDomain(): RosterTennisModel{
    return RosterTennisModel(
        athletes = athletes.map { it.asDomain() },
        displayName = displayName,
        shortDisplayName = shortDisplayName
    )
}