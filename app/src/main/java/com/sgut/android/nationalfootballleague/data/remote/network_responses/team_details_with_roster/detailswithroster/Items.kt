package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.RecordItemModel
import kotlinx.serialization.Serializable


@Serializable
data class Items3 (
  @SerialName("type"    ) val type    : String?          = null,
  @SerialName("summary" ) val summary : String?          = null,
  @SerialName("stats"   ) val stats   : List<Stats3?> = listOf()
)

fun Items3.asRecordItemModel(): RecordItemModel {
  return RecordItemModel(
    type = type ?: "",
    summary = summary ?: "",
    stats = stats.map { it?.asStatsItemModel()!!}
  )
}