package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsInjuriesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.InjTeamModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.InjuriesItemModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsInjuries(
  @SerialName("team")
  val team: InjTeam = InjTeam(),
  @SerialName("injuries")
  val injuries: List<InjuriesItem> = listOf(),
  )
fun GameDetailsInjuries.asDomain(): GameDetailsInjuriesListModel {
  return GameDetailsInjuriesListModel(
    team = team.asDomain(),
    injuries = injuries.map { it.asDomain() }
  )
}

@Serializable
data class InjTeam(
  @SerialName("id")
  val id: String = "",
  @SerialName("uid")
  val uid: String = "",
  @SerialName("displayName")
  val displayName: String = "",
  @SerialName("logo")
  val logo: String = "",
  )

fun InjTeam.asDomain(): InjTeamModel {
  return InjTeamModel(
    id = id,
    displayName = displayName,
    logo = logo
  )
}

@Serializable
data class InjuriesItem(
  @SerialName("status")
  val status: String = "",
  @SerialName("date")
  val date: String = "",
  @SerialName("athlete")
  val athlete: GameDetailsAthlete = GameDetailsAthlete(),
  @SerialName("type")
  val type: GameDetailsType = GameDetailsType(),
  )

fun InjuriesItem.asDomain(): InjuriesItemModel {
  return InjuriesItemModel(
    status = status,
    date = date,
    athlete = athlete.asDomain(),
    type = type.asDomain()
  )
}