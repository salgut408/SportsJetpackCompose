package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsPositionModel
import kotlinx.serialization.Serializable


@Serializable
data class Position(

  @SerialName("id") val id: String = "",
  @SerialName("name") val name: String = "",
  @SerialName("displayName") val displayName: String = "",
  @SerialName("abbreviation") val abbreviation: String = "",
  @SerialName("leaf") val leaf: Boolean? = null,
  @SerialName("parent") val parent: Parent3? = Parent3(),

  )

fun Position.asDomain(): GameDetailsPositionModel {
  return GameDetailsPositionModel(
    name = name,
    displayName = displayName,
    abbreviation =  abbreviation
  )
}