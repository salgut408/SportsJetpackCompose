package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.TicketsInfoModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsTicketsInfo(

  @SerialName("tickets")
  val tickets: List<GameDetailsTickets> = listOf(),
  @SerialName("seatSituation")
  val seatSituation: SeatSituation? = SeatSituation(),

  )

fun GameDetailsTicketsInfo.asDomain() : TicketsInfoModel {
  return TicketsInfoModel(
    tickets = tickets.map { it.asDomain() },
    seatSituation = seatSituation?.asDomain()
  )
}