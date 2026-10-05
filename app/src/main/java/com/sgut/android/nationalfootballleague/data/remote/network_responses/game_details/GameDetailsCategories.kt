package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsCategories (

  @SerialName("id"          ) var id          : Int?    = null,
  @SerialName("description" ) var description : String? = null,
  @SerialName("type"        ) var type        : String? = null,
  @SerialName("sportId"     ) var sportId     : Int?    = null,
  @SerialName("leagueId"    ) var leagueId    : Int?    = null,
  @SerialName("league"      ) var league      : GameDetailsLeague? = GameDetailsLeague(),
  @SerialName("uid"         ) var uid         : String? = null

)