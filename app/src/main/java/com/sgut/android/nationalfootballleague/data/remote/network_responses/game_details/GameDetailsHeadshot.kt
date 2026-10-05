package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsHeadshotModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsHeadshot (

  @SerialName("href" )
  val href : String? = null,
  @SerialName("alt"  )
  val alt  : String? = null

)
fun GameDetailsHeadshot.asDomain(): GameDetailsHeadshotModel {
  return GameDetailsHeadshotModel(
    href = href ?: "",
    alt = alt ?: ""
  )
}