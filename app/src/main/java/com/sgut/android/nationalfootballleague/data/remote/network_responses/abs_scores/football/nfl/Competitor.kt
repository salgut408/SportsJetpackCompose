package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.football.nfl


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Competitor(
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("linescores")
    val linescores: List<Linescore> = listOf(),
    @SerialName("order")
    val order: Int = 0,
    @SerialName("records")
    val records: List<Record> = listOf(),
    @SerialName("score")
    val score: String = "",
    @Transient
    val statistics: List<Any> = listOf(),
    @SerialName("team")
    val team: Team = Team(),
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = "",
    @SerialName("winner")
    val winner: Boolean = false
)