package com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.InningPrefix
data class GameDetailsStatusModel (
    val type: GameDetailsTypeModel? = GameDetailsTypeModel(),
    val periodPrefix: InningPrefix? = InningPrefix.PRE
)