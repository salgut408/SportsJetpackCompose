package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.StatsItemModel
import kotlinx.serialization.Serializable


@Serializable
data class Stats3(
  @SerialName("name") var name: String? = null,
  @SerialName("value") var value: Float? = null,
  )

fun Stats3.asStatsItemModel() : StatsItemModel {
  return StatsItemModel(
    name = name ?: "",
    value = value ?: 0f
  )
}

fun Stats3.toDomainStatsModelList(initial: List<Stats3>): List<StatsItemModel> {
  return initial.map { it.asStatsItemModel() }
}


