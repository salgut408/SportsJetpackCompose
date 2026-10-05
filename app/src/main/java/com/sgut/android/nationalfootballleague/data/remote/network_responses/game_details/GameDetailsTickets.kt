package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.TicketModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsTickets(

  @SerialName("ticketName")
  val ticketName: String? = null,
  @SerialName("ticketLink")
  val ticketLink: String? = null,
  @SerialName("type")
  val type: String? = null,

  )

fun GameDetailsTickets.asDomain(): TicketModel {
  return TicketModel(
    ticketName = ticketName ?: "",
    ticketLink = ticketLink ?: "",
    type = type ?: ""
  )
}