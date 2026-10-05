package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.BirthPlace
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Draft
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Experience
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Flag
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Headshot
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Injury
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Position
import com.sgut.android.nationalfootballleague.*

data class AthletesRosterModel(

    val id: String = "",
     val uid: String = "",
     val guid: String = "",
     val type: String = "",
    val firstName: String = "",
    val lastName: String = "",
     val fullName: String = "",
    val displayName: String = "",
    val shortName: String = "",
     val displayWeight: String = "",
     val displayHeight: String = "",
     val age: Int = 0,
    val dateOfBirth: String = "",
    val debutYear: Int = 0,
    val birthPlace: BirthPlace? = BirthPlace(),
    val slug: String = "",
    val headshot: Headshot = Headshot(),
    val jersey: String = "",
    val position: Position = Position(),
    val injuries: List<Injury>? = listOf(),
    val experience: Experience? = Experience(),
    val active: Boolean? = null,
    val draft: Draft = Draft(),
    val flag: Flag? = Flag(),
)
