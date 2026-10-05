package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsStatusModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsStatus(

  @SerialName("type")
  val type: GameDetailsType? = GameDetailsType(),
  @SerialName("periodPrefix")
  val periodPrefix: InningPrefix? =InningPrefix.PRE,
  )

@Serializable
enum class InningPrefix {
  @SerialName("Mid")
  MID,
  @SerialName("Top")
  TOP,
  @SerialName("Pre")
  PRE,
  @SerialName("Bottom")
  BOTTOM,
  @SerialName("End")
  END,
  @SerialName("Start")
  START
}

fun GameDetailsStatus.asDomain(): GameDetailsStatusModel {
  return GameDetailsStatusModel(
    type = type?.asDomain(),
    periodPrefix = periodPrefix
  )
}