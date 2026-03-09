package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.NextEventModel
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate


@Composable
fun NextEvent(
    nextEvent: NextEventModel,
    modifier: Modifier,
    team: FullTeamDetailWithRosterModel,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    val altColor = HexToJetpackColor2.getColor(team.alternateColor)
    val venue = nextEvent.competitions.firstOrNull()?.venue

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            // Team-colored header strip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.horizontalGradient(listOf(teamColor, altColor)))
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Next Game",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Surface(
                    color = Color.White.copy(alpha = 0.20f),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = nextEvent.date.toDate()?.formatTo("MMM dd") ?: "",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            // Body
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(teamColor.copy(alpha = 0.05f))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = nextEvent.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (venue != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = teamColor,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = venue.fullName,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = teamColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = nextEvent.date.toDate()?.formatTo("EEEE, MMM dd · h:mm a") ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}