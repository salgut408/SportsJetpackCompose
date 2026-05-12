package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list

import com.sgut.android.nationalfootballleague.data.db.sport.SportDbObj

data class SportModel(
    val id: String = "",
    val uid: String = "",
    val name: String = "",
    val slug: String = "",
    val league: LeagueModel = LeagueModel()
)

fun SportModel.totoDbModel(): SportDbObj {
    return SportDbObj(
        id = id,
        uid = uid,
        name = name,
        slug = slug,
    )
}
