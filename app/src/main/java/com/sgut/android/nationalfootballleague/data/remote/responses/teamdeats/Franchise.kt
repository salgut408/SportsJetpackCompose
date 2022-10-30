package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName


data class Franchise (

  @SerializedName("\$ref"             ) var ref             : String?  = null,
  @SerializedName("id"               ) var id               : String?  = null,
  @SerializedName("uid"              ) var uid              : String?  = null,
  @SerializedName("slug"             ) var slug             : String?  = null,
  @SerializedName("location"         ) var location         : String?  = null,
  @SerializedName("name"             ) var name             : String?  = null,
  @SerializedName("abbreviation"     ) var abbreviation     : String?  = null,
  @SerializedName("displayName"      ) var displayName      : String?  = null,
  @SerializedName("shortDisplayName" ) var shortDisplayName : String?  = null,
  @SerializedName("color"            ) var color            : String?  = null,
  @SerializedName("isActive"         ) var isActive         : Boolean? = null,
  @SerializedName("venue"            ) var venue            : Venue?   = Venue(),
  @SerializedName("team"             ) var team2             : Team2?    = Team2()

)