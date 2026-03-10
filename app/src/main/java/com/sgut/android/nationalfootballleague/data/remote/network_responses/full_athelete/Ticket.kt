package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ticket(
    @SerialName("link")
    val link: String = "",
    @SerialName("name")
    val name: String = ""
)