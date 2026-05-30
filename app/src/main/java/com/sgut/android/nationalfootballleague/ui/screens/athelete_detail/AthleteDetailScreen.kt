package com.sgut.android.nationalfootballleague.ui.screens.athelete_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sgut.android.nationalfootballleague.di.ToolBar2
import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.AthleteStatModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.full_athlete.FullAthleteModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.GenericImageLoader
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.CardSectionHeader
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.GameTeamColors
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.SectionLabel
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.SectionSpacer
import com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen.SectionValue

/**
 * Route → Screen → Content layering, same as the rest of the app.
 *
 *   Route   — VM-aware, runs the load LaunchedEffect.
 *   Screen  — stateless dispatcher: Scaffold + TopBar + Loading/Error/Content.
 *   Content — pure renderer of [FullAthleteModel].
 */
@Composable
fun AthleteDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: AthleteDetailViewModel = hiltViewModel(),
    sport: String,
    league: String,
    athleteId: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(sport, league, athleteId) {
        viewModel.loadAthlete(sport, league, athleteId)
    }

    AthleteDetailScreen(
        modifier = modifier,
        state = state,
        canNavigateBack = canNavigateBack,
        navigateUp = navigateUp,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AthleteDetailScreen(
    modifier: Modifier = Modifier,
    state: AthleteDetailScreenUiState,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val title = (state as? AthleteDetailScreenUiState.Content)?.athlete?.displayName.orEmpty()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ToolBar2(
                title = title,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                scrollBehavior = scrollBehavior,
            )
        },
        content = { padding ->
            when (val s = state) {
                AthleteDetailScreenUiState.Loading -> Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center,
                ) { CircularProgressIndicator() }

                is AthleteDetailScreenUiState.Error -> Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = s.message, style = MaterialTheme.typography.bodyMedium)
                }

                is AthleteDetailScreenUiState.Content -> AthleteDetailContent(
                    modifier = Modifier.padding(padding),
                    athlete = s.athlete,
                )
            }
        },
    )
}

@Composable
private fun AthleteDetailContent(
    modifier: Modifier,
    athlete: FullAthleteModel,
) {
    val colors = remember(athlete) { athleteColors(athlete) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        AthleteHero(athlete = athlete, colors = colors)

        if (athlete.stats.isNotEmpty()) {
            SectionSpacer()
            StatsSummaryCard(athlete = athlete, colors = colors)
        }

        SectionSpacer()
        BioCard(athlete = athlete, colors = colors)

        Spacer(modifier = Modifier.height(28.dp))
    }
}

/** Team-gradient hero with the player's headshot, name, number, and position. */
@Composable
private fun AthleteHero(
    athlete: FullAthleteModel,
    colors: GameTeamColors,
) {
    val gradient = Brush.linearGradient(
        colors = listOf(colors.homePrimary, colors.homeAlternate),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
    )
    val onColor = if (colors.homePrimary.luminance() > 0.5f) Color(0xFF111111) else Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradient),
    ) {
        // Dark scrim so a near-white team color still reads.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Black.copy(alpha = 0.05f), Color.Black.copy(alpha = 0.30f)),
                    ),
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // Headshot on a translucent disc.
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(athlete.headshotHref)
                            .crossfade(true)
                            .build(),
                        contentDescription = athlete.displayName,
                        modifier = Modifier.size(104.dp).clip(CircleShape),
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = athlete.displayName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        color = onColor,
                        lineHeight = 28.sp,
                    )
                    // Jersey · position
                    Text(
                        text = listOfNotNull(
                            athlete.jersey.takeIf { it.isNotBlank() }?.let { "#$it" },
                            athlete.positionAbbreviation.takeIf { it.isNotBlank() }
                                ?: athlete.positionName.takeIf { it.isNotBlank() },
                        ).joinToString("  ·  "),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        color = onColor.copy(alpha = 0.85f),
                    )
                    // Team row
                    if (athlete.team.displayName.isNotBlank()) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            if (athlete.team.logoHref.isNotBlank()) {
                                GenericImageLoader(
                                    obj = athlete.team.logoHref,
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                            Text(
                                text = athlete.team.displayName,
                                fontSize = 12.sp,
                                color = onColor.copy(alpha = 0.85f),
                            )
                        }
                    }
                    // Status pill (active / injured / etc.)
                    if (athlete.statusName.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White.copy(alpha = 0.22f))
                                .padding(horizontal = 10.dp, vertical = 3.dp),
                        ) {
                            Text(
                                text = athlete.statusName.uppercase(),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.5.sp,
                                color = onColor,
                            )
                        }
                    }
                }
            }
        }
    }
}

/** Headline stats as a horizontally-scrolling strip of big-number tiles. */
@Composable
private fun StatsSummaryCard(
    athlete: FullAthleteModel,
    colors: GameTeamColors,
) {
    DefaultCard(modifier = Modifier) {
        CardSectionHeader(
            emoji = "📈",
            title = athlete.statsSummaryLabel.ifBlank { "Stats" },
            subtitle = "Season highlights",
            accentColors = colors,
        )
        LazyRow(
            modifier = Modifier.padding(top = 4.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(athlete.stats) { stat ->
                StatTile(stat = stat, accent = colors.homePrimary)
            }
        }
    }
}

@Composable
private fun StatTile(stat: AthleteStatModel, accent: Color) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(accent.copy(alpha = 0.08f))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stat.displayValue.ifBlank { stat.value.toString() },
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = stat.abbreviation.ifBlank { stat.shortDisplayName }.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            color = accent,
        )
        if (stat.rankDisplayValue.isNotBlank()) {
            Text(
                text = stat.rankDisplayValue,
                fontSize = 9.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/** Two-column bio facts (height/weight/age/born/college/draft/experience/bats-throws). */
@Composable
private fun BioCard(
    athlete: FullAthleteModel,
    colors: GameTeamColors,
) {
    val facts = buildList {
        if (athlete.displayHeight.isNotBlank()) add("Height" to athlete.displayHeight)
        if (athlete.displayWeight.isNotBlank()) add("Weight" to athlete.displayWeight)
        if (athlete.age > 0) add("Age" to athlete.age.toString())
        if (athlete.displayDOB.isNotBlank()) add("Born" to athlete.displayDOB)
        if (athlete.displayBirthPlace.isNotBlank()) add("Birthplace" to athlete.displayBirthPlace)
        if (athlete.collegeName.isNotBlank()) add("College" to athlete.collegeName)
        if (athlete.displayExperience.isNotBlank()) add("Experience" to athlete.displayExperience)
        if (athlete.displayDraft.isNotBlank()) add("Draft" to athlete.displayDraft)
        if (athlete.displayBatsThrows.isNotBlank()) add("Bats/Throws" to athlete.displayBatsThrows)
    }
    if (facts.isEmpty()) return

    DefaultCard(modifier = Modifier) {
        CardSectionHeader(
            emoji = "👤",
            title = "Bio",
            accentColors = colors,
        )
        Column(modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)) {
            facts.forEachIndexed { index, (label, value) ->
                if (index > 0) {
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SectionLabel(text = label)
                    SectionValue(text = value)
                }
            }
        }
    }
}

// ----- helpers -----

private fun athleteColors(athlete: FullAthleteModel): GameTeamColors {
    val primary = parseHex(athlete.team.color, default = Color(0xFF1D2D44))
    val alt = parseHex(athlete.team.alternateColor, default = Color(0xFF0B1320))
    return GameTeamColors(
        awayPrimary = primary,
        awayAlternate = alt,
        homePrimary = primary,
        homeAlternate = alt,
    )
}

private fun parseHex(hex: String, default: Color): Color =
    runCatching {
        if (hex.isBlank()) default else Color(android.graphics.Color.parseColor("#$hex"))
    }.getOrDefault(default)

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, name = "Athlete — Loading")
@Composable
private fun AthleteDetailScreenLoadingPreview() {
    AthleteDetailScreen(
        state = AthleteDetailScreenUiState.Loading,
        canNavigateBack = true,
        navigateUp = {},
    )
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, name = "Athlete — Error")
@Composable
private fun AthleteDetailScreenErrorPreview() {
    AthleteDetailScreen(
        state = AthleteDetailScreenUiState.Error("Couldn't load athlete details. Please try again."),
        canNavigateBack = true,
        navigateUp = {},
    )
}
