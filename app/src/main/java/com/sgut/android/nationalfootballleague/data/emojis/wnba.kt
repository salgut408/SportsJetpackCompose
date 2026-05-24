package com.sgut.android.nationalfootballleague.data.emojis

/**
 * Emoji that visually represents each WNBA team — keyed by ESPN team abbreviation.
 *
 * Sourced from the team identity (mascot, logo, nickname, team color, or local/cultural association).
 * Used to add small visual flair next to team abbreviations on team cards.
 *
 * Keys must match the `abbreviation` field returned by ESPN for that team.
 *
 * Note: ESPN uses "GS" for the Golden State Valkyries.
 */
internal val WnbaTeamEmojis: Map<String, String> = mapOf(
    "ATL" to "🪽",     // Atlanta Dream
    "CHI" to "🌌",     // Chicago Sky
    "CONN" to "☀️",    // Connecticut Sun
    "DAL" to "🪽",     // Dallas Wings
    "GS"  to "⚔️",     // Golden State Valkyries
    "IND" to "🔥",     // Indiana Fever
    "LA"  to "✨",     // Los Angeles Sparks
    "LV"  to "🎰",     // Las Vegas Aces
    "MIN" to "🐱",     // Minnesota Lynx
    "NY"  to "🗽",     // New York Liberty
    "PHX" to "☿️",     // Phoenix Mercury
    "SEA" to "🌩️",    // Seattle Storm
    "WSH" to "🏛️",    // Washington Mystics
)