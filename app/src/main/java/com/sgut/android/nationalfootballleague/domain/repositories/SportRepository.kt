package com.sgut.android.nationalfootballleague.domain.repositories

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel

interface SportRepository {
    suspend fun getSport(sportSlug: String, leagueSlug: String): SportModel
}