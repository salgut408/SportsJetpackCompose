package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameMetaModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameMeta(
    @SerialName("gameState") val gameState: String = "",
    @SerialName("gameSwitcherEnabled") val gameSwitcherEnabled: Boolean = false,
    @SerialName("lastUpdatedAt") val lastUpdatedAt: String = "",
    @SerialName("firstPlayWallClock") val firstPlayWallClock: String = "",
    @SerialName("lastPlayWallClock") val lastPlayWallClock: String = "",
    @SerialName("syncUrl") val syncUrl: String = "",
)

fun GameMeta.asDomain(): GameMetaModel = GameMetaModel(
    gameState = gameState,
    gameSwitcherEnabled = gameSwitcherEnabled,
    lastUpdatedAt = lastUpdatedAt,
    firstPlayWallClock = firstPlayWallClock,
    lastPlayWallClock = lastPlayWallClock,
    syncUrl = syncUrl,
)