package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.tennis


import com.google.gson.annotations.SerializedName

data class Linescore(
    @SerializedName("tiebreak")
    val tiebreak: Int = 0,
    @SerializedName("value")
    val value: Double = 0.0,
    @SerializedName("winner")
    val winner: Boolean = false
)