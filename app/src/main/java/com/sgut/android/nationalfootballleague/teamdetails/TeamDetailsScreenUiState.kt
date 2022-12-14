package com.sgut.android.nationalfootballleague.teamdetails

import com.sgut.android.nationalfootballleague.Athletes
import com.sgut.android.nationalfootballleague.NextEvent3
import com.sgut.android.nationalfootballleague.Team3
import com.sgut.android.nationalfootballleague.data.domainmodels.TeamDetailWithRosterModel

data class TeamDetailsScreenUiState(
    var currentSport: String = "",
    var currentLeague: String = "",
    val currentTeamDetails: TeamDetailWithRosterModel? = null,
    var atheletes: List<Athletes> = listOf(),
    var nextEvents: List<NextEvent3> = listOf()
    )
