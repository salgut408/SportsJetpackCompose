package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BaseballPitchCountModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BaseballPlayModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BaseballPlayPeriodModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BaseballPlayTypeModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseballPlay(
    @SerialName("id") val id: String = "",
    @SerialName("sequenceNumber") val sequenceNumber: String = "",
    @SerialName("type") val type: BaseballPlayType = BaseballPlayType(),
    @SerialName("text") val text: String = "",
    @SerialName("awayScore") val awayScore: Int = 0,
    @SerialName("homeScore") val homeScore: Int = 0,
    @SerialName("period") val period: BaseballPlayPeriod = BaseballPlayPeriod(),
    @SerialName("scoringPlay") val scoringPlay: Boolean = false,
    @SerialName("scoreValue") val scoreValue: Int = 0,
    @SerialName("team") val team: BaseballPlayTeam = BaseballPlayTeam(),
    @SerialName("wallclock") val wallclock: String = "",
    @SerialName("atBatId") val atBatId: String = "",
    @SerialName("summaryType") val summaryType: String = "",
    @SerialName("pitchCount") val pitchCount: BaseballPitchCount? = null,
    @SerialName("resultCount") val resultCount: BaseballPitchCount? = null,
    @SerialName("outs") val outs: Int = 0,
)

@Serializable
data class BaseballPlayType(
    @SerialName("id") val id: String = "",
    @SerialName("text") val text: String = "",
    @SerialName("type") val type: String = "",
)

@Serializable
data class BaseballPlayPeriod(
    @SerialName("type") val type: String = "",
    @SerialName("number") val number: Int = 0,
    @SerialName("displayValue") val displayValue: String = "",
)

@Serializable
data class BaseballPlayTeam(
    @SerialName("id") val id: String = "",
)

@Serializable
data class BaseballPitchCount(
    @SerialName("balls") val balls: Int = 0,
    @SerialName("strikes") val strikes: Int = 0,
)

fun BaseballPlay.asDomain(): BaseballPlayModel = BaseballPlayModel(
    id = id,
    sequenceNumber = sequenceNumber,
    type = type.asDomain(),
    text = text,
    awayScore = awayScore,
    homeScore = homeScore,
    period = period.asDomain(),
    scoringPlay = scoringPlay,
    scoreValue = scoreValue,
    teamId = team.id,
    wallclock = wallclock,
    atBatId = atBatId,
    summaryType = summaryType,
    pitchCount = pitchCount?.asDomain(),
    resultCount = resultCount?.asDomain(),
    outs = outs,
)

fun BaseballPlayType.asDomain(): BaseballPlayTypeModel = BaseballPlayTypeModel(
    id = id,
    text = text,
    type = type,
)

fun BaseballPlayPeriod.asDomain(): BaseballPlayPeriodModel = BaseballPlayPeriodModel(
    type = type,
    number = number,
    displayValue = displayValue,
)

fun BaseballPitchCount.asDomain(): BaseballPitchCountModel = BaseballPitchCountModel(
    balls = balls,
    strikes = strikes,
)