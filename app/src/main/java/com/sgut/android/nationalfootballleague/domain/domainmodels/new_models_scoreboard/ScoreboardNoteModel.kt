package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard

/**
 * A competition-level note from ESPN — most commonly used for postponements,
 * suspensions, or special series context (e.g. "Game 3 of NLCS").
 *
 * Example payload: `{ "type": "event", "headline": "Rain - Makeup date Aug 17" }`
 */
data class ScoreboardNoteModel(
    val type: String = "",
    val headline: String = "",
)