package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list

import com.sgut.android.nationalfootballleague.data.db.league.LeagueDbObj
import com.sgut.android.nationalfootballleague.data.db.sport.SportDbObj

data class LeagueModel(
    val id: String = "",
    val uid: String = "",
    val name: String = "",
    val abbreviation: String = "",
    val shortName: String = "",
    val slug: String = "",
    val teams: List<TeamModel> = listOf()
)


fun LeagueModel.totoDbModel(): LeagueDbObj {
    return LeagueDbObj(
        id = id,
        uid = uid,
        name = name,
        abbreviation = abbreviation,
        shortName = shortName,
        slug = slug,
    )
}

