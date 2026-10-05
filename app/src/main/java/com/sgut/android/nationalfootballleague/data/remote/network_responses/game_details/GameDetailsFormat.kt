package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsFormatModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsOvertimeModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsRegulationModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsFormat(

  @SerialName("regulation")
  val regulation: GameDetailsRegulation? = GameDetailsRegulation(),
  @SerialName("overtime")
  val overtime: GameDetailsOvertime? = GameDetailsOvertime(),

  )

fun GameDetailsFormat.asDomain(): GameDetailsFormatModel {
  return GameDetailsFormatModel(
    regulation = regulation?.asDomain() ?: GameDetailsRegulationModel(),
    overtime = overtime?.asDomain() ?: GameDetailsOvertimeModel()
  )
}