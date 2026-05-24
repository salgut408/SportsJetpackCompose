package com.sgut.android.nationalfootballleague.utils

import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASEBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.BASKETBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FOOTBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.HOCKEY
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.RACING
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.SOCCER
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.TENNIS

/**
 * Maps an ESPN sport slug to a representative emoji. Falls back to a generic
 * stadium glyph for sports we haven't mapped explicitly.
 */
fun sportEmoji(sportSlug: String): String = when (sportSlug) {
    BASEBALL -> "⚾"
    BASKETBALL -> "🏀"
    FOOTBALL -> "🏈"
    SOCCER -> "⚽"
    HOCKEY -> "🏒"
    TENNIS -> "🎾"
    RACING -> "🏎️"
    "mma", "ufc" -> "🥊"
    "golf" -> "⛳"
    "lacrosse" -> "🥍"
    else -> "🏟️"
}