package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName


data class ArticleWeb(

  @SerializedName("leagues")
  val leagues: ArticleLeagues? = ArticleLeagues(),

  )