package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Situation(
    @SerialName("balls")
    val balls: Int? = 0,
    @SerialName("batter")
    val batter: Batter? = Batter(),
    @SerialName("dueUp")
    val dueUp: List<DueUp>? = listOf(),
    @SerialName("lastPlay")
    val lastPlay: LastPlay? = LastPlay(),
    @SerialName("onFirst")
    val onFirst: Boolean? = false,
    @SerialName("onSecond")
    val onSecond: Boolean? = false,
    @SerialName("onThird")
    val onThird: Boolean? = false,
    @SerialName("outs")
    val outs: Int? = 0,
    @SerialName("pitcher")
    val pitcher: Pitcher? = Pitcher(),
    @SerialName("strikes")
    val strikes: Int? = 0
)