package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TypeTennisModelX
import kotlinx.serialization.Serializable

@Serializable
data class TypeX(
    @SerialName("id")
    val id: String = "",
    @SerialName("slug")
    val slug: String = "",
    @SerialName("text")
    val text: String = ""
)

fun TypeX.asDomain(): TypeTennisModelX{
    return TypeTennisModelX(
        id = id,
        slug = slug,
        text = text
    )
}