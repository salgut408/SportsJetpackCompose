package com.sgut.android.nationalfootballleague.domain.use_cases

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.BasicScoreboardModel
import com.sgut.android.nationalfootballleague.domain.repositories.ScoreboardRepository
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_BASKETBALL
import com.sgut.android.nationalfootballleague.utils.printToLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetScoresUseCase @Inject constructor(
     private val scoreboardRepository: ScoreboardRepository,
     private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke (sport: String, league: String): BasicScoreboardModel =
        withContext(ioDispatcher) {
            if (league == NCAA_BASKETBALL) {
                return@withContext scoreboardRepository.getCollegeBasketballScoreboard(
                    sport = sport,
                    league = league,
                    limit = "200"
                )
            }
            return@withContext scoreboardRepository.getGeneralScoreboard(sport = sport, league = league). also {
                Timber.d("SAL_GUT GetScoresUseCase SUCCESS : ${it.league}")
            }
        }
}


sealed class RepoResult<out T : Any> {
    data class Success<out T: Any>(val data: T): RepoResult<T>()
    data class Error(val message: String): RepoResult<Nothing>()
}