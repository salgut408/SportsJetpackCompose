package com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common

import com.sgut.android.nationalfootballleague.utils.Constants.Companion.ATP
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.CHAMPIONS
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.CLUB_FRIENDLIES
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.EPL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FIFA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.FRA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.LA_LIGA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.MLB
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.MLS
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NBA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_BASEBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_BASKETBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_FOOTBALL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_HOCKEY
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NCAA_LACROSSE
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NFL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.NHL
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.UEFA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.UFC
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.WBC
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.WNBA
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.XFL
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import timber.log.Timber

object ScoreboardDataSerializer : KSerializer<ScoreboardData> {

    override val descriptor = buildClassSerialDescriptor("ScoreboardData")

    override fun deserialize(decoder: Decoder): ScoreboardData {
        val jsonDecoder = decoder as JsonDecoder
        val element = jsonDecoder.decodeJsonElement().jsonObject
        val slug = element["leagues"]
            ?.jsonArray
            ?.firstOrNull()
            ?.jsonObject
            ?.get("slug")
            ?.jsonPrimitive
            ?.content
            ?: throw SerializationException("SAL_GUT SCOREBOARD SERIALIZER - leagues[0].slug missing")

        Timber.d("SAL_GUT SCOREBOARD SERIALIZER SLUG - $slug")

        return when (slug) {
            NFL, NBA, NHL, NCAA_BASKETBALL, NCAA_FOOTBALL, WNBA, XFL, NCAA_HOCKEY, NCAA_LACROSSE ->
                jsonDecoder.json.decodeFromJsonElement(DefaultScoreboardData.serializer(), element)
            MLB, WBC, NCAA_BASEBALL ->
                jsonDecoder.json.decodeFromJsonElement(BaseballScoreboard.serializer(), element)
            ATP ->
                jsonDecoder.json.decodeFromJsonElement(TennisScoreboard.serializer(), element)
            UFC ->
                jsonDecoder.json.decodeFromJsonElement(MmaScoreboard.serializer(), element)
            MLS, FIFA, CHAMPIONS, EPL, FRA, UEFA, LA_LIGA, CLUB_FRIENDLIES ->
                jsonDecoder.json.decodeFromJsonElement(SoccerScoreboard.serializer(), element)
            else -> throw SerializationException("SAL_GUT SCOREBOARD SERIALIZER - Unknown slug: $slug")
        }
    }

    override fun serialize(encoder: Encoder, value: ScoreboardData) =
        throw UnsupportedOperationException("ScoreboardData serialization not supported")
}