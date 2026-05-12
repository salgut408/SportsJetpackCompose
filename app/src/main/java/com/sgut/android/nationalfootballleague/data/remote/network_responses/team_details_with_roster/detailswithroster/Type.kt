package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.CompetitionTypeModel
import kotlinx.serialization.Serializable


@Serializable
data class Type3(

  @SerialName("id")
  val id: String = "",
  @SerialName("name")
  val name: String = "",
  @SerialName("state")
  val state: StatusState = StatusState.PRE,
  @SerialName("completed")
  val completed: Boolean = false,
  @SerialName("description")
  val description: String = "",
  @SerialName("detail")
  val detail: String = "",
  @SerialName("shortDetail")
  val shortDetail: String = "",

  )

fun Type3.asDomain(): CompetitionTypeModel {
  return  CompetitionTypeModel(
    id = id,
    state = state,
    completed = completed,
    description = description,
    detail = detail,
    shortDetail = shortDetail
  )
}