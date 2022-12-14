package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName


data class Pickcenter (

  @SerializedName("provider"     ) var provider     : Provider?         = Provider(),
  @SerializedName("details"      ) var details      : String?           = null,
  @SerializedName("overUnder"    ) var overUnder    : Float?              = null,
  @SerializedName("spread"       ) var spread       : Float?              = null,
  @SerializedName("awayTeamOdds" ) var awayTeamOdds : AwayTeamOdds?     = AwayTeamOdds(),
  @SerializedName("homeTeamOdds" ) var homeTeamOdds : HomeTeamOdds?     = HomeTeamOdds(),
  @SerializedName("links"        ) var links        : ArrayList<String> = arrayListOf()

)