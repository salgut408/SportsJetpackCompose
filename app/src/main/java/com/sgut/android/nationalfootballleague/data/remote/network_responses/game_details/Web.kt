package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.Leagues
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Web (

  @SerialName("leagues" ) var leagues : Leagues? = Leagues()

)