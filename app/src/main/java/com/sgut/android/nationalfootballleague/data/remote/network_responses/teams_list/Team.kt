package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamModel
import kotlinx.serialization.Serializable


@Serializable
data class Team(

  @SerialName("id")
  var id: String = "",
  @SerialName("uid")
  val uid: String = "",
  @SerialName("slug")
  val slug: String = "",
  @SerialName("abbreviation")
  val abbreviation: String = "",
  @SerialName("displayName")
  val displayName: String = "",
  @SerialName("shortDisplayName")
  val shortDisplayName: String = "",
  @SerialName("name")
  val name: String = "",
  @SerialName("nickname")
  val nickname: String = "",
  @SerialName("location")
  val location: String = "",
  @SerialName("color")
  val color: String = "",
  @SerialName("alternateColor")
  val alternateColor: String = "",
  @SerialName("isActive")
  val isActive: Boolean? = null,
  @SerialName("isAllStar")
  val isAllStar: Boolean? = null,
  @SerialName("logos")
  val logos: List<Logos> = listOf(),


  )

fun Team.asDomainModel(): TeamModel{
  return TeamModel(
    id = id,
    uid = uid,
    slug = slug,
    abbreviation = abbreviation,
    displayName = displayName,
    shortDisplayName = shortDisplayName,
    name = name,
    nickname = nickname,
    location = location,
    color = color,
    alternateColor = alternateColor,
    logos =  logos.getOrNull(0)?.href.toString() ?: ""
  )


}

fun Team.toDomainModelList(initial: List<Team>): List<TeamModel> {
  return initial.map { it.asDomainModel() }
}