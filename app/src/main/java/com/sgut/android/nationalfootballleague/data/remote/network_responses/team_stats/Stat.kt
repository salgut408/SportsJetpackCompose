package com.sgut.android.nationalfootballleague.data.remote.network_responses.team_stats


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.StatModel
import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    @SerialName("abbreviation")
    val abbreviation: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("displayValue")
    val displayValue: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("value")
    val value: Double = 0.0
)

fun Stat.asDomain(): StatModel {
    return StatModel(
        abbreviation = abbreviation,
        description = description,
        displayName = displayName,
        displayValue = displayValue,
        name = name,
        shortDisplayName = shortDisplayName,
        value = value
    )
}