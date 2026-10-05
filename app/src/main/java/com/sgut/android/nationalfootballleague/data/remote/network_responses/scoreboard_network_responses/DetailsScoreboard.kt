package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardTeamModel
import kotlinx.serialization.Serializable


@Serializable
data class DetailsScoreboard(

  @SerialName("type")
  val type: TypeScoreboard? = TypeScoreboard(),
  @SerialName("clock")
  val clock: ClockScoreboard? = ClockScoreboard(),
  @SerialName("team")
  val team: TeamScoreboard? = TeamScoreboard(),
  @SerialName("scoreValue")
  val scoreValue: Int? = null,
  @SerialName("scoringPlay")
  val scoringPlay: Boolean? = null,
  @SerialName("redCard")
  val redCard: Boolean? = null,
  @SerialName("yellowCard")
  val yellowCard: Boolean? = null,
  @SerialName("penaltyKick")
  val penaltyKick: Boolean? = null,
  @SerialName("ownGoal")
  val ownGoal: Boolean? = null,
  @SerialName("shootout")
  val shootout: Boolean? = null,
  @SerialName("athletesInvolved")
  val athletesInvolved: List<AthletesInvolvedScoreboard> = listOf(),

  )

fun DetailsScoreboard.asDomain(): ScoreboardDetailsModel {
  return ScoreboardDetailsModel(
    type = type?.asDomain(),
    clock = clock?.asDomain(),
    team = team?.asDomain() ?: ScoreboardTeamModel(),
    scoreValue = scoreValue ?: 0,
    scoringPlay = scoringPlay ?: false,
    redCard = redCard,
    yellowCard = yellowCard,
    penaltyKick = penaltyKick,
    ownGoal = ownGoal,
    shootout = shootout,
    athletesInvolved = athletesInvolved.map{ it.asDomain() }
  )
}