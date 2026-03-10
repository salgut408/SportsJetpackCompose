package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Round(
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("id")
    val id: String = ""
)