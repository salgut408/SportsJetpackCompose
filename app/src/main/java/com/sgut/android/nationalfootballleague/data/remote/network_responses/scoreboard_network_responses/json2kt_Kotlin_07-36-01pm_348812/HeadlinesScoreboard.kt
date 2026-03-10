package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardHeadlineModel
import kotlinx.serialization.Serializable


@Serializable
data class HeadlinesScoreboard(

  @SerialName("description")
  val description: String? = null,
  @SerialName("type")
  val type: String? = null,
  @SerialName("shortLinkText")
  val shortLinkText: String? = null,

  )

fun HeadlinesScoreboard.asDomain(): ScoreboardHeadlineModel {
  return ScoreboardHeadlineModel(
    description = description ?: "",
    type = type ?: "",
    shortLinkText = shortLinkText ?: ""
  )
}