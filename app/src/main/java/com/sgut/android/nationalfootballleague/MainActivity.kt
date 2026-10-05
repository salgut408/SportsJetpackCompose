package com.sgut.android.nationalfootballleague


import com.sgut.android.nationalfootballleague.ui.application.EspnApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Theme {
                EspnApp()
            }
        }
    }
}







