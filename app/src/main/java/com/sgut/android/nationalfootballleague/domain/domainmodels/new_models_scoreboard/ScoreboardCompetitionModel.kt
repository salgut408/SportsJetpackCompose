package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.SituationScoreboard
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.StatusDomainModel



data class ScoreboardCompetitionModel(
    val id: String = "",
    val uid: String = "",
    val date: String = "",
    val startDate: String = "",
    val attendance: Int = 0,
    val status: StatusDomainModel? = StatusDomainModel(),
    val format: ScoreboardFormatModel = ScoreboardFormatModel(),
     val competitors: List<ScoreboardCompetitorsModel> = listOf(),
    val details: List<ScoreboardDetailsModel> = listOf(),
    val headlines: List<ScoreboardHeadlineModel> = listOf(), // move to own obj
    val venue: ScoreboardVenueModel = ScoreboardVenueModel(),
    val situation: SituationScoreboard = SituationScoreboard()
) {
    //return home TeamComm

    fun getHomeTeam(): ScoreboardCompetitorsModel {
        return competitors.first { it.homeAway == "home" }
    }

    fun getAwayTeam(): ScoreboardCompetitorsModel {
        return competitors.first { it.homeAway == "away" }
    }

    //    returns team tht should be shown first
    fun getFirstTeam() = getAwayTeam()

    fun getSecondTeam() = getHomeTeam()

//    TODO


    fun getPairedStatistics(): List<PairedStatistic> {
        return getFirstTeam().statistics.associateBy { it.name }.orEmpty()
            .let { firstStatisticMap ->
                getSecondTeam().statistics.orEmpty()
                    .filter { firstStatisticMap.containsKey(it.name) }
                    .map {
                        PairedStatistic(
                            it.abbreviation,
                            firstStatisticMap.getValue(it.name).displayValue,
                            it.displayValue
                        )
                    }
            }
    }

    data class PairedStatistic(
        val name: String,
        val firstDisplayValue: String,
        val secondDisplayValue: String,
    )


}
