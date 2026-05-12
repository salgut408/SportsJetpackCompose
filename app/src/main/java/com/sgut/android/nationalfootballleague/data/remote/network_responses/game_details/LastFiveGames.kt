package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.LastFiveGamesModel
import kotlinx.serialization.Serializable


@Serializable
data class LastFiveGames(

  @SerialName("team")
  val team: GameDetailsTeam = GameDetailsTeam(),
  @SerialName("events")
  val lastEvents: List<GameDetailsEvents> = listOf(),

  )

fun LastFiveGames.asDomain(): LastFiveGamesModel {
  return LastFiveGamesModel(
    team = team.asDomain(),
    lastEvents = lastEvents.map { it.asDomain() }
  )
}