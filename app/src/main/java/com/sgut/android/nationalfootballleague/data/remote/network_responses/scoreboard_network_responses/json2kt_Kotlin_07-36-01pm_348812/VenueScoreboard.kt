package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardVenueModel
import kotlinx.serialization.Serializable


@Serializable
data class VenueScoreboard (

  @SerialName("id" )
  val id : String? = null

)

fun VenueScoreboard.asDomain(): ScoreboardVenueModel {
  return ScoreboardVenueModel(
    id = id ?: ""
  )
}