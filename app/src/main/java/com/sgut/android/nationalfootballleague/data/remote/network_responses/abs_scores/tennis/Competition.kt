package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("competitors")
    val competitors: List<Competitor> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("major")
    val major: Boolean = false,
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("status")
    val status: Status = Status(),
    @SerialName("uid")
    val uid: String = "",

)