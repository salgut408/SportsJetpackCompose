package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.hockey


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Competitor(
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("order")
    val order: Int = 0,
    @SerialName("score")
    val score: String = "",
    @Transient
    val statistics: List<Any> = listOf(),
    @SerialName("team")
    val team: Team = Team(),
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = ""
)