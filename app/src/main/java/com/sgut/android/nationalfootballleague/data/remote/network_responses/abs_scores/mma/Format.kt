package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.mma


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Format(
    @SerialName("regulation")
    val regulation: Regulation = Regulation()
)