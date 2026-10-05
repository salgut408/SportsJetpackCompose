package com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.data.db.sport.SportDbObj
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.LeagueModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel
import kotlinx.serialization.Serializable


@Serializable
data class Sports(

  @SerialName("id")
  val id: String = "",

  @SerialName("uid")
  val uid: String = "",
  @SerialName("name")
  val name: String = "",
  @SerialName("slug")
  val slug: String = "",
  @SerialName("leagues")
  val leagues: List<Leagues>?,

  )

fun Sports.toDomain(): SportModel {
  return SportModel(
    id = id,
    uid = uid,
    name = name,
    slug = slug,
    league = leagues?.get(0)?.toDomain() ?: LeagueModel(),
  )
}

fun Sports.toDbModel(): SportDbObj {
  return SportDbObj(
    id = id,
    uid = uid,
    name = name,
    slug = slug,
  )
}