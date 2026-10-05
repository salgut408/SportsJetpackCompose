package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.StatusState
import com.sgut.android.nationalfootballleague.data.emojis.teamEmoji
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.TennisScoreboard
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.SituationScoreboard
import com.sgut.android.nationalfootballleague.ui.commoncomps.TopAppBarWithLogo
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.DefaultScoreboardEventModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitionModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitorsModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.EIGHT
import com.sgut.android.nationalfootballleague.ui.commoncomps.LeagueSelectionRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.BasicImage
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.SpacerDp
import com.sgut.android.nationalfootballleague.ui.commoncomps.TeamLogoScoreboardImageLoader
import com.sgut.android.nationalfootballleague.ui.commoncomps.PillKind
import com.sgut.android.nationalfootballleague.ui.commoncomps.ShimmerBox
import com.sgut.android.nationalfootballleague.ui.commoncomps.StatusPill
import com.sgut.android.nationalfootballleague.ui.screens.homelistscreen.NewsRow
import com.sgut.android.nationalfootballleague.ui.screens.shared_viewmodels.SelectionViewModel
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.Theme
import com.sgut.android.nationalfootballleague.utils.sportEmoji
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.utils.Constants
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.TENNIS
import com.sgut.android.nationalfootballleague.ui.commoncomps.ui_extenstions.formatTo
import com.sgut.android.nationalfootballleague.ui.commoncomps.ui_extenstions.toDate
import timber.log.Timber


@Composable
fun ScoreboardRoute(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
    selectionViewModel: SelectionViewModel,
    scoreboardViewModel: ScoreboardViewModel = hiltViewModel(),
) {
    val selectionUiState by selectionViewModel.sport.collectAsStateWithLifecycle()
    val sportSlug = selectionUiState.slug
    val leagueSlug = selectionUiState.league.slug

    val state by scoreboardViewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing by scoreboardViewModel.isRefreshing.collectAsStateWithLifecycle()

    LaunchedEffect(sportSlug, leagueSlug) {
        if (leagueSlug.isNotBlank()) {
            scoreboardViewModel.loadScoreboard(sportSlug, leagueSlug)
        }
    }

    // The tennis ordering hack lives at the route level because it touches both VMs.
    val onLeagueSelected: (String, String) -> Unit = { sport, league ->
        if (sport == TENNIS) {
            // TODO FIX bc first we call setDifferentSport so it can be null and show tennis
            selectionViewModel.setDifferentSport(sport, league)
            scoreboardViewModel.loadScoreboard(sport, league)
        } else {
            selectionViewModel.setDifferentSport(sport, league)
        }
    }

    ScoreboardScreen(
        state = state,
        isRefreshing = isRefreshing,
        canNavigateBack = canNavigateBack,
        navigateUp = navigateUp,
        onRefresh = {
            if (leagueSlug.isNotBlank()) {
                scoreboardViewModel.loadScoreboard(sportSlug, leagueSlug, isRefresh = true)
            }
        },
        onClearError = scoreboardViewModel::clearError,
        onLeagueSelected = onLeagueSelected,
        onNavigateToGame = onNavigateToGame,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreboardScreen(
    state: ScoreboardUiState,
    isRefreshing: Boolean,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onRefresh: () -> Unit,
    onClearError: () -> Unit,
    onLeagueSelected: (sport: String, league: String) -> Unit,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val snackbarHostState = remember { SnackbarHostState() }

    // Surface error events as a transient snackbar without blocking the screen.
    val errorMessage = (state as? ScoreboardUiState.Error)?.message
    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            snackbarHostState.showSnackbar(message = errorMessage, duration = SnackbarDuration.Long)
            onClearError()
        }
    }

    // Derive title/logo from the Content state (empty during Loading/Error).
    val title = (state as? ScoreboardUiState.Content)?.defaultScoreboard?.league?.abbreviation.orEmpty()
    val logoUrl = when (state) {
        is ScoreboardUiState.Content ->
            state.abstractScoreData?.league?.firstOrNull()?.logos?.firstOrNull()?.href
                ?: state.defaultScoreboard.league.logos.getOrNull(0)?.href
                ?: ""
        else -> ""
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarWithLogo(
                title = title,
                logo = logoUrl,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                scrollBehavior = scrollBehavior,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
        when (val s = state) {
            ScoreboardUiState.Loading -> ScoreboardSkeleton(padding = innerPadding)
            is ScoreboardUiState.Error -> ScoreboardSkeleton(padding = innerPadding)
            is ScoreboardUiState.Content -> PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                ScoreboardContent(
                    state = s,
                    padding = innerPadding,
                    onLeagueSelected = onLeagueSelected,
                    onNavigateToGame = onNavigateToGame,
                )
            }
        }
    }
}

@Composable
private fun ScoreboardContent(
    state: ScoreboardUiState.Content,
    padding: PaddingValues,
    onLeagueSelected: (sport: String, league: String) -> Unit,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        LeagueSelectionRow(
            leagues = Constants.LIST_OF_LEAGUE_PAIRS,
            padding = padding,
            onLeagueSelected = onLeagueSelected,
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                Scoreboard(
                    events = state.defaultScoreboard.events,
                    modifier = Modifier,
                    sport = state.sport,
                    league = state.league,
                    onNavigateToGame = onNavigateToGame,
                    scoreboardData = state.abstractScoreData,
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                NewsRow(
                    news = state.articles,
                    modifier = Modifier.wrapContentSize(),
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun ScoreboardSkeleton(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = padding.calculateTopPadding())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(Modifier.height(8.dp))
        // League selector row placeholder
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(5) {
                ShimmerBox(
                    modifier = Modifier
                        .width(72.dp)
                        .height(32.dp),
                    shape = RoundedCornerShape(16.dp),
                )
            }
        }
        // Scoreboard card placeholder
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(12.dp),
        )
        // News card placeholder
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            shape = RoundedCornerShape(12.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScoreboardScreenLoadingPreview() {
    Theme {
        ScoreboardScreen(
            state = ScoreboardUiState.Loading,
            isRefreshing = false,
            canNavigateBack = true,
            navigateUp = {},
            onRefresh = {},
            onClearError = {},
            onLeagueSelected = { _, _ -> },
            onNavigateToGame = { _, _, _ -> },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScoreboardScreenErrorPreview() {
    Theme {
        ScoreboardScreen(
            state = ScoreboardUiState.Error("Couldn't load scoreboard for mlb"),
            isRefreshing = false,
            canNavigateBack = true,
            navigateUp = {},
            onRefresh = {},
            onClearError = {},
            onLeagueSelected = { _, _ -> },
            onNavigateToGame = { _, _, _ -> },
        )
    }
}


@Composable
fun Scoreboard(
    events: List<DefaultScoreboardEventModel>,
    sport: String,
    league: String,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
    modifier: Modifier,
    scoreboardData: ScoreboardData?,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CardHeaderText(text = "Scoreboard", emoji = sportEmoji(sport))
        if (events.isEmpty()) {
            ScoreboardEmptyState(sport = sport)
        } else {
            events.forEach { event ->
                if (event.competitions.isEmpty()) {
                    TennisScoreboardHeader(scoreboardData)
                } else {
                    NewEventMatchup(
                        event = event,
                        modifier = Modifier,
                        sport = sport,
                        league = league,
                        onNavigateToGame = onNavigateToGame,
                    )
                }
            }
        }
    }
}

@Composable
private fun ScoreboardEmptyState(sport: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = sportEmoji(sport),
            fontSize = 40.sp,
        )
        Text(
            text = "No games today",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "Check back later for the next matchup.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun TennisScoreboardHeader(scoreboardData: ScoreboardData?) {
        scoreboardData?.events?.map { eventData ->
            eventData.competitions?.map { competition ->
                Text(text = competition.startDate)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Timber.d("SAL_WTF ${competition.competitors.firstOrNull()?.team?.name }")

                    Text(text = competition.competitors.firstOrNull()?.team?.name ?: "null")
                    Text(text = competition.competitors.getOrNull(1)?.team?.name ?: "null")
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = scoreboardData?.league?.firstOrNull()?.name ?: "")
            Text(text = scoreboardData?.day?.date ?: "")
            Text(text = scoreboardData?.events?.firstOrNull()?.name ?: "")

        }
}




@Composable
fun CompetitorRow(
    competitor: ScoreboardCompetitorsModel?,
    sport: String,
    league: String,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BasicImage(
                imgUrl = competitor?.team?.logo ?: "",
                contentDescription = competitor?.team?.name ?: "",
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                borderWidth = 0.dp,
                borderColor = Color.Transparent,
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier.size(30.dp)
            )
            val emoji = teamEmoji(
                sport = sport,
                league = league,
                teamAbbreviation = competitor?.team?.abbreviation.orEmpty(),
            )
            if (emoji != null) {
                Text(text = emoji, fontSize = 16.sp)
            }
            Text(
                text = competitor?.team?.shortDisplayName ?: "",
                fontWeight = if (competitor?.winner == true) FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp,
            )
        }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
//            Text(text = competitor.score,  fontSize = 16.sp)
            content()
        }

    }
}


/**
 * Look up a stat by its ESPN abbreviation (e.g. "H", "R", "E", "AVG").
 * Returns null when the team hasn't accumulated this stat yet
 * (PRE games, or sports that don't expose the stat).
 */
private fun statValue(
    competitor: ScoreboardCompetitorsModel?,
    abbreviation: String,
): String? = competitor?.statistics
    ?.firstOrNull { it.abbreviation == abbreviation }
    ?.displayValue
    ?.takeIf { it.isNotBlank() }

private data class PillDescriptor(
    val kind: PillKind,
    val label: String,
    val emoji: String? = null,
)

/**
 * Maps an ESPN event's raw status to a display-ready pill descriptor.
 * Handles delay/suspension variants which keep state==IN but render differently
 * (yellow, no pulsing dot, weather-specific emoji).
 */
private fun eventToPill(event: DefaultScoreboardEventModel): PillDescriptor {
    val type = event.status.type
    val state = type?.state
    val name = type?.name.orEmpty()
    val startDate = event.competitions.firstOrNull()?.startDate

    // Delay-like statuses come back as state == IN but the game isn't being played.
    val isDelayed = state == StatusState.IN && (
        name.contains("DELAY") || name == "STATUS_SUSPENDED"
    )

    return when {
        // Name-based statuses first — they override state-based mapping.
        // ESPN returns state="post" for postponed games, so checking state alone
        // would incorrectly classify them as FINAL.
        name == "STATUS_POSTPONED" -> PillDescriptor(PillKind.POSTPONED, "POSTPONED", "📅")
        name == "STATUS_CANCELED" || name == "STATUS_CANCELLED" ->
            PillDescriptor(PillKind.CANCELED, "CANCELED", emoji = "❌")
        isDelayed -> {
            val emoji = when {
                name.contains("RAIN") -> "🌧️"
                name.contains("LIGHTNING") -> "⚡"
                name.contains("FOG") -> "🌫️"
                else -> "⏸"
            }
            PillDescriptor(PillKind.DELAYED, type.shortDetail.ifBlank { type.description }.ifBlank { "Delayed" }, emoji)
        }
        state == StatusState.IN -> PillDescriptor(PillKind.LIVE, "LIVE")
        state == StatusState.POST -> PillDescriptor(PillKind.FINAL, "FINAL", emoji = "✅")
        state == StatusState.PRE -> PillDescriptor(
            PillKind.SCHEDULED,
            startDate?.toDate()?.formatTo("h:mm a") ?: "TBD",
            emoji = "📅",
        )
        else -> PillDescriptor(PillKind.NEUTRAL, type?.description?.uppercase() ?: "TBD")
    }
}

@Composable
fun NewEventMatchup(
    event: DefaultScoreboardEventModel,
    modifier: Modifier,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
    sport: String,
    league: String,
) {
    val pill = eventToPill(event)
    val isLiveBaseball = sport == Constants.BASEBALL && pill.kind == PillKind.LIVE
    // Auto-expand for live MLB games — that's where the rich data lives.
    var expanded by remember(event.id) { mutableStateOf(isLiveBaseball) }

    val homeCompetitor = event.competitions.firstOrNull()?.competitors?.lastOrNull()
    val awayCompetitor = event.competitions.firstOrNull()?.competitors?.firstOrNull()

    Card(
        onClick = { onNavigateToGame(sport, league, event.id) },
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            // Header: status pill on the right, expand chevron on the far right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StatusPill(kind = pill.kind, label = pill.label, emoji = pill.emoji)
                if (isLiveBaseball) {
                    IconButton(
                        onClick = { expanded = !expanded },
                        modifier = Modifier.size(28.dp),
                    ) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = if (expanded) "Collapse" else "Expand",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }

            CompetitorRow(
                competitor = homeCompetitor,
                sport = sport,
                league = league,
                modifier = Modifier,
            ) {
                TeamScoreSlot(competitor = homeCompetitor, kind = pill.kind)
            }
            CompetitorRow(
                competitor = awayCompetitor,
                sport = sport,
                league = league,
                modifier = Modifier,
            ) {
                TeamScoreSlot(competitor = awayCompetitor, kind = pill.kind)
            }

            // Surface competition notes (e.g. "Rain - Makeup date Aug 17" for postponed games).
            val noteHeadline = event.competitions.firstOrNull()
                ?.notes
                ?.firstOrNull { it.headline.isNotBlank() }
                ?.headline
            if (!noteHeadline.isNullOrBlank()) {
                Text(
                    text = noteHeadline,
                    fontSize = 11.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 6.dp),
                )
            }

            // Live-baseball situation card — collapsible
            AnimatedVisibility(
                visible = expanded && isLiveBaseball,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                CompetitionSituation(
                    situation = event.competitions.firstOrNull()?.situation,
                    homeCompetitor = homeCompetitor,
                    awayCompetitor = awayCompetitor,
                )
            }
        }
    }
}

/**
 * Right-hand cell of a CompetitorRow. The kind decides the shape:
 *  - SCHEDULED / POSTPONED / CANCELED → show the team's season record summary
 *    (a 0-0 "score" would be misleading for games that haven't happened)
 *  - LIVE / DELAYED / FINAL → show the score plus a small "H X · E Y" stats line
 *
 * Hits/errors are filtered from `competitor.statistics`. The secondary line is
 * skipped entirely when neither stat is present, so non-baseball sports stay
 * compact.
 */
@Composable
private fun TeamScoreSlot(
    competitor: ScoreboardCompetitorsModel?,
    kind: PillKind,
) {
    val showRecords = kind == PillKind.SCHEDULED ||
        kind == PillKind.POSTPONED ||
        kind == PillKind.CANCELED
    if (showRecords) {
        Text(text = competitor?.records?.getOrNull(0)?.summary ?: "")
        return
    }

    val hits = statValue(competitor, "H")
    val errors = statValue(competitor, "E")
    val hasStats = hits != null || errors != null

    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = competitor?.score ?: "",
            fontWeight = FontWeight.Bold,
        )
        if (hasStats) {
            Text(
                text = buildString {
                    if (hits != null) append("H $hits")
                    if (hits != null && errors != null) append(" · ")
                    if (errors != null) append("E $errors")
                },
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}


@Composable
fun CompetitionSituation(
    situation: SituationScoreboard?,
    modifier: Modifier = Modifier,
    homeCompetitor: ScoreboardCompetitorsModel? = null,
    awayCompetitor: ScoreboardCompetitorsModel? = null,
) {
    BaseballLiveSituation(
        situation = situation,
        homeCompetitor = homeCompetitor,
        awayCompetitor = awayCompetitor,
        modifier = modifier,
    )
}



