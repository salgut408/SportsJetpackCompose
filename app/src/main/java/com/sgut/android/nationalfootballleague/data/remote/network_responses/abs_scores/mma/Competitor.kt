package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.mma


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competitor(
    @SerialName("athlete")
    val athlete: Athlete = Athlete(),
    @SerialName("id")
    val id: String = "",
    @SerialName("order")
    val order: Int = 0,
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = "",
    @SerialName("winner")
    val winner: Boolean = false
)