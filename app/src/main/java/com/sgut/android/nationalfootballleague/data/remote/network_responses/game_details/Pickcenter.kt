package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.AwayTeamOddsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.HomeTeamOddsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.PickcenterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ProviderModel
import kotlinx.serialization.Serializable


@Serializable
data class Pickcenter(

  @SerialName("provider")
  val provider: Provider? = Provider(),
  @SerialName("details")
  val details: String? = null,
  @SerialName("overUnder")
  val overUnder: Double? = null,
  @SerialName("spread")
  val spread: Double? = null,
  @SerialName("awayTeamOdds")
  val awayTeamOdds: AwayTeamOdds? = AwayTeamOdds(),
  @SerialName("homeTeamOdds")
  val homeTeamOdds: HomeTeamOdds? = HomeTeamOdds(),

  )

fun Pickcenter.asDomain(): PickcenterModel {
  return PickcenterModel(
    provider = provider?.asDomain() ?: ProviderModel(),
    details = details ?: "",
    overUnder = overUnder ?: 0.0,
    spread = spread ?: 0.0,
    awayTeamOdds = awayTeamOdds?.asDomain() ?: AwayTeamOddsModel(),
    homeTeamOdds = homeTeamOdds?.asDomain() ?: HomeTeamOddsModel()
  )
}