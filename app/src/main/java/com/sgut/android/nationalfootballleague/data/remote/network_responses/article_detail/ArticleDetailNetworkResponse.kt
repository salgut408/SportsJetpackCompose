package com.sgut.android.nationalfootballleague.data.remote.network_responses.article_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleDetailNetworkResponse(

    @SerialName("resultsOffset")
    val resultsOffset: Int = 0,
    @SerialName("resultsCount")
    val resultsCount:  Int = 0,
//    @SerialName("headlines"     )
//    val headlines     : ArrayList<Headlines> = arrayListOf(),
    @SerialName("resultsLimit")
    val resultsLimit:  Int = 0,
    @SerialName("timestamp")
    val timestamp: String = "",
    @SerialName("status")
    val status: String = "",

    )
