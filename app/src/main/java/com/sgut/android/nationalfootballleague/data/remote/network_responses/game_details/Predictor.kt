package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.PredictorModel
import kotlinx.serialization.Serializable


@Serializable
data class Predictor(

  @SerialName("header")
  val header: String? = null,
  @SerialName("homeTeam")
  val homeTeam: GameDetailsHomeTeam? = GameDetailsHomeTeam(),
  @SerialName("awayTeam")
  val awayTeam: GameDetailsAwayTeam? = GameDetailsAwayTeam(),

  )

fun Predictor.asDomain(): PredictorModel {
  return PredictorModel(
    header = header,
    homeTeam = homeTeam?.asDomain(),
    awayTeam = awayTeam?.asDomain()
  )
}