package com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details

import kotlinx.serialization.SerialName
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import kotlinx.serialization.Serializable


@Serializable
data class Situation(

    @SerialName("lastPlay")
    val lastPlay: LastPlay? = LastPlay(),
    @SerialName("balls")
    val balls: Int? = null,
    @SerialName("strikes")
    val strikes: Int? = null,
    @SerialName("outs")
    val outs: Int? = null,
    @SerialName("pitcher")
    val pitcher: Pitcher? = Pitcher(),
    @SerialName("batter")
    val batter: Batter? = Batter(),
    @SerialName("dueUp")
    val dueUp: List<DueUpItem> = listOf(),
    @SerialName("onSecond")
    val onSecond: OnSecond? = OnSecond(),
    @SerialName("onFirst")
    val onFirst: OnFirst? = OnFirst(),
    @SerialName("onThird")
    val onThird: OnThird? = OnThird(),
)

@Serializable
data class SituationScoreboard(

    @SerialName("lastPlay")
    val lastPlay: LastPlay? = LastPlay(),
    @SerialName("balls")
    val balls: Int? = null,
    @SerialName("strikes")
    val strikes: Int? = null,
    @SerialName("outs")
    val outs: Int? = null,
    @SerialName("pitcher")
    val pitcher: Pitcher? = Pitcher(),
    @SerialName("batter")
    val batter: Batter? = Batter(),
    @SerialName("dueUp")
    val dueUp: List<DueUpItem> = listOf(),
    @SerialName("onSecond")
    val onSecond: Boolean? = false,
    @SerialName("onFirst")
    val onFirst: Boolean = false,
    @SerialName("onThird")
    val onThird: Boolean? = false,
)

@Serializable
data class OnThird(
    val playerId: Int? = null,

    )

fun OnThird.asDomain(): OnThirdModel {
    return OnThirdModel(
        playerId = playerId
    )
}

@Serializable
data class OnSecond(
    val playerId: Int? = null,
)

@Serializable
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

@Serializable
data class DueUpItem(
    @SerialName("playerId")
    val playerId: String = "",
    @SerialName("batOrder")
    val batOrder: String = "",
    @SerialName("athlete")
    val athlete: SituationAthlete? = null,
)

@Serializable
data class SituationAthlete(
    @SerialName("id")
    val id: String = "",
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("displayName")
    val displayName: String = "",
    @SerialName("shortName")
    val shortName: String = "",
    @SerialName("jersey")
    val jersey: String? = null,
    @SerialName("headshot")
    val headshot: String? = null,
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

@Serializable
data class LastPlay(

    @SerialName("id")
    val id: String? = null,
    val text: String = ""

    )

fun LastPlay.asDomain(): LastPlayModel {
    return LastPlayModel(
        id = id ?: "",
        text = text
    )
}

@Serializable
data class Pitcher(
    @SerialName("playerId")
    val playerId: Int? = null,
    @SerialName("athlete")
    val athlete: SituationAthlete? = null,
    @SerialName("summary")
    val summary: String? = null,
)

fun Pitcher.asDomain(): PitcherModel {
    return PitcherModel(
        playerId = playerId ?: 0
    )
}

@Serializable
data class Batter(
    @SerialName("playerId")
    val playerId: Int? = null,
    @SerialName("athlete")
    val athlete: SituationAthlete? = null,
    @SerialName("summary")
    val summary: String? = null,
)

fun Batter.asDomain(): BatterModel {
    return BatterModel(
        playerId = playerId
    )
}
