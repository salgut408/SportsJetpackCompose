package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomainModel
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.Team
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomainModel
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardAthleteInvolvedModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamModel
import kotlinx.serialization.Serializable


@Serializable
data class AthletesInvolvedScoreboard(

  @SerialName("id")
  val id: String? = null,
  @SerialName("displayName")
  val displayName: String? = null,
  @SerialName("shortName")
  val shortName: String? = null,
  @SerialName("fullName")
  val fullName: String? = null,
  @SerialName("jersey")
  val jersey: String? = null,
  @SerialName("team")
  val team: Team? = Team(),
  @SerialName("links")
  val links: ArrayList<LinksScoreboard> = arrayListOf(),
  @SerialName("position")
  val position: String? = null,

  )

fun AthletesInvolvedScoreboard.asDomain(): ScoreboardAthleteInvolvedModel {
  return ScoreboardAthleteInvolvedModel(
    id = id ?: "",
    displayName = displayName ?: "",
    shortName = shortName ?: "",
    fullName = fullName ?: "",
    jersey = jersey ?: "",
    team = team?.asDomainModel() ?: TeamModel(),
    position = position ?: ""
  )
}
