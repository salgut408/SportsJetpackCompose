package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.data.remote.network_responses.articles.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.asDomain
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.asDomain
import com.sgut.android.nationalfootballleague.data.db.SportsDataBase
import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.DefaultScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard.BaseballScoreBoardNetwork
import com.sgut.android.nationalfootballleague.data.remote.network_responses.tennis_scoreboard_response.asDomain
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.tennis_scoreboard_models.TennisScoreboardModel
import com.sgut.android.nationalfootballleague.domain.repositories.ScoreboardRepository
import com.sgut.android.nationalfootballleague.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

/**
 * Scoreboard repository. Every endpoint degrades to an empty model on failure
 * (HTTP error, missing body, parse error) so one bad sport-specific parse can't
 * take down the whole scoreboard screen, which loads several of these in parallel.
 */
class ScoreboardRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @Suppress("unused") private val sportsDataBase: SportsDataBase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ScoreboardRepository {

    override suspend fun getGeneralScoreboard(
        sport: String,
        league: String,
    ): BasicScoreboardModel = fetch(
        label = "general $sport/$league",
        default = BasicScoreboardModel(),
        call = { sportsApi.getGeneralScoreboard(sport, league) },
        transform = { it.asDomain() },
    )

    override suspend fun getCollegeBasketballScoreboard(
        sport: String,
        league: String,
        limit: String,
    ): BasicScoreboardModel = fetch(
        label = "college basketball $sport/$league",
        default = BasicScoreboardModel(),
        call = { sportsApi.getCollegeBasketballScoreboard(sport, league, limit) },
        transform = { it.asDomain() },
    )

    override suspend fun getBaseballScoreboard(
        sport: String,
        league: String,
    ): BaseballScoreBoardNetwork = fetch(
        label = "baseball $sport/$league",
        default = BaseballScoreBoardNetwork(),
        call = { sportsApi.getBaseballScoreboard(sport, league) },
        transform = { it },
    )

    override suspend fun getTennisScoreBoard(sport: String, league: String): TennisScoreboardModel = fetch(
        label = "tennis $sport/$league",
        default = TennisScoreboardModel(),
        call = { sportsApi.getTennisScoreboard(sport, league) },
        transform = { it.asDomain() },
    )

    override suspend fun getAbstractScoreBoard(sport: String, league: String): ScoreboardData = fetch(
        label = "abstract $sport/$league",
        default = DefaultScoreboardData(),
        call = { sportsApi.getAbstractScoreboard(sport, league) },
        transform = { it },
    )

    override suspend fun getGeneralScoreboardByDate(
        sport: String,
        league: String,
        date: String,
    ): BasicScoreboardModel = fetch(
        label = "general by date $sport/$league/$date",
        default = BasicScoreboardModel(),
        call = { sportsApi.getGeneralScoreboardWithDate(sport, league, date) },
        transform = { it.asDomain() },
    )

    /**
     * Runs [call] on [ioDispatcher] and maps the body with [transform], falling back to
     * [default] on any failure. [CancellationException] is rethrown so leaving the screen
     * still cancels the request (catching it would break structured concurrency).
     */
    private suspend fun <T, R> fetch(
        label: String,
        default: R,
        call: suspend () -> Response<T>,
        transform: (T) -> R,
    ): R = withContext(ioDispatcher) {
        try {
            val response = call()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                transform(body)
            } else {
                Timber.e("Scoreboard $label failed: HTTP ${response.code()}")
                default
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Timber.e(e, "Scoreboard $label threw")
            default
        }
    }
}
