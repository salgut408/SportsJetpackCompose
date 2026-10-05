package com.sgut.android.nationalfootballleague.data.emojis

/**
 * Emoji that visually represents each NBA team — keyed by ESPN team abbreviation.
 *
 * Sourced from the team identity (mascot, logo, nickname, team color, or local/cultural association).
 * Used to add small visual flair next to team abbreviations on team cards.
 *
 * Keys must match the `abbreviation` field returned by ESPN for that team.
 */
internal val NbaTeamEmojis: Map<String, String> = mapOf(
    "ATL" to "🦅",     // Atlanta Hawks
    "BOS" to "☘️",    // Boston Celtics
    "BKN" to "🌉",     // Brooklyn Nets
    "CHA" to "🐝",     // Charlotte Hornets
    "CHI" to "🐂",     // Chicago Bulls
    "CLE" to "⚔️",    // Cleveland Cavaliers
    "DAL" to "🐴",     // Dallas Mavericks
    "DEN" to "⛏️",    // Denver Nuggets
    "DET" to "🔧",     // Detroit Pistons
    "GS"  to "🌉",     // Golden State Warriors
    "HOU" to "🚀",     // Houston Rockets
    "IND" to "🏎️",    // Indiana Pacers
    "LAC" to "⛵",     // LA Clippers
    "LAL" to "💜💛",   // Los Angeles Lakers
    "MEM" to "🐻",     // Memphis Grizzlies
    "MIA" to "🔥",     // Miami Heat
    "MIL" to "🦌",     // Milwaukee Bucks
    "MIN" to "🐺",     // Minnesota Timberwolves
    "NO"  to "🪶",     // New Orleans Pelicans
    "NY"  to "🗽",     // New York Knicks
    "OKC" to "🌩️",    // Oklahoma City Thunder
    "ORL" to "✨",     // Orlando Magic
    "PHI" to "🔔",     // Philadelphia 76ers
    "PHX" to "☀️",    // Phoenix Suns
    "POR" to "🌲",     // Portland Trail Blazers
    "SA"  to "⚙️",     // San Antonio Spurs
    "SAC" to "👑",     // Sacramento Kings
    "TOR" to "🦖",     // Toronto Raptors
    "UTA" to "🎵",     // Utah Jazz
    "WSH" to "🧙",     // Washington Wizards
)