package com.sgut.android.nationalfootballleague

import android.content.res.Resources
import androidx.compose.foundation.layout.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sgut.android.nationalfootballleague.ui.application.EspnAppState
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.Navigation
import com.sgut.android.nationalfootballleague.ui.navigation.NavigationScreens
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EspnApp(
    modifier: Modifier = Modifier,
    ) {


    val appState = rememberAppState()
    val backStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: NavigationScreens.MainScreenTeamsList.route
    val scaffoldState = rememberScaffoldState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()



    Scaffold(
        bottomBar = {}

    ) { innerPadding ->
        Navigation(appState, padding = innerPadding)
    }
}


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) =
    remember(navController, resources, coroutineScope) {
        EspnAppState(navController, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}


