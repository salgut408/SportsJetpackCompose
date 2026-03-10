package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.LogoTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Logo(
    @SerialName("alt")
    val alt: String = "",
    @SerialName("height")
    val height: Int = 0,
    @SerialName("href")
    val href: String = "",
    @SerialName("lastUpdated")
    val lastUpdated: String = "",
    @SerialName("width")
    val width: Int = 0
)

fun Logo.asDomain(): LogoTennisModel {
    return LogoTennisModel(
        alt = alt,
        height = height,
        href = href,
        lastUpdated = lastUpdated,
        width = width
    )
}