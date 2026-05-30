package com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete

/**
 * Domain model for a single athlete's detail page. Flat, UI-friendly
 * projection of ESPN's `common/v3/.../athletes/{id}` response — every field
 * defaulted/nullable because ESPN's athlete payload varies a lot by sport and
 * by whether the player is active.
 */
data class FullAthleteModel(
    val id: String = "",
    val fullName: String = "",
    val displayName: String = "",
    val shortName: String = "",
    val jersey: String = "",
    val headshotHref: String = "",
    val active: Boolean = false,

    // Position
    val positionName: String = "",
    val positionAbbreviation: String = "",

    // Bio
    val age: Int = 0,
    val displayHeight: String = "",
    val displayWeight: String = "",
    val displayBirthPlace: String = "",
    val displayDOB: String = "",
    val displayExperience: String = "",
    val displayDraft: String = "",
    val displayBatsThrows: String = "",
    val collegeName: String = "",

    // Status (active / injured / etc.)
    val statusName: String = "",

    // Team context (for the hero gradient + crest)
    val team: AthleteTeamModel = AthleteTeamModel(),

    // Headline stats shown on the player card (e.g. AVG / HR / RBI or PTS / REB / AST)
    val statsSummaryLabel: String = "",
    val stats: List<AthleteStatModel> = emptyList(),
)

data class AthleteTeamModel(
    val id: String = "",
    val displayName: String = "",
    val abbreviation: String = "",
    val location: String = "",
    val color: String = "",
    val alternateColor: String = "",
    val logoHref: String = "",
)

data class AthleteStatModel(
    val name: String = "",
    val displayName: String = "",
    val shortDisplayName: String = "",
    val abbreviation: String = "",
    val description: String = "",
    val value: Double = 0.0,
    val displayValue: String = "",
    val rankDisplayValue: String = "",
)
