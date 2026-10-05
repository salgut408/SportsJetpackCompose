package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsVenueModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsVenue(

  @SerialName("id")
  val id: String = "",
  @SerialName("fullName")
  val fullName: String = "",
  @SerialName("address")
  val address: GameDetailsAddress = GameDetailsAddress(),
  @SerialName("capacity")
  val capacity: Int = 0,
  @SerialName("grass")
  val grass: Boolean = false,
  @SerialName("images")
  val images: List<GameDetailsImages> = listOf(),

  )

fun GameDetailsVenue.asDomain(): GameDetailsVenueModel {
  return GameDetailsVenueModel(
    id = id,
    fullName = fullName,
    address = address.asDomain(),
    capacity = capacity,
    grass = grass,
    images = images
  )
}