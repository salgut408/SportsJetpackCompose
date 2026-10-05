package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TeamOdds (

  @SerialName("preMatchMoneyLineAway"      ) var preMatchMoneyLineAway      : PreMatchMoneyLineAway?      = PreMatchMoneyLineAway(),
  @SerialName("preMatchMoneyLineHome"      ) var preMatchMoneyLineHome      : PreMatchMoneyLineHome?      = PreMatchMoneyLineHome(),
  @SerialName("preMatchSpreadHandicapAway" ) var preMatchSpreadHandicapAway : PreMatchSpreadHandicapAway? = PreMatchSpreadHandicapAway(),
  @SerialName("preMatchSpreadHome"         ) var preMatchSpreadHome         : PreMatchSpreadHome?         = PreMatchSpreadHome(),
  @SerialName("preMatchWinningMarginHome"  ) var preMatchWinningMarginHome  : PreMatchWinningMarginHome?  = PreMatchWinningMarginHome(),
  @SerialName("preMatchTotalOver"          ) var preMatchTotalOver          : PreMatchTotalOver?          = PreMatchTotalOver(),
  @SerialName("preMatchWinningMarginOther" ) var preMatchWinningMarginOther : PreMatchWinningMarginOther? = PreMatchWinningMarginOther(),
  @SerialName("preMatchSpreadAway"         ) var preMatchSpreadAway         : PreMatchSpreadAway?         = PreMatchSpreadAway(),
  @SerialName("preMatchWinningMarginAway"  ) var preMatchWinningMarginAway  : PreMatchWinningMarginAway?  = PreMatchWinningMarginAway(),
  @SerialName("preMatchTotalUnder"         ) var preMatchTotalUnder         : PreMatchTotalUnder?         = PreMatchTotalUnder(),
  @SerialName("preMatchTotalHandicap"      ) var preMatchTotalHandicap      : PreMatchTotalHandicap?      = PreMatchTotalHandicap(),
  @SerialName("preMatchSpreadHandicapHome" ) var preMatchSpreadHandicapHome : PreMatchSpreadHandicapHome? = PreMatchSpreadHandicapHome()

)