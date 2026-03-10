package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsInfo(
    @SerialName("seatSituation")
    val seatSituation: SeatSituation = SeatSituation(),
    @SerialName("tickets")
    val tickets: List<Ticket> = listOf()
)