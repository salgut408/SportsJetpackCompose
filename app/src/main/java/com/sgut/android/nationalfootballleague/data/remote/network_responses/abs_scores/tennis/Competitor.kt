package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Competitor(
    @SerialName("athlete")
    val athlete: Athlete = Athlete(),
    @SerialName("curatedRank")
    val curatedRank: CuratedRank = CuratedRank(),
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("linescores")
    val linescores: List<Linescore> = listOf(),
    @SerialName("order")
    val order: Int = 0,
    @Transient
    val statistics: List<Any> = listOf(),
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = "",
    @SerialName("winner")
    val winner: Boolean = false
)