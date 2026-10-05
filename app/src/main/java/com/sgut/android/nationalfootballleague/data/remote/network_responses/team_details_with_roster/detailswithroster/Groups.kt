package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Groups3 (

  @SerialName("id"           ) var id           : String?  = null,
  @SerialName("parent"       ) var parent       : Parent3?  = Parent3(),
  @SerialName("isConference" ) var isConference : Boolean? = null

)