package com.sgut.android.nationalfootballleague.data.repository

import com.sgut.android.nationalfootballleague.data.remote.api.SportsApi
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel
import com.sgut.android.nationalfootballleague.domain.repositories.SportRepository
import com.sgut.android.nationalfootballleague.data.remote.network_responses.teams_list.toDomain
import com.sgut.android.nationalfootballleague.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SportRepositoryImpl @Inject constructor(
    private val sportsApi: SportsApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SportRepository {

    override suspend fun getSport(sportSlug: String, leagueSlug: String): SportModel =
        withContext(ioDispatcher) {
            sportsApi.getTeamsListForLeague(sportSlug, leagueSlug)
                .body()
                ?.sports
                ?.firstOrNull()
                ?.toDomain()
                ?: SportModel()
        }
}