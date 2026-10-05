package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardRegulationModel
import kotlinx.serialization.Serializable


@Serializable
data class RegulationScoreboard (

  @SerialName("periods")
  val periods : Int? = null

)
fun RegulationScoreboard.asDomain(): ScoreboardRegulationModel {
  return ScoreboardRegulationModel(
    periods = periods ?: 0
  )
}