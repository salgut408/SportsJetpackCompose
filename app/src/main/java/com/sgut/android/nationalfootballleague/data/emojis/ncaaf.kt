package com.sgut.android.nationalfootballleague.data.emojis

/**
 * Emoji that visually represents each NCAA FBS football team — keyed by ESPN team abbreviation.
 *
 * Sourced from the team identity (mascot, logo, nickname, team color, or local/cultural association).
 * Used to add small visual flair next to team abbreviations on team cards.
 *
 * Keys should match the `abbreviation` field returned by ESPN for that team.
 *
 * Notes:
 * - This is intended for FBS / Division I-A college football, not every FCS team.
 * - ESPN abbreviations for college teams can be inconsistent, especially for newer FBS teams.
 * - Verify questionable keys against the ESPN API response before relying on them.
 */
internal val NcaaFootballTeamEmojis: Map<String, String> = mapOf(
    "AFA" to "✈️",     // Air Force Falcons
    "AKR" to "🦘",     // Akron Zips
    "ALA" to "🐘",     // Alabama Crimson Tide
    "APP" to "⛰️",     // Appalachian State Mountaineers
    "ARIZ" to "🐱",    // Arizona Wildcats
    "ARK" to "🐗",     // Arkansas Razorbacks
    "ARST" to "🐺",    // Arkansas State Red Wolves
    "ARMY" to "🪖",    // Army Black Knights
    "ASU" to "😈",     // Arizona State Sun Devils
    "AUB" to "🐅",     // Auburn Tigers

    "BALL" to "🐦🔴",  // Ball State Cardinals
    "BAY" to "🐻",     // Baylor Bears
    "BC" to "🦅",      // Boston College Eagles
    "BGSU" to "🦅",    // Bowling Green Falcons
    "BOIS" to "🐎",    // Boise State Broncos
    "BUFF" to "🦬",    // Buffalo Bulls
    "BYU" to "🐆",     // BYU Cougars

    "CAL" to "🐻",     // California Golden Bears
    "CCU" to "🐓",     // Coastal Carolina Chanticleers
    "CHAR" to "⛏️",    // Charlotte 49ers
    "CIN" to "🐾",     // Cincinnati Bearcats
    "CLEM" to "🐅",    // Clemson Tigers
    "CMU" to "🔥",     // Central Michigan Chippewas
    "CONN" to "🐺",    // UConn Huskies
    "COLO" to "🦬",    // Colorado Buffaloes
    "CSU" to "🐏",     // Colorado State Rams

    "DEL" to "🐔",     // Delaware Blue Hens
    "DUKE" to "😈",    // Duke Blue Devils

    "ECU" to "🏴‍☠️",  // East Carolina Pirates
    "EMU" to "🦅",     // Eastern Michigan Eagles

    "FAU" to "🦉",     // Florida Atlantic Owls
    "FIU" to "🐆",     // FIU Panthers
    "FLA" to "🐊",     // Florida Gators
    "FSU" to "🍢",     // Florida State Seminoles
    "FRES" to "🐶",    // Fresno State Bulldogs

    "GASO" to "🦅",    // Georgia Southern Eagles
    "GAST" to "🐆",    // Georgia State Panthers
    "GT" to "🐝",      // Georgia Tech Yellow Jackets

    "HAW" to "🌺",     // Hawai'i Rainbow Warriors
    "HOU" to "🐆",     // Houston Cougars

    "ILL" to "🔶",     // Illinois Fighting Illini
    "IND" to "🔴",     // Indiana Hoosiers
    "IOWA" to "🐤",    // Iowa Hawkeyes
    "ISU" to "🌪️",    // Iowa State Cyclones

    "JMU" to "🐶",     // James Madison Dukes
    "JVST" to "🐓",    // Jacksonville State Gamecocks

    "KAN" to "🐦",     // Kansas Jayhawks
    "KENN" to "🦉",    // Kennesaw State Owls
    "KENT" to "⚡",    // Kent State Golden Flashes
    "KSU" to "🐱",     // Kansas State Wildcats
    "KU" to "🐦",      // Kansas Jayhawks, alternate ESPN-style key if present
    "UK" to "🐱",      // Kentucky Wildcats

    "LIB" to "🔥",     // Liberty Flames
    "LT" to "🐶",      // Louisiana Tech Bulldogs
    "LOU" to "🐦🔴",   // Louisville Cardinals
    "LSU" to "🐅",     // LSU Tigers

    "MASS" to "🚩",    // UMass Minutemen
    "MD" to "🐢",      // Maryland Terrapins
    "MEM" to "🐅",     // Memphis Tigers
    "MIA" to "🌀",     // Miami Hurricanes
    "M-OH" to "🟥",    // Miami (OH) RedHawks
    "MICH" to "🐺",    // Michigan Wolverines
    "MINN" to "🦫",    // Minnesota Golden Gophers
    "MISS" to "🐻",    // Ole Miss Rebels
    "MIZ" to "🐅",     // Missouri Tigers
    "MRSH" to "🦬",    // Marshall Thundering Herd
    "MSST" to "🐶",    // Mississippi State Bulldogs
    "MSU" to "⚔️",    // Michigan State Spartans
    "MTSU" to "⚡",    // Middle Tennessee Blue Raiders

    "NAVY" to "⚓",    // Navy Midshipmen
    "NCST" to "🐺",    // NC State Wolfpack
    "NDSU" to "🦬",    // North Dakota State Bison
    "NEB" to "🌽",     // Nebraska Cornhuskers
    "NEV" to "🐺",     // Nevada Wolf Pack
    "NIU" to "🐺",     // Northern Illinois Huskies
    "NMSU" to "🤠",    // New Mexico State Aggies
    "NU" to "🐱",      // Northwestern Wildcats
    "NW" to "🐱",      // Northwestern Wildcats, alternate key if present
    "ND" to "☘️",      // Notre Dame Fighting Irish
    "NORTH" to "🐺",   // North Texas Mean Green
    "UNT" to "🟢",     // North Texas Mean Green, alternate key if present

    "ODU" to "👑",     // Old Dominion Monarchs
    "OHIO" to "🐾",    // Ohio Bobcats
    "OKLA" to "🛒",    // Oklahoma Sooners
    "OKST" to "🤠",    // Oklahoma State Cowboys
    "ORE" to "🦆",     // Oregon Ducks
    "ORST" to "🦫",    // Oregon State Beavers

    "PENN" to "🦁",    // Penn State Nittany Lions
    "PITT" to "🐆",    // Pittsburgh Panthers
    "PUR" to "🚂",     // Purdue Boilermakers

    "RICE" to "🦉",    // Rice Owls
    "RUTG" to "🛡️",   // Rutgers Scarlet Knights

    "SAC" to "🐝",     // Sacramento State Hornets
    "SAM" to "🐻",     // Sam Houston Bearkats
    "SDSU" to "⚔️",   // San Diego State Aztecs
    "SJSU" to "⚔️",   // San José State Spartans
    "SMU" to "🐎",     // SMU Mustangs
    "SOALA" to "🐆",   // South Alabama Jaguars
    "SOILL" to "🐕",   // Southern Illinois, only if needed / verify before use
    "SC" to "🐓",      // South Carolina Gamecocks
    "STAN" to "🌲",    // Stanford Cardinal
    "SYR" to "🍊",     // Syracuse Orange

    "TCU" to "🐸",     // TCU Horned Frogs
    "TEM" to "🦉",     // Temple Owls
    "TENN" to "🍊",    // Tennessee Volunteers
    "TEX" to "🤘",     // Texas Longhorns
    "TLSA" to "🌪️",   // Tulsa Golden Hurricane
    "TOL" to "🚀",     // Toledo Rockets
    "TROY" to "⚔️",    // Troy Trojans
    "TTU" to "🏴‍☠️",  // Texas Tech Red Raiders
    "TULN" to "🌊",    // Tulane Green Wave
    "TXST" to "🐾",    // Texas State Bobcats

    "UAB" to "🐉",     // UAB Blazers
    "UCF" to "⚔️",    // UCF Knights
    "UCLA" to "🐻",    // UCLA Bruins
    "UL" to "🌶️",      // Louisiana Ragin' Cajuns
    "ULM" to "🦅",     // UL Monroe Warhawks
    "UNLV" to "🎰",    // UNLV Rebels
    "USC" to "⚔️",     // USC Trojans
    "USF" to "🐂",     // South Florida Bulls
    "USM" to "🦅",     // Southern Miss Golden Eagles
    "UTAH" to "🪶",    // Utah Utes
    "UTEP" to "⛏️",    // UTEP Miners
    "UTSA" to "🐦",    // UTSA Roadrunners
    "UVA" to "⚔️",     // Virginia Cavaliers

    "VAN" to "⚓",     // Vanderbilt Commodores
    "VT" to "🦃",      // Virginia Tech Hokies

    "WASH" to "🐺",    // Washington Huskies
    "WKU" to "🔴",     // Western Kentucky Hilltoppers
    "WMU" to "🐎",     // Western Michigan Broncos
    "WIS" to "🦡",     // Wisconsin Badgers
    "WVU" to "⛰️",     // West Virginia Mountaineers
    "WYO" to "🤠",     // Wyoming Cowboys
)