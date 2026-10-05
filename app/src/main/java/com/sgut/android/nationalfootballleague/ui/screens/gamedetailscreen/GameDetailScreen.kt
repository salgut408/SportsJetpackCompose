package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.End
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.Start
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.StatusState
import com.sgut.android.nationalfootballleague.data.remote.network_responses.team_details_with_roster.detailswithroster.Details
import android.widget.TextView
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.GameDetailsTopBar
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.PressIconButton
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.ui.commoncomps.ui_extenstions.formatTo
import com.sgut.android.nationalfootballleague.ui.commoncomps.ui_extenstions.toDate
import java.util.*
import kotlin.math.nextUp
import timber.log.Timber
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.Dialog
import java.text.NumberFormat


/**
 * Route entry point. Owns the ViewModel, observes its state, triggers the
 * load, and forwards everything to the stateless [GameDetailsScreen].
 *
 * Layering mirrors the Home screen pattern:
 *   Route   → VM-aware, lifecycle-aware, side-effects only.
 *   Screen  → stateless dispatcher: takes a sealed UiState + callbacks, owns
 *             Scaffold/TopBar, branches Loading/Error/Content.
 *   Content → pure happy-path renderer of the unwrapped Content data.
 */
@Composable
fun GameDetailsRoute(
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    event: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    gameDetailViewModel: GameDetailViewModel = hiltViewModel(),
) {
    val state by gameDetailViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(sport, league, event) {
        gameDetailViewModel.loadGameDetails(sport, league, event)
    }

    GameDetailsScreen(
        modifier = modifier,
        state = state,
        canNavigateBack = canNavigateBack,
        navigateUp = navigateUp,
    )
}

/**
 * Stateless dispatcher: takes a [GameDetailsUiState] + nav callbacks, owns the
 * Scaffold and TopBar, then branches on the sealed state. The happy path
 * delegates to [GameDetailsContent] with the unwrapped Content data so that
 * the inner renderer never has to think about Loading/Error.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier,
    state: GameDetailsUiState,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val eventName = (state as? GameDetailsUiState.Content)
        ?.let { formatTeamNamesForTopBar(it.game) }
        .orEmpty()

    SportScaffold(
        topBar = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                GameDetailsTopBar(
                    eventName = eventName,
                    canNavigateBack = canNavigateBack,
                    navigateUp = navigateUp,
                    scrollBehavior = scrollBehavior,
                )

                val contentState = state as? GameDetailsUiState.Content
                if (contentState != null) {
                    val header = contentState.game.header ?: HeaderModel()
                    val competition = header.competitions.firstOrNull()
                    val statusState = competition?.status?.type?.statusState
                    val teamColors = gameTeamColors(contentState.game)
                    GameDetailsHeroHeader(
                        header = header,
                        colors = teamColors,
                        isLive = statusState == StatusState.IN,
                        middle = {
                            when (statusState) {
                                StatusState.PRE -> {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = competition.date?.toDate()?.formatTo("K:mm aa").orEmpty(),
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Text(
                                            text = contentState.game.pickcenter?.firstOrNull()?.details.orEmpty(),
                                            fontSize = 11.sp,
                                        )
                                    }
                                }
                                StatusState.IN -> {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(
                                            text = competition.status?.type?.description.orEmpty(),
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                        )
                                        Text(
                                            text = competition.status?.type?.shortGameTimeDetail.orEmpty(),
                                            fontSize = 11.sp,
                                        )
                                    }
                                }
                                StatusState.POST -> {
                                    Text(
                                        text = competition.status?.type?.description.orEmpty(),
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }
                                else -> Text(text = "")
                            }
                        },
                    )
                }
            }
        },
        content = { padding ->
            when (val s = state) {
                GameDetailsUiState.Loading -> {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(padding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        DataLoadingComponent()
                    }
                }
                is GameDetailsUiState.Error -> {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(padding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = s.message,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                is GameDetailsUiState.Content -> {
                    androidx.compose.runtime.CompositionLocalProvider(
                        com.sgut.android.nationalfootballleague.ui.navigation.LocalSportLeague provides
                            com.sgut.android.nationalfootballleague.ui.navigation.SportLeagueContext(
                                sport = s.sport,
                                league = s.league,
                            ),
                    ) {
                        GameDetailsContent(
                            modifier = modifier,
                            padding = padding,
                            sport = s.sport,
                            game = s.game,
                            players = s.players,
                        )
                    }
                }
            }
        },
    )
}

/**
 * Pure renderer of the unwrapped Content state. Takes the three fields out of
 * [GameDetailsUiState.Content] directly (sport, game, players) so this layer
 * never has to think about Loading/Error and can be reasoned about as a plain
 * function of its inputs.
 */
@Composable
private fun GameDetailsContent(
    modifier: Modifier,
    padding: PaddingValues,
    sport: String,
    game: GameDetailsModel,
    players: Map<String, GameDetailsAthleteDetailsModel>,
) {
    val teamColors = remember(game) { gameTeamColors(game) }
    val latestWinProb = game.winprobability.lastOrNull()
    val statusState = game.header?.competitions?.firstOrNull()?.status?.type?.statusState
    val competitors = game.header?.competitions?.firstOrNull()?.competitors
    val awayTeam = competitors?.lastOrNull()
    val homeTeam = competitors?.firstOrNull()
    val awayName = awayTeam?.team?.abbreviation.orEmpty()
    val homeName = homeTeam?.team?.abbreviation.orEmpty()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(padding),
        horizontalAlignment = Alignment.Start,
    ) {
        // Team-color accent under the hero — anchors the gradient into the
        // scrolling content as a thin, full-width signal element.
        TeamAccentStrip(
            colors = teamColors,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
        )

        // Operational notes (rain delay, postponements, etc.) — surfaced
        // at the very top so a user catching up on a delayed game sees the
        // status before the stats.
        if (game.notes.isNotEmpty()) {
            SectionSpacer()
            NotesBanner(notes = game.notes)
        }

        if (latestWinProb != null) {
            SectionSpacer()
            DefaultCard(modifier = modifier) {
                CardSectionHeader(
                    emoji = "📈",
                    title = "Win Probability",
                    subtitle = "Live model-implied chances",
                    accentColors = teamColors,
                )
                Spacer(modifier = Modifier.height(8.dp))
                WinProbabilityBar(winProbability = latestWinProb, colors = teamColors)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        SectionSpacer()
        when (sport) {
            "basketball", "football" -> AnimatedCircle(
                modifier = modifier,
                gameDetailModel = game,
                teamColors = teamColors,
            )
            "baseball" -> BaseballSpecific(
                modifier = modifier,
                gameDetailSituation = game.baseballSituation ?: SituationModel(),
                gameDetailsModel = game,
                teamMap = players,
                gameState = statusState,
                teamColors = teamColors,
            )
        }

        // Pre-game / model prediction card (distinct from live win-probability).
        if (game.predictor != null) {
            SectionSpacer()
            PredictorCard(
                predictor = game.predictor,
                awayTeamName = awayName,
                homeTeamName = homeName,
                teamColors = teamColors,
            )
        }

        SectionSpacer()
        WeightedRows(modifier = modifier, header = game, teamColors = teamColors)

        // Run-scoring timeline. For final / in-progress games this gives the
        // narrative arc at a glance.
        if (game.scoringPlays.isNotEmpty()) {
            SectionSpacer()
            ScoringPlaysCard(scoringPlays = game.scoringPlays, teamColors = teamColors)
        }

        // Baseball play-by-play (pitch-level). Baseball-only since the model
        // is sport-specific and other sports won't populate it.
        if (sport == "baseball" && game.baseballPlays.isNotEmpty()) {
            SectionSpacer()
            BaseballPlaysCard(plays = game.baseballPlays, teamColors = teamColors)
        }

        SectionSpacer()
        BoxScoreTeamStats(
            modifier = modifier,
            boxscore = game.boxscore ?: BoxScoreModel(),
            teamColors = teamColors,
        )

        // Head-to-head series score (e.g. "ATH wins series 3-1").
        if (game.seasonseries.isNotEmpty()) {
            SectionSpacer()
            SeasonSeriesCard(seasonSeries = game.seasonseries, teamColors = teamColors)
        }

        if (game.lastFiveGames.isNotEmpty()) {
            SectionSpacer()
            TabsLastFiveGames(
                modifier = modifier,
                lastFiveGames = game.lastFiveGames,
                teamColors = teamColors,
            )
        }

        if (game.leaders.isNotEmpty()) {
            SectionSpacer()
            SeasonLeaders(modifier = modifier, leaders = game.leaders, teamColors = teamColors)
        }

        if (game.leaders.isNotEmpty()) {
            SectionSpacer()
            TabsSeasonLeaders(modifier = modifier, leaders = game.leaders, teamColors = teamColors)
        }

        if (game.pickcenter.isNotEmpty()) {
            SectionSpacer()
            PickCenterList(modifier = Modifier, list = game.pickcenter, teamColors = teamColors)
        }

        if (game.odds.isNotEmpty()) {
            SectionSpacer()
            OddsCard(odds = game.odds, teamColors = teamColors)
        }

        if (game.againstTheSpread.isNotEmpty()) {
            SectionSpacer()
            AgainstTheSpreadCard(ats = game.againstTheSpread, teamColors = teamColors)
        }

        if (game.news?.articles?.isNotEmpty() == true) {
            SectionSpacer()
            NewsRailCard(news = game.news, teamColors = teamColors)
        }

        SectionSpacer()
        ExpandableGameArticle(modifier = modifier, gameDetailModel = game, teamColors = teamColors)

        if (game.videos.isNotEmpty()) {
            SectionSpacer()
            NewVidList(modifier = modifier, vidList = game.videos, teamColors = teamColors)
        }

        SectionSpacer()
        CompetitionStatus(
            modifier = modifier,
            competitions = game.header?.competitions ?: listOf(),
            teamColors = teamColors,
        )

        if (game.format != null) {
            SectionSpacer()
            GameFormatCard(format = game.format, teamColors = teamColors)
        }

        if (game.broadcasts.isNotEmpty()) {
            SectionSpacer()
            BroadcastsCard(broadcasts = game.broadcasts, teamColors = teamColors)
        }

        SectionSpacer()
        GameInformation(modifier = modifier, gameDetailModel = game, teamColors = teamColors)

        if (game.injuries.isNotEmpty()) {
            SectionSpacer()
            InjuriesReportCard(modifier = modifier, gameDetailModel = game, teamColors = teamColors)
        }

        SectionSpacer()
        FindTickets(
            modifier = modifier,
            ticketsInfo = game.ticketsInfo ?: TicketsInfoModel(),
            teamColors = teamColors,
        )

        SectionSpacer()
        TeamStatCard3(
            modifier = modifier,
            boxscore = game.boxscore ?: BoxScoreModel(),
            teamColors = teamColors,
        )

        SectionSpacer()
        HeaderStatusSlot(modifier = modifier, gameDetailModel = game, teamColors = teamColors)

        // "Updated 30s ago" chip footer.
        if (game.meta != null) {
            SectionSpacer()
            MetaFooter(meta = game.meta)
        }

        // Trailing breathing room so the last section isn't flush against
        // the system gesture area.
        Spacer(
            modifier = Modifier.height(GameDetailsSpacing.SectionGap),
        )
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, name = "Game Details — Loading")
@Composable
private fun GameDetailsScreenLoadingPreview() {
    GameDetailsScreen(
        state = GameDetailsUiState.Loading,
        canNavigateBack = true,
        navigateUp = {},
    )
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, name = "Game Details — Error")
@Composable
private fun GameDetailsScreenErrorPreview() {
    GameDetailsScreen(
        state = GameDetailsUiState.Error("Could not load game details. Check your connection."),
        canNavigateBack = true,
        navigateUp = {},
    )
}

/**
 * Builds the "Away @ Home" title string for the top bar from a game's header.
 * Pure function — no VM dependency, easy to test.
 */
private fun formatTeamNamesForTopBar(
    game: com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel,
): String {
    val competitors = game.header?.competitions?.firstOrNull()?.competitors
    val away = competitors?.lastOrNull()?.team?.name.orEmpty()
    val home = competitors?.firstOrNull()?.team?.name.orEmpty()
    return "$away @ $home"
}


// Baseball-specific stat abbreviations to display in dropdown

@Composable
fun BaseballRosterCard2(
    modifier: Modifier = Modifier,
    rosters: List<RostersModel>,
) {
    var selectedTab by remember { mutableStateOf(0) }

    DefaultCard(modifier = modifier.fillMaxWidth()) {
        Column {
            // Team tabs
            TabRow(selectedTabIndex = selectedTab) {
                rosters.forEachIndexed { index, rosterEntry ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                GenericImageLoader(
                                    obj = rosterEntry.team.logos,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                                Text(
                                    text = rosterEntry.team.abbreviation,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    )
                }
            }

            // Roster list for selected team
            rosters.getOrNull(selectedTab)?.let { rosterEntry ->
                // Header row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#",
                        modifier = Modifier.width(28.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "PLAYER",
                        modifier = Modifier.weight(1f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "POS",
                        modifier = Modifier.width(40.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider()

                rosterEntry.roster
                    .sortedBy { it.batOrder.takeIf { o -> o > 0 } ?: Int.MAX_VALUE }
                    .forEach { player ->
                        BaseballRosterPlayerRow(player = player)
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                    }
            }
        }
    }
}


