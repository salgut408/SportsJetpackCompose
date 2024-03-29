package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster

interface DefaultStatusInterface{
    val type: DefaultTypeInterface
}

data class StatusDomainModel(
    val clock: String = "",
    val displayClock: String = "",
    val period: Int = 0,
    val type: CompetitionTypeModel? = CompetitionTypeModel(),

    )
