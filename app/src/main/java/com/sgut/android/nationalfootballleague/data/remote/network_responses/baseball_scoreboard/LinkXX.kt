package com.sgut.android.nationalfootballleague.data.remote.network_responses.baseball_scoreboard


import com.google.gson.annotations.SerializedName

data class LinkXX(
    @SerializedName("href")
    val href: String? = "",
    @SerializedName("isExternal")
    val isExternal: Boolean? = false,
    @SerializedName("isPremium")
    val isPremium: Boolean? = false,
    @SerializedName("rel")
    val rel: List<String?>? = listOf(),
    @SerializedName("text")
    val text: String? = ""
)