package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.CompetitionsEventModel
import kotlinx.serialization.Serializable


@Serializable
data class Competitions3(

  @SerialName("id")
  val id: String? = null,
  @SerialName("date")
  val date: String? = null,
  @SerialName("attendance")
  val attendance: Int? = null,
  @SerialName("type")
  val type: Type3? = Type3(),
  @SerialName("timeValid")
  val timeValid: Boolean? = null,
  @SerialName("neutralSite")
  val neutralSite: Boolean? = null,
  @SerialName("boxscoreAvailable")
  val boxscoreAvailable: Boolean? = null,
  @SerialName("ticketsAvailable")
  val ticketsAvailable: Boolean? = null,
  @SerialName("venue")
  val venue: Venue3 = Venue3(),
  @SerialName("competitors")
  val competitors: List<Competitors3> = listOf(),
//  @SerialName("notes")
//  val notes: List<String> = listOf(),
  @SerialName("tickets")
  val tickets: List<Tickets3> = listOf(),
  @SerialName("status")
  val status: Status3? = Status3(),
  )
fun Competitions3.asDomain(): CompetitionsEventModel {
  return CompetitionsEventModel(
    id = id ?: "",
    date = date ?: "",
    attendance = attendance ?: 0,
    type = type?.asDomain(),
    timeValid = timeValid,
    boxscoreAvailable = boxscoreAvailable,
    venue = venue.asDomain(),
    competitors = competitors.map { it.asDomain() },
    status = status?.asDomain(),
    tickets = tickets.map { it.asDomain() }
  )
}