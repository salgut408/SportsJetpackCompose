package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.FootballPlayModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.PreviousModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ScoringPlayModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ScoringTypeModel
import kotlinx.serialization.Serializable


@Serializable
data class Previous(

    @SerialName("id")
    val id: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("team")
    val team: Team? = Team(),
    @SerialName("start")
    val start: Start? = Start(),
    @SerialName("end")
    val end: End? = End(),
    @SerialName("timeElapsed")
    val timeElapsed: TimeElapsed? = TimeElapsed(),
    @SerialName("yards")
    val yards: Int? = null,
    @SerialName("isScore")
    val isScore: Boolean? = null,
    @SerialName("offensivePlays")
    val offensivePlays: Int? = null,
    @SerialName("result")
    val result: String? = null,
    @SerialName("shortDisplayResult")
    val shortDisplayResult: String? = null,
    @SerialName("displayResult")
    val displayResult: String? = null,
    @SerialName("plays")
    val plays: List<Plays> = listOf(),

    )

fun Previous.asDomain(): PreviousModel {
    return PreviousModel(
        id = id ?: "",
        description = description ?: "",
        team = team?.asDomainModel(),
        timeElapsed = timeElapsed?.displayValue ?: "",
        yards = yards ?: 0,
        isScore = isScore ?: false,
        offensivePlays = offensivePlays ?: 0,
        result = result ?: "",
        displayResult = displayResult ?: "",
        shortDisplayResult = shortDisplayResult ?: "",
        plays = plays.map { it.asDomain() }
    )
}

@Serializable
data class TimeElapsed(

    @SerialName("displayValue")
    val displayValue: String? = null,

    )


@Serializable
data class Start(

    @SerialName("period")
    val period: Period? = Period(),
    @SerialName("clock")
    val clock: Clock? = Clock(),
    @SerialName("yardLine")
    val yardLine: Int? = null,
    @SerialName("text")
    val text: String? = null,

    )

@Serializable
data class Period(

    @SerialName("type") val type: String? = null,
    @SerialName("number") val number: Int? = null,

    )

@Serializable
data class Clock(

    @SerialName("displayValue") val displayValue: String? = null,

    )

@Serializable
data class End(

    @SerialName("period") val period: Period? = Period(),
    @SerialName("clock") val clock: Clock? = Clock(),
    @SerialName("yardLine") val yardLine: Int? = null,
    @SerialName("text") val text: String? = null,

    )

@Serializable
data class Plays(

    @SerialName("id")
    val id: String? = null,
    @SerialName("sequenceNumber")
    val sequenceNumber: String? = null,
    @SerialName("type")
    val type: GameDetailsType? = GameDetailsType(),
    @SerialName("text")
    val text: String? = null,
    @SerialName("awayScore")
    val awayScore: Int? = null,
    @SerialName("homeScore")
    val homeScore: Int? = null,
    @SerialName("period")
    val period: Period? = Period(),
    @SerialName("clock")
    val clock: Clock? = Clock(),
    @SerialName("scoringPlay")
    val scoringPlay: Boolean? = null,
    @SerialName("priority")
    val priority: Boolean? = null,
    @SerialName("modified")
    val modified: String? = null,
    @SerialName("wallclock")
    val wallclock: String? = null,
    @SerialName("start")
    val start: Start? = Start(),
    @SerialName("end")
    val end: End? = End(),
    @SerialName("statYardage")
    val statYardage: Int? = null,

    )
fun Plays.asDomain(): FootballPlayModel {
    return FootballPlayModel(
        type = type?.asDomain(),
        text = text ?: "",
        awayScore = awayScore ?: 0,
        homeScore = homeScore ?: 0,
        period = period?.number ?: 0,
        clock = clock?.displayValue ?: "",
        scoringPlay = scoringPlay ?: false,
        wallclock = wallclock ?: "",
        statYardage = statYardage ?: 0
    )
}

@Serializable
data class ScoringPlays(

    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: GameDetailsType? = GameDetailsType(),
    @SerialName("text")
    val text: String? = null,
    @SerialName("awayScore")
    val awayScore: Int? = null,
    @SerialName("homeScore")
    val homeScore: Int? = null,
    @SerialName("period")
    val period: Period? = Period(),
    @SerialName("clock")
    val clock: Clock? = Clock(),
    @SerialName("team")
    val team: GameDetailsTeam? = GameDetailsTeam(),
    @SerialName("scoringType")
    val scoringType: ScoringType? = ScoringType(),

    )

fun ScoringPlays.asDomain(): ScoringPlayModel {
    return ScoringPlayModel(
        id = id ?: "",
        type = type?.asDomain() ,
        awayScore = awayScore ?: 0,
        homeScore = homeScore ?: 0,
        period = period?.number ?: 0,
        clock = clock?.displayValue ?: "",
        team = team?.asDomain(),
        scoringType = scoringType?.asDomain()
    )
}

@Serializable
data class ScoringType(

    @SerialName("name")
    val name: String? = null,
    @SerialName("displayName")
    val displayName: String? = null,
    @SerialName("abbreviation")
    val abbreviation: String? = null,

    )
fun ScoringType.asDomain(): ScoringTypeModel {
    return ScoringTypeModel(
        name = name ?: "",
        displayName = displayName ?: "",
        abbreviation = abbreviation ?: ""
    )
}