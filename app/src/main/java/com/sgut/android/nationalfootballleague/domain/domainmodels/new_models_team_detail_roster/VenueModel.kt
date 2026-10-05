package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Images3
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.AddressModel

data class VenueModel(
    val fullName : String  = "",
    val address  : AddressModel? = AddressModel(),
    val images3: List<Images3> = listOf()

    )
