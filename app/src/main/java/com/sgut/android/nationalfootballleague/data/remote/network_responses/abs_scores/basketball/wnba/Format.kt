package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.wnba


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Format(
    @SerialName("regulation")
    val regulation: Regulation = Regulation()
)