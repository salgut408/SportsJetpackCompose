package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.ResultsModel
import kotlinx.serialization.Serializable

@Serializable
data class Results(
    @SerialName("splits")
    val splits: List<Split> = listOf(),
    @SerialName("stats")
    val stats: Stats = Stats()
)
fun Results.asDomain(): ResultsModel {
    return ResultsModel(
        splits = splits.map { it.asDomain() },
        stats = stats.asDomain()
    )
}