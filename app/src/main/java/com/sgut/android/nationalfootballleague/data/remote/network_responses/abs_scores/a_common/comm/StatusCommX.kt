package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba.TypeX
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusCommX(
    @SerialName("clock")
    val clock: Double = 0.0,
    @SerialName("displayClock")
    val displayClock: String = "",
    @SerialName("period")
    val period: Int = 0,
    @SerialName("type")
    val type: TypeX = TypeX()
)