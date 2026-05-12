package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TypeTennisModelXXXX
import kotlinx.serialization.Serializable

@Serializable
data class TypeXXXX(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("type")
    val type: Int = 0
)
fun TypeXXXX.asDomain(): TypeTennisModelXXXX {
    return TypeTennisModelXXXX(
        abbreviation = abbreviation,
        id = id,
        name = name,
        type = type
    )
}