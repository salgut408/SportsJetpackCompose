package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsEventModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsOpponentModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsEvents(
    @SerialName("id")
    val id: String = "",
//  @SerialName("links" ) var links : List<EventLinks> = listOf(),
    @SerialName("atVs")
    val atVs: String = "",
    @SerialName("gameDate")
    val gameDate: String = "",
    @SerialName("score")
    val score: String = "",
    @SerialName("homeTeamScore")
    val homeTeamScore: String = "",
    @SerialName("awayTeamScore")
    val awayTeamScore: String = "",
    @SerialName("gameResult")
    val gameResult: String = "",
    @SerialName("opponent") val
    opponent: Opponent = Opponent(),
    @SerialName("opponentLogo") val
    opponentLogo: String = "",
    @SerialName("leagueName") val
    leagueName: String = "",
    @SerialName("leagueAbbreviation")
    val leagueAbbreviation: String = "",

    )

fun GameDetailsEvents.asDomain(): GameDetailsEventModel {
    return GameDetailsEventModel(
        id = id,
        atVs = atVs,
        gameDate = gameDate,
        score = score,
        homeTeamScore = homeTeamScore,
        awayTeamScore = awayTeamScore,
        gameResult = gameResult,
        opponent = opponent.asDomain(),
        leagueName = leagueName,
        leagueAbbreviation = leagueAbbreviation
    )
}

@Serializable
data class Opponent(
    @SerialName("id")
    val id: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("logo")
    val logo: String = "",
    )
fun Opponent.asDomain(): GameDetailsOpponentModel {
    return GameDetailsOpponentModel(
        id = id,
        displayName = displayName,
        abbreviation = abbreviation,
        logo = logo
    )
}

@Serializable
data class EventLinks(
    @SerialName("href") var href: String = "",
    @SerialName("text") var text: String = "",
)