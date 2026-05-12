package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HeadlinesNetworkResponse(

    @SerialName("dataSourceIdentifier")
    val dataSourceIdentifier: String = "",
    @SerialName("keywords")
    val keywords: ArrayList<String> = arrayListOf(),
    @SerialName("description")
    val description: String = "",
    @SerialName("source")
    val source: String = "",
    @SerialName("video")
    val video: ArrayList<Video> = arrayListOf(),
    @SerialName("type")
    val type: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("links")
    val links: Links? = Links(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("headline")
    val headline: String = "",
    @SerialName("originallyPosted")
    val originallyPosted: String = "",
//    @SerialName("images"               ) val images               : ArrayList<Images>     = arrayListOf(),
    @SerialName("published")
    val published: String = "",
    @SerialName("lastModified")
    val lastModified: String = "",
    @SerialName("story")
    val story: String = "",

    )
