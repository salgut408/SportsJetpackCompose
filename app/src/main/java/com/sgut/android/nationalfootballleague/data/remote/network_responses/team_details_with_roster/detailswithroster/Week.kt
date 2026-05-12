package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.WeekModel
import kotlinx.serialization.Serializable


@Serializable
data class Week3(

  @SerialName("number")
  val number: Int? = null,
  @SerialName("text")
  val text: String? = null,
  )

fun Week3.asDomain(): WeekModel {
  return WeekModel(
    number = number ?: 0,
    text = text ?: ""
  )
}