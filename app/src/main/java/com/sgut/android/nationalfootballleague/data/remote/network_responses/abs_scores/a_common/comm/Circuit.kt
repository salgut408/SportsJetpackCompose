package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Circuit(
    @SerialName("address")
    val address: AddressComm = AddressComm(),
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("id")
    val id: String = ""
)