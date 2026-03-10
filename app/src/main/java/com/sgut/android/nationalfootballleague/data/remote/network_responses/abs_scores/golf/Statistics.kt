package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.golf


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Statistics(
    @SerialName("categories")
    val categories: List<Category> = listOf()
)