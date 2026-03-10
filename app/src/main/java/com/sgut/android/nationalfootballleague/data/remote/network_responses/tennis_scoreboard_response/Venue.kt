package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.VenueTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    @SerialName("court")
    val court: String = "",
    @SerialName("fullName")
    val fullName: String = ""
)

fun Venue.asDomain(): VenueTennisModel{
    return VenueTennisModel(
        court = court,
        fullName = fullName,
    )
}