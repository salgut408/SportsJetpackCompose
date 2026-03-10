package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.wnba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("competitors")
    val competitors: List<Competitor> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("status")
    val status: Status = Status(),
    @SerialName("uid")
    val uid: String = "",
)