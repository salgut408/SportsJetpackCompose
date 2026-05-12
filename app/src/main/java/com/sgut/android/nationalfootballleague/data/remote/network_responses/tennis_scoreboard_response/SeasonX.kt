package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.SeasonTennisModelX
import kotlinx.serialization.Serializable

@Serializable
data class SeasonX(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("endDate")
    val endDate: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("type")
    val type: TypeXXXX = TypeXXXX(),
    @SerialName("year")
    val year: Int = 0
)

fun SeasonX.asDomain(): SeasonTennisModelX{
    return SeasonTennisModelX(
        displayName = displayName,
        endDate = endDate,
        startDate = startDate,
        type = type.asDomain(),
        year = year
    )
}