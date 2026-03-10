package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.basketball.college


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("city")
    val city: String = "",
    @SerialName("state")
    val state: String = ""
)