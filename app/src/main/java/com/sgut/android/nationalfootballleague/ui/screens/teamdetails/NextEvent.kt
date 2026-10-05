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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.NextEventModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.CardSectionHeader
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate


@Composable
fun NextEvent(
    nextEvent: NextEventModel,
    modifier: Modifier,
    team: FullTeamDetailWithRosterModel,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    val venue = nextEvent.competitions.firstOrNull()?.venue
    val accentColors = teamDetailColors(team)

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "⏰",
            title = "Next Game",
            subtitle = nextEvent.date.toDate()?.formatTo("EEEE · MMM dd · h:mm a"),
            accentColors = accentColors,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(teamColor.copy(alpha = 0.05f))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = nextEvent.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )

            if (venue != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = teamColor,
                        modifier = Modifier.size(14.dp),
                    )
                    Text(
                        text = venue.fullName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}