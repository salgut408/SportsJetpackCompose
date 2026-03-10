package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.mma


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Venue(
    @SerialName("address")
    val address: Address = Address(),
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("indoor")
    val indoor: Boolean = false
)