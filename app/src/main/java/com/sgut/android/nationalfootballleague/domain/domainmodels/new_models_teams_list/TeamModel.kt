package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list

import com.sgut.android.nationalfootballleague.data.db.team.TeamDbObj
import com.sgut.android.nationalfootballleague.data.db.team.TeamDbObjFullInfo

data class TeamModel(
    val id: String = "",
    val uid: String = "",
    val slug: String = "",
    val abbreviation: String = "",
    val displayName: String = "",
    val shortDisplayName: String = "",
    val name: String = "",
    val nickname: String = "",
    val location: String = "",
    val color: String = "",
    val alternateColor: String = "",
    val isFavorite: Boolean = false,
    val logos: String = ""
    )

    fun TeamModel.asDbObj(sport: String, league: String, leagueAbrv: String) : TeamDbObjFullInfo {
        return TeamDbObjFullInfo(
            id = id,
            uid = uid,
            slug = slug,
            abbreviation = abbreviation,
            displayName = displayName,
            shortDisplayName = shortDisplayName,
            name = name,
            nickname = nickname,
            color = color,
            alternateColor = alternateColor,
            isFavorite = isFavorite,
            logo = logos,
            sport = sport,
            league = league,
            leagueAbbreviation = leagueAbrv
        )
    }


fun TeamModel.asTeamDbObj(isFavorite: Boolean, sport: String?, league: String?) : TeamDbObj {
    return TeamDbObj(
        id = id,
        uid = uid,
        slug = slug,
        abbreviation = abbreviation,
        displayName = displayName,
        color = color,
        alternateColor = alternateColor,
        isFavorite = isFavorite,
        logo = logos,
        sport = sport ?: "",
        league = league ?: "",
    )
}