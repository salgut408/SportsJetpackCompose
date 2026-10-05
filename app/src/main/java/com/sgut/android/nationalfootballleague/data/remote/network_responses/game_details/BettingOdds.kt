package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BettingOddsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAwayTeamModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsHomeTeamModel
import kotlinx.serialization.Serializable


@Serializable
data class BettingOdds (

  @SerialName("homeTeam" )
  val homeTeam : GameDetailsHomeTeam? = GameDetailsHomeTeam(),
  @SerialName("awayTeam" )
  val awayTeam : GameDetailsAwayTeam? = GameDetailsAwayTeam(),
  @SerialName("teamOdds" )
  val teamOdds : TeamOdds? = TeamOdds()

)

fun BettingOdds.asDomain(): BettingOddsModel {
  return BettingOddsModel(
    homeTeam = homeTeam?.asDomain() ?: GameDetailsHomeTeamModel(),
    awayTeam = awayTeam?.asDomain() ?: GameDetailsAwayTeamModel()
  )
}