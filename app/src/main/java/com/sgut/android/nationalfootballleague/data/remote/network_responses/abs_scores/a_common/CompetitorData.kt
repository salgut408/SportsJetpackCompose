package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.AthleteGolf
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.comm.TeamComm
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class CompetitorData {
    abstract val homeAway: HomeAway
    abstract val id: String
    abstract val score: String
    abstract val team: TeamComm
    abstract val uid: String
    abstract val winner: Boolean

    @Serializable
    enum class HomeAway {
        @SerialName("home")
        HOME,
        @SerialName("away")
        AWAY,
    }
}

@Serializable
data class DefaultCompetitor(
    @SerialName("homeAway")
    override val homeAway: HomeAway = HomeAway.HOME,
    @SerialName("id")
    override val id: String = "",
    @SerialName("score")
    override val score: String = "",
    @SerialName("team")
    override val team: TeamComm = TeamComm(),
    @SerialName("uid")
    override val uid: String = "",
    @SerialName("winner")
    override val winner: Boolean = false
) : CompetitorData()

@Serializable
data class SingleCompetitor(
    @SerialName("homeAway")
    override val homeAway: HomeAway = HomeAway.HOME,
    @SerialName("id")
    override val id: String = "",
    @SerialName("score")
    override val score: String = "",
    @SerialName("team")
    override val team: TeamComm = TeamComm(),
    @SerialName("uid")
    override val uid: String = "",
    @SerialName("winner")
    override val winner: Boolean = false,
    @SerialName("athlete")
    val athlete: AthleteGolf = AthleteGolf()
) : CompetitorData()

@Serializable
data class BaseballCompetitor(
    @SerialName("errors")
    val errors: Int = 0,
    @SerialName("hits")
    val hits: Int = 0,
    @SerialName("homeAway")
    override val homeAway: HomeAway = HomeAway.HOME,
    @SerialName("id")
    override val id: String = "",
    @SerialName("score")
    override val score: String = "",
    @SerialName("team")
    override val team: TeamComm = TeamComm(),
    @SerialName("uid")
    override val uid: String = "",
    @SerialName("winner")
    override val winner: Boolean = false
) : CompetitorData()