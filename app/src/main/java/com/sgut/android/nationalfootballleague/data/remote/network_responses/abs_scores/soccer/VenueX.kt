package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.soccer


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueX(
    @SerialName("address")
    val address: Address = Address(),
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("id")
    val id: String = ""
)