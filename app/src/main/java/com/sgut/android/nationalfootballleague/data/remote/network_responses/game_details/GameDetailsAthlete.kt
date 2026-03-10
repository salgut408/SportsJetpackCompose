package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BatsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel4
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ThrowModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsAthlete(

    @SerialName("id")
    val id: String? = null,
    @SerialName("uid")
    val uid: String? = null,
    @SerialName("guid")
    val guid: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("fullName")
    val fullName: String? = null,
    @SerialName("displayName")
    val displayName: String? = null,
    @SerialName("shortName")
    val shortName: String? = null,
    @SerialName("headshot")
    val headshot: GameDetailsHeadshot? = GameDetailsHeadshot(),
    @SerialName("jersey")
    val jersey: String? = null,
    @SerialName("position")
    val position: GameDetailsPosition? = GameDetailsPosition(),

    )

@Serializable
data class GameDetailsAthlete4(

    @SerialName("id")
    val id: String? = null,
    @SerialName("uid")
    val uid: String? = null,
    @SerialName("guid")
    val guid: String? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("fullName")
    val fullName: String? = null,
    @SerialName("displayName")
    val displayName: String? = null,
    @SerialName("shortName")
    val shortName: String? = null,
    @SerialName("headshot")
    val headshot: String? = "",
    @SerialName("jersey")
    val jersey: String? = null,
    @SerialName("position")
    val position: GameDetailsPosition? = GameDetailsPosition(),
)

fun GameDetailsAthlete4.asDomain(): GameDetailsAthleteDetailsModel4 {
    return GameDetailsAthleteDetailsModel4(
        id = id ?: "",
        uid = uid ?: "",
        lastName = lastName ?: "",
        fullName = fullName ?: "",
        displayName = displayName ?: "",
        shortName = shortName ?: "",
        headshot = headshot ?: "",
        jersey = jersey ?: "",
        position = position?.asDomain(),
    )
}




fun GameDetailsAthlete.asDomain(): GameDetailsAthleteDetailsModel {
    return GameDetailsAthleteDetailsModel(
        id = id ?: "",
        uid = uid ?: "",
        lastName = lastName ?: "",
        fullName = fullName ?: "",
        displayName = displayName ?: "",
        shortName = shortName ?: "",
        headshot = headshot?.asDomain(),
        jersey = jersey ?: "",
        position = position?.asDomain(),
//        throws = throws.asDomain(),
//        bats = bats.asDomain()
    )
}

@Serializable
data class Bats(
    @SerialName("type")
    val type: String = "",
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("displayValue")
    val displayValue: String = "",
)

fun Bats.asDomain(): BatsModel {
return BatsModel(
    type = type,
    abbreviation = abbreviation,
    displayValue = displayValue,
)
}



@Serializable
data class Throw(
  @SerialName("type")
  val type: String = "",
  @SerialName("abbreviation")
  val abbreviation: String = "",
  @SerialName("displayValue")
  val displayValue: String = "",
  )

fun Throw.asDomain(): ThrowModel {
    return ThrowModel(
        type = type,
        abbreviation = abbreviation,
        displayValue = displayValue,
    )
}