package com.sgut.android.nationalfootballleague.data.emojis
/**
 * Emoji that visually represents each NFL team — keyed by ESPN team abbreviation.
 *
 * Sourced from the team identity (mascot, logo, nickname, team color, or local/cultural association).
 * Used to add small visual flair next to team abbreviations on team cards.
 *
 * Keys must match the `abbreviation` field returned by ESPN for that team.
 */
internal val NflTeamEmojis: Map<String, String> = mapOf(
    "ARI" to "🐦🔴",   // Arizona Cardinals
    "ATL" to "🦅",     // Atlanta Falcons
    "BAL" to "🐦‍⬛",   // Baltimore Ravens
    "BUF" to "🦬",     // Buffalo Bills
    "CAR" to "🐈‍⬛",   // Carolina Panthers
    "CHI" to "🐻",     // Chicago Bears
    "CIN" to "🐅",     // Cincinnati Bengals
    "CLE" to "🟤",     // Cleveland Browns
    "DAL" to "🤠",     // Dallas Cowboys
    "DEN" to "🐎",     // Denver Broncos
    "DET" to "🦁",     // Detroit Lions
    "GB"  to "🧀",     // Green Bay Packers
    "HOU" to "🐂",     // Houston Texans
    "IND" to "🐴",     // Indianapolis Colts
    "JAX" to "🐆",     // Jacksonville Jaguars
    "KC"  to "🏹",     // Kansas City Chiefs
    "LV"  to "☠️",     // Las Vegas Raiders
    "LAC" to "⚡",     // Los Angeles Chargers
    "LAR" to "🐏",     // Los Angeles Rams
    "MIA" to "🐬",     // Miami Dolphins
    "MIN" to "🛡️",    // Minnesota Vikings
    "NE"  to "🇺🇸",    // New England Patriots
    "NO"  to "⚜️",    // New Orleans Saints
    "NYG" to "🏙️",    // New York Giants
    "NYJ" to "✈️",     // New York Jets
    "PHI" to "🦅🟢",   // Philadelphia Eagles
    "PIT" to "⚙️",     // Pittsburgh Steelers
    "SF"  to "⛏️",    // San Francisco 49ers
    "SEA" to "🦅🌊",   // Seattle Seahawks
    "TB"  to "🏴‍☠️",  // Tampa Bay Buccaneers
    "TEN" to "⚔️",     // Tennessee Titans
    "WSH" to "🪖",     // Washington Commanders
)