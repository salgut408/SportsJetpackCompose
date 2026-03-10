package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.StatModel
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
    @SerialName("id")
    val id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("shortDisplayName")
    val shortDisplayName: String = "",
    @SerialName("summary")
    val summary: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("value")
    val value: Double = 0.0
)

fun Stat.asDomain(): StatModel {
    return StatModel(
        abbreviation = abbreviation,
        description = description,
        displayValue = displayValue,
        displayName = displayName,
        id = id,
        name = name,
        shortDisplayName = shortDisplayName,
        summary = summary,
        type = type,
        value = value
    )
}