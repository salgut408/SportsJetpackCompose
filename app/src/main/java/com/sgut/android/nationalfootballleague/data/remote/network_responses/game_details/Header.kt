package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.HeaderModel
import kotlinx.serialization.Serializable


@Serializable
data class Header(
  @SerialName("id")
  val id: String? = null,
  @SerialName("uid")
  val uid: String? = null,
  @SerialName("season")
  val season: GameDetailsSeason = GameDetailsSeason(),
  @SerialName("timeValid")
  val timeValid: Boolean? = null,
  @SerialName("competitions")
  val competitions: List<GameDetailsCompetitions> = listOf(),
  @SerialName("week")
  val week: Int? = null,
  @SerialName("league")
  val league: GameDetailsLeague = GameDetailsLeague(),
  @SerialName("gameNote")
  val gameNote: String? = null,


  )





fun Header.asDomain(): HeaderModel {
  return HeaderModel(
    id = id ?: "",
    uid = uid ?: "",
    timeValid = timeValid ?: false,
    competitions = competitions.map { it.asDomain() },
    week = week ?: 0,
    league = league.asDomain() ,
    gameNote = gameNote ?: "",
  )
}