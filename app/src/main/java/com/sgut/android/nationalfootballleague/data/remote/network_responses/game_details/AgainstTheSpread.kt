package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.AgainstTheSpreadModel
import kotlinx.serialization.Serializable


@Serializable
data class AgainstTheSpread(

  @SerialName("team")
  val team: GameDetailsTeam = GameDetailsTeam(),
  @SerialName("records")
  val records: ArrayList<String> = arrayListOf(),

  )

fun AgainstTheSpread.asDomain(): AgainstTheSpreadModel {
  return AgainstTheSpreadModel(
    team = team.asDomain(),
    records = records
  )
}