package com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.CompetitionTypeModel
import kotlinx.serialization.Serializable


@Serializable
data class TypeScoreboard(

  @SerialName("id")
  val id: String = "",
  @SerialName("name")
  val name: String = "",
  @SerialName("state")
  val state: StatusState? = StatusState.PRE,
  @SerialName("completed")
  val completed: Boolean = false,
  @SerialName("description")
  val description: String = "",
  @SerialName("detail")
  val detail: String = "",
  @SerialName("shortDetail")
  val shortDetail: String = "",
  )

@Serializable
enum class StatusState {
    @SerialName("post")
    POST,
    @SerialName("in")
    IN,
    @SerialName("pre")
    PRE
}

fun TypeScoreboard.asDomain(): CompetitionTypeModel {
    return CompetitionTypeModel(
        id = id,
        name = name,
        state = state,
        completed = completed,
        description = description,
        detail = detail,
        shortDetail = shortDetail,
    )
}