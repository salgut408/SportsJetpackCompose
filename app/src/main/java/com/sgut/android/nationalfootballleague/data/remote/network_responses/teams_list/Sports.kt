package com.sgut.android.nationalfootballleague

import com.google.gson.annotations.SerializedName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models.SportModel


data class Sports(

  @SerializedName("id") val id: String = "",
  @SerializedName("uid") val uid: String = "",
  @SerializedName("name") val name: String = "",
  @SerializedName("slug") val slug: String = "",
  @SerializedName("leagues") val leagues: List<Leagues>?,

  )

fun Sports.toDomain(): SportModel {
  return SportModel(
    id = id,
    uid = uid,
    name = name,
    slug = slug,
    leagues = leagues?.map { it.asDomainModel() } ?: listOf(),
  )
}