package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CuratedRank(
    @SerialName("current")
    val current: Int = 0
)