package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.VenueModel
import kotlinx.serialization.Serializable


@Serializable
data class Venue3 (

  @SerialName("fullName" )
  val fullName : String  = "",
  @SerialName("address"  )
  val address  : Address3? = Address3(),
  @SerialName("images"  )
  val images3: List<Images3> = listOf()

)

fun Venue3.asDomain(): VenueModel {
  return VenueModel(
    fullName = fullName,
    address = address?.asDomain(),
    images3 = images3
  )
}