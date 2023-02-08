package com.sgut.android.nationalfootballleague


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sgut.android.nationalfootballleague.ui.theme.NationalFootballLeagueTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val DEBUG_TAG = "NetworkStatusExample"

        super.onCreate(savedInstanceState)
        setContent {
            NationalFootballLeagueTheme {
                EspnApp()
            }
        }
    }
}


