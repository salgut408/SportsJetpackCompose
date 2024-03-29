package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster

data class FullTeamDetailsFranchiseModel(
    val id: String = "",
    val uid: String = "",
    val slug: String = "",
    val location: String = "",
    val name: String = "",
    val nickname: String = "",
    val abbreviation: String = "",
    val displayName: String = "",
    val shortDisplayName: String = "",
    val color: String = "",
    val isActive: Boolean = true,
    val venue: VenueModel? = VenueModel(),
)
