package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.LogosModel
import kotlinx.serialization.Serializable


@Serializable
data class LogosScoreboard (

  @SerialName("href"        ) var href        : String?           = null,
  @SerialName("width"       ) var width       : Int?              = null,
  @SerialName("height"      ) var height      : Int?              = null,
  @SerialName("alt"         ) var alt         : String?           = null,
  @SerialName("rel"         ) var rel         : ArrayList<String> = arrayListOf(),
  @SerialName("lastUpdated" ) var lastUpdated : String?           = null

)

fun LogosScoreboard.asDomain() : LogosModel {
  return LogosModel(
    href = href ?: "",
    alt =  alt ?: "",
    rel = rel,
    width = width ?: 0,
    height = height ?: 0

  )
}