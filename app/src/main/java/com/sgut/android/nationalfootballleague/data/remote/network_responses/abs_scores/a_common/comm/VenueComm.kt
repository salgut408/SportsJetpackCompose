package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VenueComm(
    @SerialName("address")
    val address: AddressComm = AddressComm(),
    @SerialName("capacity")
    val capacity: Int = 0,
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("indoor")
    val indoor: Boolean = false
)