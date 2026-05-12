package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsCompetitionModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ProbablesModel
import kotlinx.serialization.Serializable


@Serializable
data class GameDetailsCompetitions(

    @SerialName("id")
    val id: String? = null,
    @SerialName("uid")
    val uid: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("neutralSite")
    val neutralSite: Boolean? = null,
    @SerialName("conferenceCompetition")
    val conferenceCompetition: Boolean? = null,
    @SerialName("boxscoreAvailable")
    val boxscoreAvailable: Boolean? = null,
    @SerialName("commentaryAvailable")
    val commentaryAvailable: Boolean? = null,
    @SerialName("liveAvailable")
    val liveAvailable: Boolean? = null,
    @SerialName("onWatchESPN")
    val onWatchESPN: Boolean? = null,
    @SerialName("recent")
    val recent: Boolean? = null,
    @SerialName("boxscoreSource")
    val boxscoreSource: String? = null,
    @SerialName("playByPlaySource")
    val playByPlaySource: String? = null,
    @SerialName("competitors")
    val competitors: List<GameDetailsCompetitors> = listOf(),
    @SerialName("status")
    val status: GameDetailsStatus? = GameDetailsStatus(),
    @SerialName("broadcasts")
    val broadcasts: ArrayList<GameDetailsBroadcasts> = arrayListOf(),
    @SerialName("shotChartAvailable")
    val shotChartAvailable: Boolean? = null,
    @SerialName("timeoutsAvailable")
    val timeoutsAvailable: Boolean? = null,
    @SerialName("possessionArrowAvailable")
    val possessionArrowAvailable: Boolean? = null,


    )

fun GameDetailsCompetitions.asDomain(): GameDetailsCompetitionModel {
    return GameDetailsCompetitionModel(
        id = id ?: "",
        date = date ?: "",
        competitors = competitors.map { it.asDomain() }, // map
        status = status?.asDomain(),
        shotChartAvailable = shotChartAvailable ?: false,
        timeoutsAvailable = timeoutsAvailable ?: false,
        possessionArrowAvailable = possessionArrowAvailable ?: false,
        boxscoreAvailable = boxscoreAvailable ?: false


    )
}

@Serializable
data class Probables(

    @SerialName("name")
    val name: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName:String = "",
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("playerId")
    val playerId: Int = 0,
    @SerialName("athlete")
    val athlete: GameDetailsAthlete? = GameDetailsAthlete(),

    )

fun Probables.asDomain(): ProbablesModel {
    return ProbablesModel(
        name = name,
        displayName = displayName,
        shortDisplayName = shortDisplayName,
        playerId = playerId,
        athlete = athlete?.asDomain()
    )
}