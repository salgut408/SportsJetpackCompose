package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.SeatSituationModel
import kotlinx.serialization.Serializable


@Serializable
data class SeatSituation(

  @SerialName("opponentTeamName")
  val opponentTeamName: String? = null,
  @SerialName("currentTeamName")
  val currentTeamName: String? = null,
  @SerialName("venueName")
  val venueName: String? = null,
  @SerialName("summary")
  val summary: String? = null,
  @SerialName("date")
  val date: String? = null,
  @SerialName("dateShort")
  val dateShort: String? = null,
  @SerialName("dateDay")
  val dateDay: String? = null,
  @SerialName("homeAway")
  val homeAway: String? = null,
  @SerialName("eventLink")
  val eventLink: String? = null,
  @SerialName("venueLink")
  val venueLink: String? = null,
  @SerialName("genericLink")
  val genericLink: String? = null,
  @SerialName("teamLink")
  val teamLink: String? = null,

  )

fun SeatSituation.asDomain(): SeatSituationModel {
  return SeatSituationModel(
    opponentTeamName = opponentTeamName ?: "",
    currentTeamName = currentTeamName ?: "",
    venueName = venueName ?: "",
    summary = summary ?: "",
    date = date ?: "",
    dateDay = dateDay ?: "",
    dateShort = dateShort ?: "",
    homeAway = homeAway ?: "",
    eventLink = eventLink ?: "",
    venueLink = venueLink ?: "",
    genericLink = genericLink ?: "",
    teamLink = teamLink ?: "",
  )
}