package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.VenueTennisModelX
import kotlinx.serialization.Serializable

@Serializable
data class VenueX(
    @SerialName("displayName")
    val displayName: String = ""
)

fun VenueX.asDomain(): VenueTennisModelX {
    return VenueTennisModelX(
        displayName = displayName
    )
}