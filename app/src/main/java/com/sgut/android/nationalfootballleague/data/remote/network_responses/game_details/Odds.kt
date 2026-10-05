package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BettingOddsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.OddsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ProviderModel
import kotlinx.serialization.Serializable


@Serializable
data class Odds(

  @SerialName("provider")
  val provider: Provider? = Provider(),
  @SerialName("bettingOdds")
  val bettingOdds: BettingOdds? = BettingOdds(),

  )

fun Odds.asDomain(): OddsModel {
  return  OddsModel(
    provider = provider?.asDomain() ?: ProviderModel(),
    bettingOdds = bettingOdds?.asDomain() ?: BettingOddsModel()
  )
}