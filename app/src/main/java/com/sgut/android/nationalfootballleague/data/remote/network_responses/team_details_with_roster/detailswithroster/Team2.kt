package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailsFranchiseModel
import kotlinx.serialization.Serializable


@Serializable
data class Team3 (

  @SerialName("id"               ) var id               : String              = "",
  @SerialName("uid"              ) var uid              : String             = "",
  @SerialName("slug"             ) var slug             : String              = "",
  @SerialName("location"         ) var location         : String              = "",
  @SerialName("name"             ) var name             : String              = "",
  @SerialName("nickname"         ) var nickname         : String              = "",
  @SerialName("abbreviation"     ) var abbreviation     : String              = "",
  @SerialName("displayName"      ) var displayName      : String              = "",
  @SerialName("shortDisplayName" ) var shortDisplayName : String              = "",
  @SerialName("color"            ) var color            : String              = "",
  @SerialName("alternateColor"   ) var alternateColor   : String              = "FFFF",
  @SerialName("isActive"         ) var isActive         : Boolean?             = null,
  @SerialName("logos"            ) var logos            : List<Logos3>     = listOf(),
  @SerialName("record"           ) var record           : Record3?              = Record3(),
  @SerialName("athletes"         ) var athletes         : List<Athletes>  = listOf(),
  @SerialName("groups"           ) var groups           : Groups3?              = Groups3(),
  @SerialName("links"            ) var links            : List<Links3>     = listOf(),
  @SerialName("franchise"        ) var franchise        : Franchise3?           = Franchise3(),
  @SerialName("nextEvent"        ) var nextEvent        : List<NextEvent3> = listOf(),
  @SerialName("standingSummary"  ) var standingSummary  : String              = ""

)

fun Team3.asDomain(): FullTeamDetailWithRosterModel {
  return FullTeamDetailWithRosterModel(
    id = id,
    uid = uid,
    slug = slug,
    location = location,
    name = name,
    abbreviation = abbreviation,
    displayName = displayName,
    shortDisplayName = shortDisplayName,
    color = color,
    alternateColor = alternateColor,
    logos = logos.map { it.asDomain() },
    record = record.asDomain(),
    franchise = franchise?.asDomain() ?: FullTeamDetailsFranchiseModel(),
    nickname = nickname,
    athletes = athletes.map { it.asDomain() },
    nextEvent = nextEvent.map { it.asDomain() },
    standingSummary = standingSummary
  )
}


