package com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list

import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomainModel
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.LogosModel
import kotlinx.serialization.Serializable


@Serializable
data class Logos (

  @SerialName("href"   ) val href   : String?           = null,
  @SerialName("alt"    ) val alt    : String?           = null,
  @SerialName("rel"    ) val rel    : List<String> = listOf(),
  @SerialName("width"  ) val width  : Int?              = null,
  @SerialName("height" ) val height : Int?              = null

)


fun Logos.asDomainModel(): LogosModel{
  return LogosModel(
    href = href ?: "",
    alt = alt ?: "",
    rel = rel,
    width = width ?: 0,
    height = height ?: 0
  )
}