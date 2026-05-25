package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

/**
 * A single baseball play (pitch / at-bat outcome). Returned by the summary
 * endpoint under the `plays` array — but ESPN's basketball plays have a
 * different shape, so this is the baseball-specific projection.
 *
 * Plays can represent:
 *  - Pitch events (Ball, Strike, Foul, In Play, etc.)
 *  - At-bat outcomes (Single, Strikeout, Walk, Hit by Pitch, etc.)
 *  - Inning transitions (Start of Inning, End of Inning)
 *
 * For analysis: every play has `atBatId` so plays can be grouped into at-bats,
 * and `pitchCount` / `resultCount` give the count BEFORE / AFTER the pitch.
 */
data class BaseballPlayModel(
    val id: String = "",
    val sequenceNumber: String = "",
    val type: BaseballPlayTypeModel = BaseballPlayTypeModel(),
    val text: String = "",
    val awayScore: Int = 0,
    val homeScore: Int = 0,
    val period: BaseballPlayPeriodModel = BaseballPlayPeriodModel(),
    val scoringPlay: Boolean = false,
    val scoreValue: Int = 0,
    val teamId: String = "",
    val wallclock: String = "",
    val atBatId: String = "",
    val summaryType: String = "",                 // "P" (pitch) / "I" (inning) / etc.
    val pitchCount: BaseballPitchCountModel? = null,
    val resultCount: BaseballPitchCountModel? = null,
    val outs: Int = 0,
)

data class BaseballPlayTypeModel(
    val id: String = "",
    val text: String = "",
    val type: String = "",                        // e.g. "start-inning", "ball", "strike-looking"
)

data class BaseballPlayPeriodModel(
    val type: String = "",                        // "Top" | "Bottom"
    val number: Int = 0,                          // inning number
    val displayValue: String = "",                // e.g. "1st Inning"
)

data class BaseballPitchCountModel(
    val balls: Int = 0,
    val strikes: Int = 0,
)