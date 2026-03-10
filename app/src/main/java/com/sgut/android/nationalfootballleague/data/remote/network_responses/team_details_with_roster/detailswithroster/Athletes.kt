package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.AthletesRosterModel
import kotlinx.serialization.Serializable


@Serializable
data class Athletes(
  @SerialName("id")
  val id: String = "",
  @SerialName("uid")
  val uid: String = "",
  @SerialName("guid")
  val guid: String = "",
  @SerialName("type")
  val type: String = "",
  @SerialName("alternateIds")
  val alternateIds: AlternateIds? = AlternateIds(),
  @SerialName("firstName")
  val firstName: String = "",
  @SerialName("lastName")
  val lastName: String = "",
  @SerialName("fullName")
  val fullName: String = "",
  @SerialName("displayName")
  val displayName: String = "",
  @SerialName("shortName")
  val shortName: String = "",
  @SerialName("weight")
  val weight: Double = 0.0,
  @SerialName("displayWeight")
  val displayWeight: String = "",
  @SerialName("height")
  val height: Double = 0.0,
  @SerialName("displayHeight")
  val displayHeight: String = "",
  @SerialName("age")
  val age: Int? = null,
  @SerialName("dateOfBirth")
  val dateOfBirth: String = "",
  @SerialName("debutYear")
  val debutYear: Int? = null,
  @SerialName("birthPlace")
  val birthPlace: BirthPlace? = BirthPlace(),
  @SerialName("slug")
  val slug: String = "",
  @SerialName("headshot")
  val headshot: Headshot = Headshot(),
  @SerialName("jersey")
  val jersey: String = "",
  @SerialName("position")
  val position: Position = Position(),
  @SerialName("injuries")
  val injuries: List<Injury>? = listOf(),
  @SerialName("linked")
  val linked: Boolean? = null,
  @SerialName("experience")
  val experience: Experience? = Experience(),
  @SerialName("active")
  val active: Boolean? = null,
  @SerialName("draft")
  val draft: Draft = Draft(),
  @SerialName("flag")
  val flag: Flag? = Flag(),
)

@Serializable
data class Flag(
  @SerialName("href")
  val href: String? = "",
  )

@Serializable
data class Injury(
    @SerialName("shortComment")
    val shortComment: String? = null,
    @SerialName("longComment")
    val longComment: String? = null,
    @SerialName("status")
    val injuryStatus: String? = null,
    @SerialName("details")
    val detail: Details? = null,
    )

@Serializable
data class Details(
    @SerialName("type")
    val type: String? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("side")
    val side: String? = null,
    @SerialName("detail")
    val detail: String? = null,
    @SerialName("returnDate")
    val returnDate: String? = null,
)

fun Athletes.asGameDetailsAthlete(): GameDetailsAthleteDetailsModel {
    return GameDetailsAthleteDetailsModel(
        id = id,
        uid = uid,
        lastName = lastName,
        fullName = fullName,
        shortName = shortName,
        headshot = headshot.asDomain(),
        jersey = jersey,
        position = position.asDomain()

    )
}

fun Athletes.asDomain(): AthletesRosterModel {
    return AthletesRosterModel(
        id = id,
        uid = uid,
        guid = guid,
        type = type,
        firstName = firstName,
        lastName = lastName,
        fullName = fullName,
        displayName = displayName,
        shortName = shortName,
        displayWeight = displayWeight,
        displayHeight = displayHeight,
        age = age ?: 0,
        dateOfBirth = dateOfBirth,
        birthPlace = birthPlace,
        slug = slug,
        headshot = headshot,
        jersey = jersey,
        position = position,
        injuries = injuries,
        experience = experience,
        draft = draft,
        active = active,
        flag = flag


    )
}


























