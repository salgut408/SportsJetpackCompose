package com.sgut.android.nationalfootballleague.utils

import android.util.Log
import com.sgut.android.nationalfootballleague.R

fun Any?.printToLog(tag: String = "DEBUG_LOG") {
    Log.d(tag, toString())
}

class Constants {
    companion object {
        const val BASE_URL = "https://site.api.espn.com/apis/"
        // Sports
        const val BASEBALL = "baseball"
        const val FOOTBALL = "football"
        const val SOCCER = "soccer"
        const val BASKETBALL = "basketball"
        const val HOCKEY = "hockey"
        const val RACING = "racing"
        const val TENNIS = "tennis"

        // Leagues
        const val FIFA = "fifa.world"
        const val NFL = "nfl"
        const val XFL = "xfl"
        const val WBC = "world-baseball-classic"
        const val MLB = "mlb"
        const val LA_LIGA = "esp.1"
        const val EPL = "eng.1"
        const val FRA = "fra.1"
        const val UEFA = "uefa.europa"
        const val NBA = "nba"
        const val NHL = "nhl"
        const val WNBA = "wnba"
        const val MLS = "usa.1"
        const val CFL = "cfl"
        const val CHAMPIONS = "uefa.champions"
        const val NCAA_FOOTBALL = "college-football"
        const val NCAA_BASKETBALL = "mens-college-basketball"
        const val NCAA_BASEBALL = "college-baseball"
        const val NCAA_HOCKEY = "mens-college-hockey"
        const val NCAA_LACROSSE = "mens-college-lacrosse"
        const val LACROSSE = "lacrosse"
        const val F1 = "f1"
        const val ATP = "atp"
        const val CLUB_FRIENDLIES = "CLUB.FRIENDLY"
        const val UFC = "ufc"

        val LIST_OF_LEAGUE_PAIRS = listOf(
            Pair(BASEBALL to MLB, R.string.MLB_league),
            Pair(FOOTBALL to NFL, R.string.NFL_League),
            Pair(HOCKEY to NHL, R.string.NHL_league),
            Pair(BASKETBALL to NBA, R.string.NBA_league),
            Pair(BASKETBALL to WNBA, R.string.WNBA_league),
            Pair(FOOTBALL to NCAA_FOOTBALL, R.string.NCAA_football),
            Pair(BASEBALL to NCAA_BASEBALL, R.string.NCAA_baseball),
            Pair(BASKETBALL to NCAA_BASKETBALL, R.string.NCAA_mens_basketball),
            Pair(TENNIS to ATP, R.string.atp),
            Pair(BASEBALL to WBC, R.string.WBC_league),
            Pair(SOCCER to FRA, R.string.fra),
            Pair(SOCCER to CHAMPIONS, R.string.champions),
            Pair(SOCCER to MLS, R.string.MLS_league),
            Pair(SOCCER to FIFA, R.string.world_cup),
            Pair(SOCCER to LA_LIGA, R.string.la_liga),
            Pair(SOCCER to EPL, R.string.premier_league),
            Pair(SOCCER to UEFA, R.string.euro_soccer),
            Pair(FOOTBALL to XFL, R.string.XFL_League),
            Pair(RACING to F1, R.string.F1_RACING),
        )
    }
}