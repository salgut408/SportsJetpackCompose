package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayComm(
    @SerialName("date")
    val date: String = ""
)