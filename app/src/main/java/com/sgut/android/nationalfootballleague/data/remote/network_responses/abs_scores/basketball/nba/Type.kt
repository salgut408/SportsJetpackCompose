package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.nba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("id")
    val id: String = "",
    @SerialName("shortName")
    val shortName: String = ""
)