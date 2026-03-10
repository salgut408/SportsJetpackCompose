package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.FormatTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Format(
    @SerialName("regulation")
    val regulation: Regulation = Regulation()
)

fun Format.asDomain(): FormatTennisModel{
    return FormatTennisModel(
        regulation = regulation.asDomain()
    )
}