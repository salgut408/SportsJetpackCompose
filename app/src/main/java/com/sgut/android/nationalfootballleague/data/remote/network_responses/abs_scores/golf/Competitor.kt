package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.AthleteGolf
import kotlinx.serialization.Serializable

@Serializable
data class Competitor(
    @SerialName("athlete")
    val athlete: AthleteGolf = AthleteGolf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("score")
    val score: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = ""
)