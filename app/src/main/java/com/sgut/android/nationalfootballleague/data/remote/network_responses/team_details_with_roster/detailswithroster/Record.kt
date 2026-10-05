package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
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