package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster

import com.sgut.android.nationalfootballleague.Links3

data class TicketsModel(
    val id: String = "",
    val summary: String = "",
    val maxPrice: Double = 0.0,
    val description: String = "",
    val startingPrice: Double = 0.0,
    val numberAvailable: Int = 0,
    val links: List<Links3> = listOf(),

    )
