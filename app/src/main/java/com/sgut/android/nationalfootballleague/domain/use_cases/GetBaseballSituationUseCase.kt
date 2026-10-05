package com.sgut.android.nationalfootballleague.domain.use_cases

import com.sgut.android.nationalfootballleague.domain.repositories.GameDetailsRepository
import com.sgut.android.nationalfootballleague.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBaseballSituationUseCase @Inject constructor(
    private val gameDetailsRepository: GameDetailsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(sport: String, league: String, event: String){
        withContext(ioDispatcher) {
            val situation = gameDetailsRepository.getGameDetails(sport, league, event).baseballSituation
            return@withContext situation
        }
    }
}