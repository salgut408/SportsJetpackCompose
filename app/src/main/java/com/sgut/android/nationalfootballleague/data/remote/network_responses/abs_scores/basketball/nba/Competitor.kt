package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.TeamComm
import kotlinx.serialization.Serializable

@Serializable
data class Competitor(
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("score")
    val score: String = "",
    @SerialName("team")
    val team: TeamComm = TeamComm(),
    @SerialName("uid")
    val uid: String = ""
)