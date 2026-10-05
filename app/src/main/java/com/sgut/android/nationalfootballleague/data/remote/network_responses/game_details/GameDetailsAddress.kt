package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.AddressModel
import kotlinx.serialization.Serializable


//same as
@Serializable
data class GameDetailsAddress(

  @SerialName("city")
  val city: String = "",
  @SerialName("state")
  val state: String = "",
  @SerialName("zipCode")
  val zipCode: String = "",

  )

fun GameDetailsAddress.asDomain(): AddressModel {
    return AddressModel(
      city = city ,
      state = state ,
      zipCode = zipCode
    )
}