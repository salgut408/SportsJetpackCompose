package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleDomainModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamStatsModel
import com.sgut.android.nationalfootballleague.domain.repositories.TeamDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

/**
 * Owns the Team Details screen state. Follows the same pattern as
 * [com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.GameDetailViewModel]:
 *
 *   - Internal source flows (one per data slice) are combined via
 *     [combine] + [stateIn] into a single sealed [TeamDetailsScreenUiState].
 *     The Composable observes one flow — no fan-out, no mutable shared state.
 *   - The three API calls (team / schedule / stats) run in parallel via
 *     [coroutineScope] + [async]. Total latency is the slowest call, not the
 *     sum of all three.
 *   - Errors surface as [TeamDetailsScreenUiState.Error] instead of getting
 *     swallowed and yielding empty-defaulted models.
 */
@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val teamDetailsRepository: TeamDetailsRepository,
) : ViewModel() {

    // Internal source flows — combined into uiState below.
    private val _sport = MutableStateFlow("")
    private val _league = MutableStateFlow("")
    private val _team = MutableStateFlow<FullTeamDetailWithRosterModel?>(null)
    private val _schedule = MutableStateFlow(ScheduleDomainModel())
    private val _stats = MutableStateFlow(TeamStatsModel())
    private val _errorMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<TeamDetailsScreenUiState> = combine(
        _sport,
        _league,
        _team,
        _schedule,
        _stats,
        _errorMessage,
    ) { values ->
        val sport = values[0] as String
        val league = values[1] as String
        val team = values[2] as FullTeamDetailWithRosterModel?
        val schedule = values[3] as ScheduleDomainModel
        val stats = values[4] as TeamStatsModel
        val errorMessage = values[5] as String?

        when {
            errorMessage != null -> TeamDetailsScreenUiState.Error(errorMessage)
            team == null -> TeamDetailsScreenUiState.Loading
            else -> TeamDetailsScreenUiState.Content(
                sport = sport,
                league = league,
                team = team,
                athletes = team.athletes.sortedBy { it.position.id },
                nextEvents = team.nextEvent,
                schedule = schedule,
                stats = stats,
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TeamDetailsScreenUiState.Loading,
    )

    /**
     * Fetch all three slices in parallel. ESPN's three endpoints are
     * independent, so structured concurrency wins us roughly 2× the latency
     * of the slowest call vs the old sequential `suspend; suspend; suspend`
     * chain.
     */
    fun loadTeamDetails(teamAbbreviation: String, sport: String, league: String) {
        viewModelScope.launch {
            _errorMessage.value = null
            try {
                val sportLc = sport.lowercase(Locale.ROOT)
                val leagueLc = league.lowercase(Locale.ROOT)
                val teamLc = teamAbbreviation.lowercase(Locale.ROOT)

                val (team, schedule, stats) = coroutineScope {
                    val teamD = async { teamDetailsRepository.getSpecificTeam(sportLc, leagueLc, teamLc) }
                    val schedD = async { teamDetailsRepository.getTeamSchedule(sport, league, teamAbbreviation) }
                    val statsD = async { teamDetailsRepository.getTeamStats(sport, league, teamAbbreviation) }
                    Triple(teamD.await(), schedD.await(), statsD.await())
                }

                _sport.value = sport
                _league.value = league
                _team.value = team
                _schedule.value = schedule
                _stats.value = stats
            } catch (e: Exception) {
                Timber.e(e, "loadTeamDetails failed for $sport/$league/$teamAbbreviation")
                _errorMessage.value = "Couldn't load team details. Please try again."
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
