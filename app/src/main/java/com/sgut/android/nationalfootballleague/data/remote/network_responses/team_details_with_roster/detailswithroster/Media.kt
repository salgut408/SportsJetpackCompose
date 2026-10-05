package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Media (

  @SerialName("shortName" ) var shortName : String? = null

)