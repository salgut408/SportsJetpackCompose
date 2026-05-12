package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.DayComm
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.LeagueCommon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = ScoreboardDataSerializer::class)
abstract class ScoreboardData {
    abstract val league: List<LeagueCommon>
    abstract val events: List<EventData>
    abstract val day: DayComm
}

@Serializable
data class DefaultScoreboardData(
    @SerialName("leagues")
    override val league: List<LeagueCommon> = listOf(),
    @SerialName("events")
    override val events: List<DefaultEvent> = listOf(),
    @SerialName("day")
    override val day: DayComm = DayComm()
) : ScoreboardData()

@Serializable
data class GolfScoreboardData(
    @SerialName("events")
    override val events: List<GolfEvent> = listOf(),
    @SerialName("leagues")
    override val league: List<LeagueCommon> = listOf(),
    @SerialName("day")
    override val day: DayComm = DayComm()
) : ScoreboardData()

@Serializable
data class BaseballScoreboard(
    @SerialName("events")
    override val events: List<BaseballEvent> = listOf(),
    @SerialName("leagues")
    override val league: List<LeagueCommon> = listOf(),
    @SerialName("day")
    override val day: DayComm = DayComm()
) : ScoreboardData()

@Serializable
data class TennisScoreboard(
    @SerialName("events")
    override val events: List<TennisEvent> = listOf(),
    @SerialName("leagues")
    override val league: List<LeagueCommon> = listOf(),
    @SerialName("day")
    override val day: DayComm = DayComm()
) : ScoreboardData()

@Serializable
data class SoccerScoreboard(
    @SerialName("events")
    override val events: List<SoccerEvent> = listOf(),
    @SerialName("leagues")
    override val league: List<LeagueCommon> = listOf(),
    @SerialName("day")
    override val day: DayComm = DayComm()
) : ScoreboardData()

@Serializable
data class MmaScoreboard(
    @SerialName("events")
    override val events: List<MmaEvent> = listOf(),
    @SerialName("leagues")
    override val league: List<LeagueCommon> = listOf(),
    @SerialName("day")
    override val day: DayComm = DayComm()
) : ScoreboardData()

@Serializable
data class RacingScoreboard(
    @SerialName("leagues")
    override val league: List<LeagueCommon> = listOf(),
    @SerialName("events")
    override val events: List<RacingEvent> = listOf(),
    @SerialName("day")
    override val day: DayComm = DayComm()
) : ScoreboardData()