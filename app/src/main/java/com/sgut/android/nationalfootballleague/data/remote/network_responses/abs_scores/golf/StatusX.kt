package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusX(
    @SerialName("type")
    val type: TypeXX = TypeXX()
)