package com.sgut.android.nationalfootballleague.data.emojis

/**
 * Emoji that visually represents each MLB team — keyed by ESPN team abbreviation.
 *
 * Sourced from the team identity (mascot, logo, nickname, or local landmark).
 * Used to add small visual flair next to team abbreviations on team cards.
 *
 * Keys must match the `abbreviation` field returned by ESPN for that team.
 * Note: ESPN now returns "ATH" for the Athletics (post Oakland → Sacramento move),
 * not "OAK".
 */
internal val MlbTeamEmojis: Map<String, String> = mapOf(
    "ARI" to "🐍",     // Arizona Diamondbacks
    "ATL" to "🪓",     // Atlanta Braves (tomahawk)
    "BAL" to "🐦",     // Baltimore Orioles
    "BOS" to "🔴🧦",   // Boston Red Sox
    "CHC" to "🐻",     // Chicago Cubs
    "CHW" to "⚪🧦",   // Chicago White Sox
    "CIN" to "🔴",     // Cincinnati Reds
    "CLE" to "🛡️",    // Cleveland Guardians
    "COL" to "🏔️",    // Colorado Rockies
    "DET" to "🐅",     // Detroit Tigers
    "HOU" to "🚀",     // Houston Astros
    "KC"  to "👑",     // Kansas City Royals
    "LAA" to "😇",     // Los Angeles Angels
    "LAD" to "💙",     // Los Angeles Dodgers
    "MIA" to "🐟",     // Miami Marlins
    "MIL" to "🍺",     // Milwaukee Brewers
    "MIN" to "👯",     // Minnesota Twins
    "NYM" to "🍎",     // New York Mets
    "NYY" to "🗽",     // New York Yankees (Statue of Liberty)
    "ATH" to "🐘",     // Athletics (elephant mascot)
    "PHI" to "🔔",     // Philadelphia Phillies (Liberty Bell)
    "PIT" to "🏴‍☠️",  // Pittsburgh Pirates
    "SD"  to "⛪",     // San Diego Padres
    "SF"  to "🌉",     // San Francisco Giants (Golden Gate Bridge)
    "SEA" to "⚓",     // Seattle Mariners
    "STL" to "🐦🔴",   // St. Louis Cardinals
    "TB"  to "☀️",     // Tampa Bay Rays
    "TEX" to "🤠",     // Texas Rangers
    "TOR" to "🐦🔵",   // Toronto Blue Jays
    "WSH" to "🇺🇸",    // Washington Nationals
)