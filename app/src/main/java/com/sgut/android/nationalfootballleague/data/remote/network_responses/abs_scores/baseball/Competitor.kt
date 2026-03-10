package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.baseball


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competitor(
    @SerialName("errors")
    val errors: Int = 0,
    @SerialName("hits")
    val hits: Int = 0,
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("leaders")
    val leaders: List<Leader> = listOf(),
    @SerialName("linescores")
    val linescores: List<Linescore> = listOf(),
    @SerialName("order")
    val order: Int = 0,
    @SerialName("probables")
    val probables: List<Probable> = listOf(),
    @SerialName("records")
    val records: List<Record> = listOf(),
    @SerialName("score")
    val score: String = "",
    @SerialName("statistics")
    val statistics: List<StatisticX> = listOf(),
    @SerialName("team")
    val team: TeamXXX = TeamXXX(),
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = "",
    @SerialName("winner")
    val winner: Boolean = false
)