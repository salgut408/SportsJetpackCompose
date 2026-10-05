package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
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