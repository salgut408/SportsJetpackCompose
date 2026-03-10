package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AwayTeamOdds(
    @SerialName("favorite")
    val favorite: Boolean = false,
    @SerialName("moneyLine")
    val moneyLine: Int = 0,
    @SerialName("spreadOdds")
    val spreadOdds: Double = 0.0,
    @SerialName("team")
    val team: TeamXXX = TeamXXX(),
    @SerialName("underdog")
    val underdog: Boolean = false
)