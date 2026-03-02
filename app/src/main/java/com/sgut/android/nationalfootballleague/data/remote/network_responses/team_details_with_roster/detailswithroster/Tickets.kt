package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.TicketsModel


data class Tickets3(

  @SerializedName("id")
  val id: String? = null,
  @SerializedName("summary")
  val summary: String? = null,
  @SerializedName("description")
  val description: String? = null,
  @SerializedName("maxPrice")
  val maxPrice: Double? = null,
  @SerializedName("startingPrice")
  val startingPrice: Double? = null,
  @SerializedName("numberAvailable")
  val numberAvailable: Int? = null,
  @SerializedName("totalPostings")
  val totalPostings: Int? = null,
  @SerializedName("links")
  val links: List<Links3> = listOf(),

  )

fun Tickets3.asDomain() : TicketsModel {
  return  TicketsModel(
    id = id ?: "",
    maxPrice = maxPrice ?: 0.0,
    description = description ?: "",
    startingPrice = startingPrice ?: 0.0,
    numberAvailable = numberAvailable ?: 0,
    links = links
  )
}
