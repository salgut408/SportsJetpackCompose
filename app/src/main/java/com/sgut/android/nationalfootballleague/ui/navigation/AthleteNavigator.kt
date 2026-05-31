package com.sgut.android.nationalfootballleague.ui.navigation

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * App-wide hook for "open this athlete's detail page."
 *
 * Athletes are rendered deep inside many screens (team rosters, game-detail
 * leaders, injury lists, scoring plays). Prop-drilling an `onAthleteClick`
 * callback through every intermediate composable would be noisy, so we expose
 * the navigation action as a CompositionLocal instead. The NavHost provides
 * the real implementation once; any leaf athlete card consumes it with
 * `LocalAthleteNavigator.current(athleteId, sport, league)`.
 *
 * Default is a no-op so previews and isolated composables don't crash — they
 * just won't navigate.
 */
fun interface AthleteNavigator {
    operator fun invoke(athleteId: String, sport: String, league: String)
}

val LocalAthleteNavigator = staticCompositionLocalOf {
    AthleteNavigator { _, _, _ -> /* no-op default */ }
}

/**
 * The sport/league context of the current screen. Provided once per detail
 * screen (team details, game details) so athlete cards can navigate with the
 * right `sport`/`league` without each one taking them as parameters.
 *
 * `athleteClick(id)` is the convenience the cards actually call — it reads the
 * navigator + this context together.
 */
data class SportLeagueContext(val sport: String = "", val league: String = "")

val LocalSportLeague = staticCompositionLocalOf { SportLeagueContext() }
