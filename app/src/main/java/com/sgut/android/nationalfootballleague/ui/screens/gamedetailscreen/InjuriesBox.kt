package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.InjuredAtheleteRow

@Composable
fun InjuriesBox(stats: String, team: FullTeamDetailWithRosterModel) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Box(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            .background(Color.LightGray).fillMaxWidth(),

    ) {
        Row() {
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "Injuries")
                if(expanded){
                    Text(text = "Injuries:", style = MaterialTheme.typography.titleSmall,)
                    InjuredAtheleteRow(team)
                }
            }
            ElevatedButton(
                onClick = { expanded = !expanded}
            ) {
                Text(if (expanded){
                    "Show less"
                } else {
                    "Show Injuries"
                }
                )


            }
        }
    }
}