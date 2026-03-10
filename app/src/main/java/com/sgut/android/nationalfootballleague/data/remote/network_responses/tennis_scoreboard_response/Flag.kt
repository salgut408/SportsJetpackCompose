package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.FlagTennisModel
import kotlinx.serialization.Serializable

@Serializable
data class Flag(
    @SerialName("alt")
    val alt: String = "",
    @SerialName("href")
    val href: String = "",
    @SerialName("rel")
    val rel: List<String> = listOf()
)
fun Flag.asDomain(): FlagTennisModel {
    return FlagTennisModel(
        alt = alt,
        href = href,
        rel = rel
    )
}