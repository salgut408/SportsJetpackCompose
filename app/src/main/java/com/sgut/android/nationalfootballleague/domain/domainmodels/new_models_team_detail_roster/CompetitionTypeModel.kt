package com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster

import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.StatusState
interface DefaultTypeInterface{
    val id: String
}

data class CompetitionTypeModel(
   override var id: String = "",
    var name: String = "",
    var state: StatusState? = StatusState.PRE,
    var completed: Boolean = false,
    var description: String = "",
    var detail: String = "",
    var shortDetail: String = "",
    ) : DefaultTypeInterface
