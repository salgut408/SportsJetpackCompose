package com.sgut.android.nationalfootballleague.ui.screens.teamdetails


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgut.android.nationalfootballleague.commoncomposables.InjuriesBox
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.VenueModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.HeadingSection
import com.sgut.android.nationalfootballleague.ui.commoncomps.TeamLogoDetailImageLoader
import com.sgut.android.nationalfootballleague.ui.commoncomps.VenueCardImageLoader
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.CardSectionHeader
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.GameTeamColors

@Composable
fun TeamDetailCard(
    team: FullTeamDetailWithRosterModel,
    modifier: Modifier,
) {
    val color = HexToJetpackColor2.getColor(team.color)
    val altcolor = HexToJetpackColor2.getColor(team.alternateColor)
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(color, altcolor))
            )
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            TeamLogoDetailImageLoader(team)

            Column() {
                Text(
                    text = team.displayName,
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    color = Color.White
                )
            }
        }

        if(team.standingSummary.isNotEmpty()){
            Text(
                text = team.standingSummary,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Left,
            )
        }

        VenueCard(
            venue = team.franchise.venue ?: VenueModel(),
            modifier = Modifier.fillMaxWidth(),
            teamColor = color,
            altColor = altcolor
        )

        HeadingSection(
            modifier = Modifier,
            title = "Athletes",
            subtitle =team.name,
           content = { AtheleteRow(team) }
        )

        team.nextEvent.map { nextEvent ->
            NextEvent(nextEvent = nextEvent, modifier = modifier, team = team)
        }
        InjuriesBox(
            stats = team.record.recordItems.getOrNull(0)?.summary.toString(), team
        )
    }
}


object HexToJetpackColor2 {
    fun getColor(colorString: String): Color {
        if (colorString.equals("")) {
            return Color.Red
        } else {
            return Color(android.graphics.Color.parseColor("#" + colorString))
        }
        return Color(android.graphics.Color.parseColor("#" + colorString))

    }
}


@Composable
fun VenueCard(
    venue: VenueModel,
    modifier: Modifier,
    teamColor: Color = Color.Black,
    altColor: Color = Color.DarkGray,
    accentColors: GameTeamColors? = null,
) {
    val city = venue.address?.city
    val state = venue.address?.state
    val subtitle = when {
        !city.isNullOrBlank() && !state.isNullOrBlank() -> "$city, $state"
        !city.isNullOrBlank() -> city
        else -> null
    }

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🏟",
            title = "Stadium",
            subtitle = subtitle,
            accentColors = accentColors,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(bottom = 12.dp),
        ) {
            VenueCardImageLoader(venue)

            // Bottom gradient overlay with team colors so the stadium name
            // stays legible regardless of the venue photo brightness.
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                teamColor.copy(alpha = 0.55f),
                                teamColor.copy(alpha = 0.88f),
                            ),
                        ),
                    ),
            )

            Text(
                text = venue.fullName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 14.dp, end = 14.dp, bottom = 12.dp),
            )
        }
    }
}


