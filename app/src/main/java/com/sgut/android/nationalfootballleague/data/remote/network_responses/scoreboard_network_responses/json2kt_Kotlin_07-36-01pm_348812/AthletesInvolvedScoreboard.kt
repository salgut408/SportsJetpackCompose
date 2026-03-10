package com.sgut.android.nationalfootballleague

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
