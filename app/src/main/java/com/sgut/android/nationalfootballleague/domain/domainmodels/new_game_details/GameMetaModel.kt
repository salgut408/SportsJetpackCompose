package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

/**
 * Metadata about the game itself: when it last got updated by ESPN, when the
 * first/last play happened in wall-clock time, what state the game is in.
 *
 * Useful for:
 *  - Cache TTL decisions (how stale is this data?)
 *  - Live-game freshness indicators in the UI ("updated 30s ago")
 *  - Confidence scoring in predictive algorithms (stale data → lower weight)
 */
data class GameMetaModel(
    val gameState: String = "",                // "pre" | "in" | "post"
    val gameSwitcherEnabled: Boolean = false,
    val lastUpdatedAt: String = "",            // ISO-8601 timestamp
    val firstPlayWallClock: String = "",       // ISO-8601 timestamp; empty for unplayed games
    val lastPlayWallClock: String = "",        // ISO-8601 timestamp
    val syncUrl: String = "",                  // optional live sync URL
)