package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.runtime.Immutable
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.AthletesRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.NextEventModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleDomainModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamStatsModel

/**
 * Sealed state for the Team Details screen. Same Loading/Content/Error shape
 * as [com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.GameDetailsUiState]
 * and [com.sgut.android.nationalfootballleague.ui.screens.homelistscreen.HomeUiState],
 * so the Route/Screen/Content layering is identical across screens.
 *
 * The previous shape was a single mutable data class with default values for
 * everything, which meant the screen couldn't tell "still loading" from
 * "loaded but ESPN returned nothing." This sealed type makes that distinction
 * explicit at the type level.
 */
sealed interface TeamDetailsScreenUiState {

    /** Pre-fetch / no data yet. Initial value of the state flow. */
    data object Loading : TeamDetailsScreenUiState

    /** All three calls (team / schedule / stats) succeeded. */
    @Immutable
    data class Content(
        val sport: String,
        val league: String,
        val team: FullTeamDetailWithRosterModel,
        val athletes: List<AthletesRosterModel>,
        val nextEvents: List<NextEventModel>,
        val schedule: ScheduleDomainModel,
        val stats: TeamStatsModel,
    ) : TeamDetailsScreenUiState

    /** A user-facing error message; the offending exception is logged. */
    data class Error(val message: String) : TeamDetailsScreenUiState
}
