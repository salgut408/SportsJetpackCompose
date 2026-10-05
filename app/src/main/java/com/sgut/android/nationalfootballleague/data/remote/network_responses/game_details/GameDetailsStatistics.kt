package com.sgut.android.nationalfootballleague

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsStatModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsStatisticModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Per-team stat group inside `boxscore.teams[].statistics`.
 *
 * For team sports ESPN returns grouped stats (e.g. baseball: "batting" /
 * "pitching" / "fielding"; football: "passing" / "rushing" / "defensive"; etc.)
 * with the actual numbers nested in [stats]. The original DTO only captured
 * the outer name/displayValue/label and silently dropped the [stats] array —
 * which is exactly where prediction-relevant abbreviations (OPS, WAR, ERA,
 * WHIP, etc.) live. New fields are additive with safe defaults so other sports
 * are unaffected if they don't return them.
 */
@Serializable
data class GameDetailsStatistics(
    @SerialName("name") val name: String? = null,
    @SerialName("displayName") val displayName: String? = null,
    @SerialName("displayValue") val displayValue: String? = null,
    @SerialName("label") val label: String? = null,
    @SerialName("stats") val stats: List<GameDetailsStatEntry> = listOf(),
)

@Serializable
data class GameDetailsStatEntry(
    @SerialName("name") val name: String = "",
    @SerialName("displayName") val displayName: String = "",
    @SerialName("shortDisplayName") val shortDisplayName: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("abbreviation") val abbreviation: String = "",
    @SerialName("type") val type: String = "",
    @SerialName("value") val value: Double = 0.0,
    @SerialName("displayValue") val displayValue: String = "",
)

fun GameDetailsStatistics.asDomain(): GameDetailsStatisticModel = GameDetailsStatisticModel(
    name = name ?: "",
    displayValue = displayValue ?: "",
    label = label ?: "",
    displayName = displayName ?: "",
    stats = stats.map { it.asDomain() },
)

fun GameDetailsStatEntry.asDomain(): GameDetailsStatModel = GameDetailsStatModel(
    name = name,
    displayName = displayName,
    shortDisplayName = shortDisplayName,
    description = description,
    abbreviation = abbreviation,
    type = type,
    value = value,
    displayValue = displayValue,
)