package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.RecordModel
import kotlinx.serialization.Serializable


@Serializable
data class Record3(
  @SerialName("items")
  val recordItems: List<Items3>? = listOf(),
  )

fun Record3?.asDomain(): RecordModel {
  return RecordModel(
    recordItems = this?.recordItems?.map { it.asRecordItemModel() } ?: listOf()
  )
}