package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.StatusCommX
import com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard.Situation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class CompetitionData {
    abstract val competitors: List<CompetitorData>
    abstract val id: String
    abstract val startDate: String
    abstract val status: StatusCommX?
    abstract val uid: String
}

@Serializable
data class DefaultCompetition(
    @SerialName("competitors")
    override val competitors: List<DefaultCompetitor> = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("startDate")
    override val startDate: String = "",
    @SerialName("status")
    override val status: StatusCommX? = StatusCommX(),
    @SerialName("uid")
    override val uid: String = ""
) : CompetitionData()

@Serializable
data class BaseballCompetition(
    @SerialName("competitors")
    override val competitors: List<BaseballCompetitor> = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("situation")
    val situation: Situation? = Situation(),
    @SerialName("outsText")
    val outsText: String? = "",
    @SerialName("startDate")
    override val startDate: String = "",
    @SerialName("status")
    override val status: StatusCommX? = StatusCommX(),
    @SerialName("uid")
    override val uid: String = ""
) : CompetitionData()

@Serializable
data class GolfCompetition(
    override val competitors: List<SingleCompetitor> = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("startDate")
    override val startDate: String = "",
    @SerialName("status")
    override val status: StatusCommX? = StatusCommX(),
    @SerialName("uid")
    override val uid: String = ""
) : CompetitionData()

@Serializable
data class MmaCompetition(
    @SerialName("competitors")
    override val competitors: List<SingleCompetitor> = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("startDate")
    override val startDate: String = "",
    @SerialName("status")
    override val status: StatusCommX = StatusCommX(),
    @SerialName("uid")
    override val uid: String = ""
) : CompetitionData()

@Serializable
data class SoccerCompetition(
    @SerialName("competitors")
    override val competitors: List<DefaultCompetitor> = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("startDate")
    override val startDate: String = "",
    @SerialName("status")
    override val status: StatusCommX = StatusCommX(),
    @SerialName("uid")
    override val uid: String = ""
) : CompetitionData()

@Serializable
data class TennisCompetition(
    @SerialName("competitors")
    override val competitors: List<SingleCompetitor> = listOf(),
    @SerialName("id")
    override val id: String = "",
    @SerialName("major")
    val major: Boolean = false,
    @SerialName("startDate")
    override val startDate: String = "",
    @SerialName("status")
    override val status: StatusCommX? = StatusCommX(),
    @SerialName("uid")
    override val uid: String = ""
) : CompetitionData()