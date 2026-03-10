package com.sgut.android.nationalfootballleague.data.remote.network_responses.standings


import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.standings_models.LogoModel
import kotlinx.serialization.Serializable

@Serializable
data class Logo(
    @SerialName("alt")
    val alt: String = "",
    @SerialName("height")
    val height: Int = 0,
    @SerialName("href")
    val href: String = "",
    @SerialName("lastUpdated")
    val lastUpdated: String = "",
    @SerialName("rel")
    val rel: List<String> = listOf(),
    @SerialName("width")
    val width: Int = 0
)

fun Logo.asDomain(): LogoModel {
    return LogoModel(
        alt = alt,
        height = height,
        href = href,
        lastUpdated = lastUpdated,
        width = width
    )
}