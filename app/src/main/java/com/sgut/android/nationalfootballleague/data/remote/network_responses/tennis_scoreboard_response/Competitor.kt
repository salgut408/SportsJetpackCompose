package com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response


import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.CompetitorTennisModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Competitor(
    @SerialName("athlete")
    val athlete: Athlete = Athlete(),
    @SerialName("curatedRank")
    val curatedRank: CuratedRank = CuratedRank(),
    @SerialName("homeAway")
    val homeAway: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("linescores")
    val linescores: List<Linescore> = listOf(),
    @SerialName("order")
    val order: Int = 0,
    @SerialName("possession")
    val possession: Boolean = false,
    @SerialName("roster")
    val roster: Roster = Roster(),
    @Transient
    val statistics: List<Any> = listOf(),
    @SerialName("type")
    val type: String = "",
    @SerialName("uid")
    val uid: String = "",
    @SerialName("winner")
    val winner: Boolean = false
)

fun Competitor.asDomain(): CompetitorTennisModel{
    return CompetitorTennisModel(
        athlete = athlete.asDomain(),
        curatedRank = curatedRank.asDomain(),
        homeAway = homeAway,
        id = id,
        linescores = linescores.map { it.asDomain() },
        order = order,
        possession = possession,
        roster = roster.asDomain(),
        statistics = statistics,
        type = type,
        uid = uid,
        winner = winner
    )
}