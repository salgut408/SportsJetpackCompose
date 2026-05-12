package com.sgut.android.nationalfootballleague

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.AddressModel
import kotlinx.serialization.Serializable


//same as
@Serializable
data class GameDetailsAddress(

  @SerialName("city")
  val city: String = "",
  @SerialName("state")
  val state: String = "",
  @SerialName("zipCode")
  val zipCode: String = "",

  )

fun GameDetailsAddress.asDomain(): AddressModel {
    return AddressModel(
      city = city ,
      state = state ,
      zipCode = zipCode
    )
}