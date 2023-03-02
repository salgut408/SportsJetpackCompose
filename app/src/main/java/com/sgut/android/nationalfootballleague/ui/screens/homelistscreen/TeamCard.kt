package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.GenericImageLoader
import com.sgut.android.nationalfootballleague.ui.navigation.NavigationScreens
import com.sgut.android.nationalfootballleague.domain.domainmodels.TeamDomainModel
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2

@Composable
fun TeamCard(
    team: TeamDomainModel,
    modifier: Modifier,
    navController: NavController,
    sport: String,
    league: String,
) {
    val color = HexToJetpackColor2.getColor(team.color)

    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(NavigationScreens.DetailScreenTeam.withArgs(team.abbreviation,
                    sport,
                    league))
            }
    ) {

        Box(
            modifier = Modifier.background(
                Brush.verticalGradient(
                    listOf(color, Color.White)
                )
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()) {

                GenericImageLoader(
                    obj = team.logos?.getOrNull(0)?.href ?: "",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(8.dp)
                )

                Text(
                    text = team.shortDisplayName,
                    color = Color(color.value),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )

                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    contentColor = Color.Black,
                    containerColor = Color.White
                ) {
                   Icon(Icons.Default.Add, null)
                }
                
            }
        }
    }
}


































