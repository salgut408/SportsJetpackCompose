package com.sgut.android.nationalfootballleague.data.emojis

import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASEBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASKETBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FOOTBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.HOCKEY
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.MLB
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NBA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_BASEBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_FOOTBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NFL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NHL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.WNBA

/**
 * Returns an emoji that represents the given team, when we have a mapping.
 * Null when the team's sport/league pair isn't covered yet — UI should treat
 * null as "no decoration."
 *
 * Add support for a new league by:
 *  1. Creating a per-league map file (see mlb.kt / nba.kt / etc.)
 *  2. Adding a branch below
 *
 * Lookups are by ESPN team abbreviation.
 */
fun teamEmoji(
    sport: String,
    league: String,
    teamAbbreviation: String,
): String? = when {
    sport == BASEBALL && league == MLB -> MlbTeamEmojis[teamAbbreviation]
    sport == BASKETBALL && league == NBA -> NbaTeamEmojis[teamAbbreviation]
    sport == BASKETBALL && league == WNBA -> WnbaTeamEmojis[teamAbbreviation]
    sport == FOOTBALL && league == NFL -> NflTeamEmojis[teamAbbreviation]
    sport == HOCKEY && league == NHL -> NhlTeamEmojis[teamAbbreviation]
    sport == FOOTBALL && league == NCAA_FOOTBALL -> NcaaFootballTeamEmojis[teamAbbreviation]
    sport == BASEBALL && league == NCAA_BASEBALL -> CollegeBaseballTeamEmojis[teamAbbreviation]
    else -> null
}