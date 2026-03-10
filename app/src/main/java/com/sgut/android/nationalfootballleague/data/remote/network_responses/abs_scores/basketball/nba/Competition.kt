package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.StatusCommX
import kotlinx.serialization.Serializable

@Serializable
data class Competition(
    @SerialName("competitors")
    val competitors: List<Competitor> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("odds")
    val odds: List<Odd> = listOf(),
    @SerialName("startDate")
    val startDate: String = "",
    @SerialName("status")
    val status: StatusCommX = StatusCommX(),
    @SerialName("uid")
    val uid: String = "",

)