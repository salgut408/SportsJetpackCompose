package com.sgut.android.nationalfootballleague.data.remote.network_responses.full_athelete

import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.AthleteStatModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.AthleteTeamModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.FullAthleteModel

/**
 * Maps the raw ESPN athlete response into the flat [FullAthleteModel] the UI
 * consumes. Prefers the team logo whose `rel` contains "default"; falls back
 * to the first logo. All access is null-safe — ESPN omits chunks of this
 * payload for inactive players and across sports.
 */
fun NetworkAthleteResponse.asDomain(): FullAthleteModel {
    val a = athlete
    return FullAthleteModel(
        id = a.id,
        fullName = a.fullName,
        displayName = a.displayName,
        shortName = a.displayName.substringAfterLast(' ', a.displayName),
        jersey = a.jersey,
        headshotHref = a.headshot.href,
        active = a.active,
        positionName = a.position.displayName,
        positionAbbreviation = a.position.abbreviation,
        age = a.age,
        displayHeight = a.displayHeight,
        displayWeight = a.displayWeight,
        displayBirthPlace = a.displayBirthPlace,
        displayDOB = a.displayDOB,
        displayExperience = a.displayExperience,
        displayDraft = a.displayDraft,
        displayBatsThrows = a.displayBatsThrows,
        collegeName = a.college.name,
        statusName = a.status.name,
        team = a.team.asAthleteTeam(),
        statsSummaryLabel = a.statsSummary.displayName,
        stats = a.statsSummary.statistics.map { it.asAthleteStat() },
    )
}

private fun Team.asAthleteTeam(): AthleteTeamModel = AthleteTeamModel(
    id = id,
    displayName = displayName,
    abbreviation = abbreviation,
    location = location,
    color = color,
    alternateColor = alternateColor,
    logoHref = logos.firstOrNull { "default" in it.rel }?.href
        ?: logos.firstOrNull()?.href.orEmpty(),
)

private fun Statistic.asAthleteStat(): AthleteStatModel = AthleteStatModel(
    name = name,
    displayName = displayName,
    shortDisplayName = shortDisplayName,
    abbreviation = abbreviation,
    description = description,
    value = value,
    displayValue = displayValue,
    rankDisplayValue = rankDisplayValue,
)
