package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.DefaultScoreboardEventModel
import kotlinx.serialization.Serializable


@Serializable
data class EventScoreboard(

  @SerialName("id")
  val id: String? = null,
  @SerialName("uid")
  val uid: String? = null,
  @SerialName("date")
  val date: String? = null,
  @SerialName("name")
  val name: String? = null,
  @SerialName("shortName")
  val shortName: String? = null,
  @SerialName("competitions")
  val competitions: List<CompetitionScoreboard> = listOf(),
  @SerialName("status")
  val status: StatusScoreboard = StatusScoreboard(),

  )

fun EventScoreboard.asDomain(): DefaultScoreboardEventModel {
  return  DefaultScoreboardEventModel(
    id = id ?: "",
    uid = uid ?: "",
    date = date ?: "",
    name = name ?: "",
    shortName = shortName ?: "",
    competitions = competitions.map { it.asDomain() },
    status = status.asDomain()
  )
}