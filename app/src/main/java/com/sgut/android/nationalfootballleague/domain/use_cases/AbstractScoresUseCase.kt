package com.sgut.android.nationalfootballleague.domain.use_cases

import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.domain.repositories.ScoreboardRepository
import com.sgut.android.nationalfootballleague.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AbstractScoresUseCase @Inject constructor(
    private val scoreboardRepository: ScoreboardRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(sport: String, league: String): ScoreboardData =
        withContext(ioDispatcher) {
            val scores = scoreboardRepository.getAbstractScoreBoard(sport, league)
           Timber.d("SAL_GUT ABSTRACT SCORE USE CASE scores: $scores")
            return@withContext scores
        }
}