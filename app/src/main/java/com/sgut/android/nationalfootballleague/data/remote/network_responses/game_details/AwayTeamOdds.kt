package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.AwayTeamOddsModel
import kotlinx.serialization.Serializable


@Serializable
data class AwayTeamOdds(

  @SerialName("favorite")
  val favorite: Boolean? = null,
  @SerialName("underdog")
  val underdog: Boolean? = null,
  @SerialName("moneyLine")
  val moneyLine: Float? = null,
  @SerialName("spreadOdds")
  val spreadOdds: Float? = null,
  @SerialName("teamId")
  val teamId: String? = null,

  )
fun AwayTeamOdds.asDomain(): AwayTeamOddsModel {
  return AwayTeamOddsModel(
    favorite = favorite ?: false,
    underdog = underdog ?: false,
    moneyLine = moneyLine ?: 0f,
    spreadOdds = spreadOdds ?: 0f,
    teamId = teamId ?: ""
  )
}