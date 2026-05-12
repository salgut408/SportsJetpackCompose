package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Market(
    @SerialName("id")
    val id: String = "",
    @SerialName("type")
    val type: String = ""
)