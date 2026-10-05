package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.ui.graphics.Color
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.GameTeamColors

/**
 * Shared adapters for reusing the Game Details polish primitives
 * (`CardSectionHeader`, `SectionSpacer`, `SectionLabel`, `SectionValue`,
 * `TeamAccentStrip`) on the Team Details screen.
 *
 * Game Details has a two-team accent gradient (away → home). Team Details has
 * one team, so we synthesize a [GameTeamColors] by using the team's own
 * primary + alternate colors for both halves. The vertical-gradient accent
 * bar in `CardSectionHeader` becomes a `color → alt → alt → color` ribbon
 * which reads as "this is the team's color treatment" rather than as a
 * matchup gradient.
 */
fun teamDetailColors(team: FullTeamDetailWithRosterModel): GameTeamColors {
    val primary = parseHex(team.color, default = Color(0xFF1976D2))
    val alt = parseHex(team.alternateColor, default = Color(0xFF0D47A1))
    return GameTeamColors(
        awayPrimary = primary,
        awayAlternate = alt,
        homePrimary = primary,
        homeAlternate = alt,
    )
}

private fun parseHex(hex: String, default: Color): Color =
    runCatching {
        if (hex.isBlank()) default
        else Color(android.graphics.Color.parseColor("#$hex"))
    }.getOrDefault(default)
