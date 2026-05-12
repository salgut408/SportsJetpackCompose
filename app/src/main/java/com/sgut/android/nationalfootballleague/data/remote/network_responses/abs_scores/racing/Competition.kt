package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.racing


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("id")
    val id: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("status")
    val status: StatusX = StatusX(),
    @SerialName("uid")
    val uid: String = ""
)