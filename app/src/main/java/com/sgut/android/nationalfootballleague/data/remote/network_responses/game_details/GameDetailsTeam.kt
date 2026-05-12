package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsTeamInfoModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsTeam(

    @SerialName("id")
    val id: String? = null,
    @SerialName("uid")
    val uid: String? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("abbreviation")
    val abbreviation: String? = null,
    @SerialName("displayName")
    val displayName: String? = null,
    @SerialName("color")
    val color: String? = null,
    @SerialName("alternateColor")
    val alternateColor: String? = null,
    @SerialName("logos")
    val logos: List<GameDetailsLogos> = listOf(),
    @SerialName("logo")
    val logo: String = "",
    @SerialName("links")
    val links: List<GameDetailsLinks> = listOf(),
    @SerialName("record")
    val record: List<GameDetailsRecord> = listOf(),


    )

fun GameDetailsTeam.asDomain(): GameDetailsTeamInfoModel {
    return GameDetailsTeamInfoModel (
        id = id ?: "",
        uid = uid ?: "",
        location = location ?: "",
        name = name ?: "",
        abbreviation = abbreviation ?: "",
        displayName = displayName ?: "",
        color = color ?: "",
        alternateColor = alternateColor ?: "",
        logos = logos.map { it.asDomainLogo() },
        logo = logo,
        record = record.map { it.asDomain() }
            )
}