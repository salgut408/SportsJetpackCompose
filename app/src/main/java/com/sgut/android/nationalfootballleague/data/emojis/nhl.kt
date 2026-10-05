package com.sgut.android.nationalfootballleague.data.emojis

/**
 * Emoji that visually represents each NHL team — keyed by ESPN team abbreviation.
 *
 * Sourced from the team identity (mascot, logo, nickname, team color, or local/cultural association).
 * Used to add small visual flair next to team abbreviations on team cards.
 *
 * Keys must match the `abbreviation` field returned by ESPN for that team.
 *
 * Note: Utah is now the Utah Mammoth, and ESPN uses "UTA" in team URLs/rosters.
 */
internal val NhlTeamEmojis: Map<String, String> = mapOf(
    "ANA" to "🦆",     // Anaheim Ducks
    "BOS" to "🐻",     // Boston Bruins
    "BUF" to "🦬",     // Buffalo Sabres
    "CAR" to "🌪️",    // Carolina Hurricanes
    "CBJ" to "💣",     // Columbus Blue Jackets
    "CGY" to "🔥",     // Calgary Flames
    "CHI" to "🦅",     // Chicago Blackhawks
    "COL" to "🏔️",    // Colorado Avalanche
    "DAL" to "⭐",     // Dallas Stars
    "DET" to "🪽",     // Detroit Red Wings
    "EDM" to "🛢️",    // Edmonton Oilers
    "FLA" to "🐆",     // Florida Panthers
    "LA"  to "👑",     // Los Angeles Kings
    "MIN" to "🌲",     // Minnesota Wild
    "MTL" to "🇨🇦",    // Montreal Canadiens
    "NJ"  to "😈",     // New Jersey Devils
    "NSH" to "🎸",     // Nashville Predators
    "NYI" to "🏝️",    // New York Islanders
    "NYR" to "🗽",     // New York Rangers
    "OTT" to "🛡️",    // Ottawa Senators
    "PHI" to "🟠",     // Philadelphia Flyers
    "PIT" to "🐧",     // Pittsburgh Penguins
    "SEA" to "🦑",     // Seattle Kraken
    "SJ"  to "🦈",     // San Jose Sharks
    "STL" to "🎵",     // St. Louis Blues
    "TB"  to "⚡",     // Tampa Bay Lightning
    "TOR" to "🍁",     // Toronto Maple Leafs
    "UTA" to "🦣",     // Utah Mammoth
    "VAN" to "🐋",     // Vancouver Canucks
    "VGK" to "⚔️",    // Vegas Golden Knights
    "WPG" to "✈️",     // Winnipeg Jets
    "WSH" to "🦅",     // Washington Capitals
)