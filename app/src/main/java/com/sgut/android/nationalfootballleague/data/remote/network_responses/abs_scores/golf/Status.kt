package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Status(
    @SerialName("period")
    val period: Int = 0,
    @SerialName("type")
    val type: TypeX = TypeX()
)