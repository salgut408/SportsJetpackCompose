package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

/**
 * Top-level stat group in `boxscore.teams[].statistics`. For team sports, ESPN
 * returns groups like "batting" / "pitching" / "fielding" (baseball) or
 * "team" (basketball/football). Each group's actual numbers live in [stats].
 *
 * The previous shape (name + displayValue + label) was a flatter assumption
 * that silently dropped the richer per-stat data ESPN returns under `stats`.
 * Both old fields are kept for back-compat; new prediction-relevant fields are
 * additive.
 */
data class GameDetailsStatisticModel(
    val name: String = "",                                  // group key, e.g. "batting"
    val displayValue: String = "",                          // present on some sports' flatter shapes
    val label: String = "",                                 // present on some sports' flatter shapes
    val displayName: String = "",                           // human-readable group name, e.g. "Batting"
    val stats: List<GameDetailsStatModel> = emptyList(),    // the actual stat entries
)

/**
 * A single stat entry: one row in the table that lives under
 * [GameDetailsStatisticModel.stats]. The `abbreviation` is what you'd key off
 * for prediction features ("OPS", "ERA", "WHIP", "WAR", etc.).
 */
data class GameDetailsStatModel(
    val name: String = "",
    val displayName: String = "",
    val shortDisplayName: String = "",
    val description: String = "",
    val abbreviation: String = "",
    val type: String = "",
    val value: Double = 0.0,
    val displayValue: String = "",
)