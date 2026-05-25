package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.AthletesRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.VenueModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_schedule.ScheduleDomainModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.team_stats_models.TeamStatsModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.GeneralImageLoader
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.SectionSpacer
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.TeamAccentStrip
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 56.dp
private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 0.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 100.dp


@Composable
fun NewTeamDetailCard(
    team: FullTeamDetailWithRosterModel,
    roster: List<AthletesRosterModel>,
    schedule: ScheduleDomainModel,
    stats: TeamStatsModel,
    modifier: Modifier,
) {
    val color = HexToJetpackColor2.getColor(team.color)
    val altcolor = HexToJetpackColor2.getColor(team.alternateColor)

    Box(
        modifier.fillMaxSize()
    ) {
        val scroll = rememberScrollState(0)
        TeamHeader(team = team)
        Body(roster = roster, team = team, scroll = scroll, teamSchedule = schedule, stats = stats)
        Title(team = team) { scroll.value }
        MainImage(team = team) { scroll.value }
    }

}


@Composable
fun TeamHeader(
    team: FullTeamDetailWithRosterModel,
) {
    val color = HexToJetpackColor2.getColor(team.color)
    val altcolor = HexToJetpackColor2.getColor(team.alternateColor)
    Spacer(
        modifier = Modifier
            .height(300.dp)
            .fillMaxSize()
            .background(Brush.horizontalGradient(listOf(color, altcolor)))
    )
}


@Composable
fun Title(
    team: FullTeamDetailWithRosterModel,
    scrollProvider: () -> Int,
) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val color = HexToJetpackColor2.getColor(team.color)
    val altcolor = HexToJetpackColor2.getColor(team.alternateColor)

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .fillMaxWidth()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(altcolor.copy(alpha = 0.97f))
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 12.dp)
    ) {
        Text(
            text = team.displayName,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.displaySmall,
            color = color
        )
        Text(
            text = team.nickname,
            style = MaterialTheme.typography.headlineSmall,
            color = color.copy(alpha = 0.75f),
            textAlign = TextAlign.Left,
        )
    }


}


@Composable
fun MainImage(
    team: FullTeamDetailWithRosterModel,
    scrollProvider: () -> Int,
) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }
    CollapsingTeamLayout(
        collapseFractionProvider = collapseFractionProvider,
    ) {
        GeneralImageLoader(
            href = team.logos.getOrNull(0)?.href ?: "",
            contentDescription = team.name,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun CollapsingTeamLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,

        ) { measurables, constraints ->
        check(measurables.size == 1)
        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth).toDp()
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth).toDp()
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth.roundToPx(),
            imageWidth.roundToPx()))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth.roundToPx()).toDp() / 2, // centered when expanded
            (constraints.maxWidth - imageWidth.roundToPx()).toDp(), // right aligned when collapsed
            collapseFraction
        )

        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth.roundToPx()
        ) {
            imagePlaceable.placeRelative(imageX.roundToPx(), imageY)
        }

    }
}

@Composable
fun Body(
    roster: List<AthletesRosterModel>,
    team: FullTeamDetailWithRosterModel,
    teamSchedule: ScheduleDomainModel,
    stats: TeamStatsModel,
    scroll: ScrollState,
) {
    val color = HexToJetpackColor2.getColor(team.color)
    val altColor = HexToJetpackColor2.getColor(team.alternateColor)
    val accentColors = teamDetailColors(team)

    Column(
        modifier = Modifier
            .verticalScroll(scroll)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Reserve space for the hero header / title / image (drawn behind by
        // NewTeamDetailCard via overlapping Boxes).
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )

        Column {
            Spacer(Modifier.height(ImageOverlap))
            Spacer(Modifier.height(TitleHeight))
            Spacer(Modifier.height(300.dp))

            // Team-color accent strip — anchors the gradient header into the
            // scrolling body the same way Game Details does.
            TeamAccentStrip(
                colors = accentColors,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            )

            SectionSpacer()
            VenueCard(
                venue = team.franchise.venue ?: VenueModel(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                teamColor = color,
                altColor = altColor,
                accentColors = accentColors,
            )

            SectionSpacer()
            TabLayout(
                team = team,
                modifier = Modifier.padding(horizontal = 8.dp),
                people = roster,
                stats = stats,
            )

            team.nextEvent.forEach { nextEvent ->
                SectionSpacer()
                NextEvent(
                    nextEvent = nextEvent,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    team = team,
                )
            }

            SectionSpacer()
            TeamRecord(
                record = team.record,
                modifier = Modifier.padding(horizontal = 8.dp),
                teamColor = color,
                accentColors = accentColors,
            )

            SectionSpacer()
            PastGames(
                schedule = teamSchedule,
                teamColor = color,
                altColor = altColor,
                accentColors = accentColors,
            )

            SectionSpacer()
            InjuriesCard(
                team = team,
                modifier = Modifier.padding(horizontal = 8.dp),
            )

            // Trailing breathing room so the last section isn't flush against
            // the system gesture area.
            Spacer(
                modifier = Modifier
                    .padding(bottom = BottomBarHeight)
                    .navigationBarsPadding()
                    .height(16.dp)
            )
        }
    }
}


