package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

/**
 * Head-to-head series context returned by the game-details summary endpoint.
 * Multiple entries per game: typically one for "current series", one for the
 * regular-season aggregate, and one for postseason if applicable.
 *
 * For betting/prediction work this is one of the highest-signal features —
 * recent head-to-head record between two teams is a classic predictor.
 */
data class SeasonSeriesModel(
    val type: String = "",                // "current" | "regular-season" | "post-season"
    val title: String = "",
    val description: String = "",
    val summary: String = "",             // e.g. "ATH wins series 3-1"
    val completed: Boolean = false,
    val totalCompetitions: Int = 0,
    val seriesScore: String = "",         // e.g. "3-1"
    val events: List<SeasonSeriesEventModel> = listOf(),
)

/**
 * A single prior matchup between the two teams. Lightweight slice — id + date +
 * status + competitors — enough to render a recap row or feed a model.
 */
data class SeasonSeriesEventModel(
    val id: String = "",
    val uid: String = "",
    val date: String = "",
    val status: String = "",              // "pre" | "in" | "post"
    val competitors: List<SeasonSeriesCompetitorModel> = listOf(),
)

data class SeasonSeriesCompetitorModel(
    val homeAway: String = "",
    val winner: Boolean = false,
    val score: String = "",
    val teamId: String = "",
    val teamAbbreviation: String = "",
    val teamDisplayName: String = "",
)