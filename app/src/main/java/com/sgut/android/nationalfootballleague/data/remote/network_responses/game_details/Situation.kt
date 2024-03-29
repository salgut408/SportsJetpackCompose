package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import com.google.gson.annotations.SerializedName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*


data class Situation(

    @SerializedName("lastPlay")
    val lastPlay: LastPlay? = LastPlay(),
    @SerializedName("balls")
    val balls: Int? = null,
    @SerializedName("strikes")
    val strikes: Int? = null,
    @SerializedName("outs")
    val outs: Int? = null,
    @SerializedName("pitcher")
    val pitcher: Pitcher? = Pitcher(),
    @SerializedName("batter")
    val batter: Batter? = Batter(),
    @SerializedName("dueUp")
    val dueUp: List<DueUpItem> = listOf(),
    @SerializedName("onSecond")
    val onSecond: OnSecond? = OnSecond(),
    @SerializedName("onFirst")
    val onFirst: OnFirst? = OnFirst(),
    @SerializedName("onThird")
    val onThird: OnThird? = OnThird(),
)

data class SituationScoreboard(

    @SerializedName("lastPlay")
    val lastPlay: LastPlay? = LastPlay(),
    @SerializedName("balls")
    val balls: Int? = null,
    @SerializedName("strikes")
    val strikes: Int? = null,
    @SerializedName("outs")
    val outs: Int? = null,
    @SerializedName("pitcher")
    val pitcher: Pitcher? = Pitcher(),
    @SerializedName("batter")
    val batter: Batter? = Batter(),
    @SerializedName("dueUp")
    val dueUp: List<DueUpItem> = listOf(),
    @SerializedName("onSecond")
    val onSecond: Boolean? = false,
    @SerializedName("onFirst")
    val onFirst: Boolean = false,
    @SerializedName("onThird")
    val onThird: Boolean? = false,
)

data class OnThird(
    val playerId: Int? = null,

    )

fun OnThird.asDomain(): OnThirdModel {
    return OnThirdModel(
        playerId = playerId
    )
}

data class OnSecond(
    val playerId: Int? = null,
)

data class OnFirst(
    val playerId: Int? = null,
)
fun OnFirst.asDomain(): OnFirstModel {
    return OnFirstModel(
        playerId = playerId
    )
}

fun OnSecond.asDomain(): OnSecondModel {
    return OnSecondModel(
        playerId = playerId
    )
}

data class DueUpItem(
    @SerializedName("playerId")
    val playerId: String = "",
    @SerializedName("batOrder")
    val batOrder: String = "",

    )

fun DueUpItem.asDomain(): DueUpItemModel {
    return DueUpItemModel(
        playerId = playerId,
        batOrder = batOrder
    )
}


fun Situation.asDomain(): SituationModel {
    return SituationModel(
        lastPlay = lastPlay?.asDomain(),
        balls = balls ?: 0,
        strikes = strikes ?: 0,
        outs = outs ?: 0,
        pitcher = pitcher?.asDomain(),
        batter = batter?.asDomain(),
        dueUp = dueUp.map { it.asDomain() },
        onSecond = onSecond?.asDomain()
    )
}

data class LastPlay(

    @SerializedName("id")
    val id: String? = null,
    val text: String = ""

    )

fun LastPlay.asDomain(): LastPlayModel {
    return LastPlayModel(
        id = id ?: "",
        text = text
    )
}

data class Pitcher(
    @SerializedName("playerId")
    val playerId: Int? = null,
)

fun Pitcher.asDomain(): PitcherModel {
    return PitcherModel(
        playerId = playerId ?: 0
    )
}

data class Batter(
    @SerializedName("playerId")
    val playerId: Int? = null,

    )

fun Batter.asDomain(): BatterModel {
    return BatterModel(
        playerId = playerId
    )
}
