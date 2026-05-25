package com.sgut.android.nationalfootballleague.data.emojis

/**
 * Emoji that visually represents common NCAA Division I college baseball teams —
 * keyed by ESPN team abbreviation.
 *
 * Keys must match the `abbreviation` field returned by ESPN.
 *
 * Notes:
 * - College baseball has hundreds of Division I teams.
 * - This map should be expanded from real ESPN API responses, not guessed manually.
 * - When a team appears without an emoji, log its abbreviation/displayName and add it here.
 */
internal val CollegeBaseballTeamEmojis: Map<String, String> = mapOf(
    "UIC" to "🔥",     // UIC Flames
    "LR"  to "🐴",     // Little Rock Trojans

    "ARK" to "🐗",     // Arkansas Razorbacks
    "LSU" to "🐅",     // LSU Tigers
    "TENN" to "🍊",    // Tennessee Volunteers
    "FLA" to "🐊",     // Florida Gators
    "FSU" to "🍢",     // Florida State Seminoles
    "MIA" to "🌀",     // Miami Hurricanes
    "CLEM" to "🐅",    // Clemson Tigers
    "SC" to "🐓",      // South Carolina Gamecocks
    "UGA" to "🐶",     // Georgia Bulldogs
    "GT" to "🐝",      // Georgia Tech Yellow Jackets
    "AUB" to "🐅",     // Auburn Tigers
    "ALA" to "🐘",     // Alabama Crimson Tide
    "MISS" to "🐻",    // Ole Miss Rebels
    "MSST" to "🐶",    // Mississippi State Bulldogs
    "VAN" to "⚓",     // Vanderbilt Commodores
    "UK" to "🐱",      // Kentucky Wildcats
    "MIZ" to "🐅",     // Missouri Tigers

    "TEX" to "🤘",     // Texas Longhorns
    "TAMU" to "👍",    // Texas A&M Aggies
    "OU" to "🛒",      // Oklahoma Sooners
    "OKST" to "🤠",    // Oklahoma State Cowboys
    "TTU" to "🏴‍☠️",  // Texas Tech Red Raiders
    "TCU" to "🐸",     // TCU Horned Frogs
    "BAY" to "🐻",     // Baylor Bears
    "KU" to "🐦",      // Kansas Jayhawks
    "KSU" to "🐱",     // Kansas State Wildcats
    "HOU" to "🐆",     // Houston Cougars
    "UCF" to "⚔️",    // UCF Knights
    "WVU" to "⛰️",     // West Virginia Mountaineers

    "UNC" to "🐏",     // North Carolina Tar Heels
    "NCST" to "🐺",    // NC State Wolfpack
    "DUKE" to "😈",    // Duke Blue Devils
    "WAKE" to "😈",    // Wake Forest Demon Deacons
    "UVA" to "⚔️",     // Virginia Cavaliers
    "VT" to "🦃",      // Virginia Tech Hokies
    "LOU" to "🐦🔴",   // Louisville Cardinals
    "PITT" to "🐆",    // Pittsburgh Panthers
    "ND" to "☘️",      // Notre Dame Fighting Irish
    "BC" to "🦅",      // Boston College Eagles

    "ORE" to "🦆",     // Oregon Ducks
    "ORST" to "🦫",    // Oregon State Beavers
    "WASH" to "🐺",    // Washington Huskies
    "WSU" to "🐆",     // Washington State Cougars
    "STAN" to "🌲",    // Stanford Cardinal
    "CAL" to "🐻",     // California Golden Bears
    "UCLA" to "🐻",    // UCLA Bruins
    "USC" to "⚔️",     // USC Trojans
    "ARIZ" to "🐱",    // Arizona Wildcats
    "ASU" to "😈",     // Arizona State Sun Devils
    "UTAH" to "🪶",    // Utah Utes

    "MICH" to "🐺",    // Michigan Wolverines
    "MSU" to "⚔️",    // Michigan State Spartans
    "OSU" to "🌰",     // Ohio State Buckeyes
    "IND" to "🔴",     // Indiana Hoosiers
    "PUR" to "🚂",     // Purdue Boilermakers
    "ILL" to "🔶",     // Illinois Fighting Illini
    "IOWA" to "🐤",    // Iowa Hawkeyes
    "MINN" to "🦫",    // Minnesota Golden Gophers
    "NEB" to "🌽",     // Nebraska Cornhuskers
    "MD" to "🐢",      // Maryland Terrapins
    "RUTG" to "🛡️",   // Rutgers Scarlet Knights
    "PSU" to "🦁",     // Penn State Nittany Lions
    "NW" to "🐱",      // Northwestern Wildcats
    "WIS" to "🦡",     // Wisconsin Badgers

    "CONN" to "🐺",    // UConn Huskies
    "CREI" to "🐦",    // Creighton Bluejays
    "SJU" to "🔴",     // St. John's Red Storm
    "VILL" to "🐱",    // Villanova Wildcats
    "XAV" to "⚔️",    // Xavier Musketeers
    "HALL" to "🏴‍☠️", // Seton Hall Pirates

    "ECU" to "🏴‍☠️",  // East Carolina Pirates
    "CHAR" to "⛏️",    // Charlotte 49ers
    "FAU" to "🦉",     // Florida Atlantic Owls
    "TULN" to "🌊",    // Tulane Green Wave
    "USF" to "🐂",     // South Florida Bulls
    "WICH" to "🌾",    // Wichita State Shockers
    "MEM" to "🐅",     // Memphis Tigers
    "RICE" to "🦉",    // Rice Owls
    "UTSA" to "🐦",    // UTSA Roadrunners

    "COFC" to "🐆",    // College of Charleston Cougars
    "ELON" to "🔥",    // Elon Phoenix
    "UNCW" to "🌊",    // UNC Wilmington Seahawks
    "NE" to "🐾",      // Northeastern Huskies
    "WM" to "🪶",      // William & Mary Tribe
    "DEL" to "🐔",     // Delaware Blue Hens

    "UCI" to "🐜",     // UC Irvine Anteaters
    "UCSB" to "🌊",    // UC Santa Barbara Gauchos
    "LBSU" to "🏖️",   // Long Beach State Dirtbags
    "CSF" to "🐘",     // Cal State Fullerton Titans
    "UCSD" to "🔱",    // UC San Diego Tritons
    "HAW" to "🌺",     // Hawai'i Rainbow Warriors

    "GONZ" to "🐶",    // Gonzaga Bulldogs
    "USD" to "⚓",     // San Diego Toreros
    "LMU" to "🦁",     // Loyola Marymount Lions
    "PEPP" to "🌊",    // Pepperdine Waves
    "SCU" to "🐎",     // Santa Clara Broncos
    "PORT" to "⚓",    // Portland Pilots

    "DBU" to "🛡️",    // Dallas Baptist Patriots
    "LT" to "🐶",      // Louisiana Tech Bulldogs
    "LIB" to "🔥",     // Liberty Flames
    "MTSU" to "⚡",    // Middle Tennessee Blue Raiders
    "NMSU" to "🤠",    // New Mexico State Aggies
    "SHSU" to "🐻",    // Sam Houston Bearkats
    "WKU" to "🔴",     // Western Kentucky Hilltoppers

    "USA" to "🐆",     // South Alabama Jaguars
    "TROY" to "⚔️",    // Troy Trojans
    "CCU" to "🐓",     // Coastal Carolina Chanticleers
    "GASO" to "🦅",    // Georgia Southern Eagles
    "GAST" to "🐆",    // Georgia State Panthers
    "UL" to "🌶️",      // Louisiana Ragin' Cajuns
    "ULM" to "🦅",     // UL Monroe Warhawks
    "APP" to "⛰️",     // Appalachian State Mountaineers
    "JMU" to "🐶",     // James Madison Dukes
    "TXST" to "🐾",    // Texas State Bobcats
    "ARST" to "🐺",    // Arkansas State Red Wolves
    "ODU" to "👑",     // Old Dominion Monarchs
    "USM" to "🦅",     // Southern Miss Golden Eagles

    "ORU" to "🦅",     // Oral Roberts Golden Eagles
    "NDSU" to "🦬",    // North Dakota State Bison
    "SDSU" to "🐰",    // South Dakota State Jackrabbits

    "SEMO" to "🐦",    // Southeast Missouri State Redhawks
    "MORE" to "🦅",    // Morehead State Eagles

    "BRY" to "🐶",     // Bryant Bulldogs
    "MAINE" to "🐻",   // Maine Black Bears
    "UMBC" to "🐕",    // UMBC Retrievers
    "BING" to "🐻",    // Binghamton Bearcats

    "CAN" to "🦅",     // Canisius Golden Griffins
    "FAIR" to "🦌",    // Fairfield Stags
    "NIAG" to "🟣",    // Niagara Purple Eagles
    "RIDER" to "🐎",   // Rider Broncs
    "SIE" to "🟢",     // Siena Saints

    "YALE" to "🐶",    // Yale Bulldogs
    "PRIN" to "🐅",    // Princeton Tigers
    "PENN" to "🔴",    // Penn Quakers
    "HARV" to "🍂",    // Harvard Crimson
    "COLU" to "🦁",    // Columbia Lions
    "CORN" to "🐻",    // Cornell Big Red
    "DART" to "🌲",    // Dartmouth Big Green
    "BRWN" to "🐻",    // Brown Bears

    "ARMY" to "🪖",    // Army Black Knights
    "NAVY" to "⚓",    // Navy Midshipmen
    "AFA" to "✈️",     // Air Force Falcons
    "BUCK" to "🦬",    // Bucknell Bison
    "LEH" to "🦅",     // Lehigh Mountain Hawks
    "LAF" to "🐆",     // Lafayette Leopards

    "CSUB" to "🏃",    // CSU Bakersfield Roadrunners
    "GCU" to "🦌",     // Grand Canyon Antelopes
    "SACST" to "🐝",   // Sacramento State Hornets
    "SELA" to "🦁",    // Southeastern Louisiana Lions
    "SFA" to "🪓",     // Stephen F. Austin Lumberjacks
    "UIW" to "🐦🔴",   // Incarnate Word Cardinals
)