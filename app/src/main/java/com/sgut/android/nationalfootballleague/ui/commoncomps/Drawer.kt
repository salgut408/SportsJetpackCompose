package com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val screens = listOf("one", "two", "three")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDrawer() {
    ModalNavigationDrawer(
        drawerState = androidx.compose.material3.rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed),
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
            ) {
                SportsDrawer()
            }
        }
    ) {

    }
}



@Composable
fun SportsDrawer(modifier: Modifier = Modifier) {
    Column(modifier
        .fillMaxSize()
        .padding(start = 24.dp, top = 48.dp)) {
        for (screen in screens) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = screen)
        }
    }
}

@Preview
@Composable
fun DrawerPreview(){
    SportsDrawer()
}