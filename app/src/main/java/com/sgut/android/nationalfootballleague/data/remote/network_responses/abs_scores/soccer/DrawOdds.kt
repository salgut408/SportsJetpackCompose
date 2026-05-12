package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrawOdds(
    @SerialName("moneyLine")
    val moneyLine: Int = 0
)