package com.sgut.android.nationalfootballleague

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