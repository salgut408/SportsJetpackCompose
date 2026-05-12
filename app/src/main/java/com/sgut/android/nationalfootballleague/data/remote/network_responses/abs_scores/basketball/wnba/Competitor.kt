package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.wnba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competitor(
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("leaders")
    val leaders: List<Leader> = listOf(),
    @SerialName("order")
    val order: Int = 0,
    @SerialName("records")
    val records: List<Record> = listOf(),
    @SerialName("score")
    val score: String = "",
    @SerialName("statistics")
    val statistics: List<Statistic> = listOf(),
    @SerialName("team")
    val team: TeamXX = TeamXX(),
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = ""
)