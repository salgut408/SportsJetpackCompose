package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameInfoModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.OfficialModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.OfficialsPositionModel
import kotlinx.serialization.Serializable


@Serializable
data class GameInfo (

  @SerialName("venue"   )
  val venue   : GameDetailsVenue   = GameDetailsVenue(),
  @SerialName("weather" )
  val weather : Weather = Weather(),
  @SerialName("attendance" )
val attendance : Int = 0,
  @SerialName("officials")
  val officials: List<Official> = listOf(),

  )


@Serializable
data class Official(
  @SerialName("displayName")
  val displayName: String? = "",
  @SerialName("order")
  val order: Int? = 0,
  @SerialName("position")
  val position: OfficialsPosition = OfficialsPosition()
)

fun Official.asDomain(): OfficialModel {
  return OfficialModel(
    displayName = displayName,
    order = order,
    position = position.asDomain()
  )
}





@Serializable
data class OfficialsPosition(
  @SerialName("displayName")
  val displayName: String? = "",
  @SerialName("id")
  val id: String? = "",
  @SerialName("name")
  val name: String? = ""
)

fun OfficialsPosition.asDomain(): OfficialsPositionModel {
  return OfficialsPositionModel(
    displayName = displayName,
  id = id,
    name = name
    )
}

fun GameInfo.asDomain(): GameInfoModel {
  return GameInfoModel(
    venue = venue.asDomain(),
    weather = weather.asDomain(),
    attendance = attendance,
    officials = officials.map { it.asDomain() }
  )



}