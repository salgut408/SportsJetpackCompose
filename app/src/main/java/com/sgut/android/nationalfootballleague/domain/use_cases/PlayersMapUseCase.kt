package com.sgut.android.nationalfootballleague.domain.use_cases

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsAthleteDetailsModel
import com.sgut.android.nationalfootballleague.domain.repositories.TeamDetailsRepository
import com.sgut.android.nationalfootballleague.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Builds a `playerId → AthleteDetails` lookup map by fetching each team's roster
 * and flattening the results.
 *
 * This use case earns its keep: it composes multiple roster calls (one per team)
 * and reshapes the results. Compare with the thin pass-throughs elsewhere
 * (`use case → repo.foo()` with no logic) — those should be inlined into the
 * ViewModel, not kept around as ceremony. The rule of thumb for this codebase:
 *
 *   - Keep a use case if it composes multiple sources OR applies real logic.
 *   - Drop a use case if it just forwards one repo call.
 *   - Don't introduce new pass-through use cases when adding a screen.
 *
 * Rosters fetch in parallel so the total wait is one roster call, not N.
 */
class PlayersMapUseCase @Inject constructor(
    private val teamDetailsRepository: TeamDetailsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        sport: String,
        league: String,
        teams: List<String>,
    ): Map<String, GameDetailsAthleteDetailsModel> = withContext(ioDispatcher) {
        coroutineScope {
            teams
                .map { team ->
                    async {
                        teamDetailsRepository.getSpecificTeamRosterInGameDetails(sport, league, team)
                    }
                }
                .awaitAll()
                .flatten()
                .associateBy { it.id }
        }
    }
}