package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.sgut.android.nationalfootballleague.data.emojis.teamEmoji
import com.sgut.android.nationalfootballleague.ui.commoncomps.ToolBar3
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.LeagueModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.SportModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamModel
import com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen.ArticleRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.BasicImage
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.LeagueSelectionRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.ShimmerBox
import com.sgut.android.nationalfootballleague.ui.commoncomps.SportScaffold
import com.sgut.android.nationalfootballleague.ui.commoncomps.rememberRelativeTime
import com.sgut.android.nationalfootballleague.ui.screens.shared_viewmodels.SelectionViewModel
import com.sgut.android.nationalfootballleague.ui.screens.standings_screen.Standings
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.Theme
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.LIST_OF_LEAGUE_PAIRS
import com.sgut.android.nationalfootballleague.utils.sportEmoji
import com.sgut.android.nationalfootballleague.R.string as AppText


@Composable
fun HomeRoute(
    selectionViewModel: SelectionViewModel,
    onNavigateToScoreboard: (sport: String, league: String) -> Unit,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
) {
    val state by selectionViewModel.homeUiState.collectAsStateWithLifecycle()
    val errorMessage by selectionViewModel.errorMessage.collectAsStateWithLifecycle()
    val isRefreshing by selectionViewModel.isRefreshing.collectAsStateWithLifecycle()
    val lastUpdatedMs by selectionViewModel.lastUpdatedMs.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        selectionViewModel.ensureDefaultSportSelected()
    }

    HomeScreen(
        state = state,
        errorMessage = errorMessage,
        isRefreshing = isRefreshing,
        lastUpdatedMs = lastUpdatedMs,
        onRefresh = selectionViewModel::refresh,
        onClearError = selectionViewModel::clearError,
        onLeagueSelected = selectionViewModel::setDifferentSport,
        onNavigateToScoreboard = onNavigateToScoreboard,
        onNavigateToTeam = onNavigateToTeam,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    errorMessage: String?,
    isRefreshing: Boolean,
    lastUpdatedMs: Long?,
    onRefresh: () -> Unit,
    onClearError: () -> Unit,
    onLeagueSelected: (sport: String, league: String) -> Unit,
    onNavigateToScoreboard: (sport: String, league: String) -> Unit,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            snackbarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Long,
            )
            onClearError()
        }
    }

    SportScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ToolBar3(
                title = when (state) {
                    is HomeUiState.Content -> state.sport.league.name
                    HomeUiState.Loading -> ""
                },
                scrollBehavior = scrollBehavior,
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { padding ->
            when (val s = state) {
                HomeUiState.Loading -> HomeContentSkeleton(padding = padding)
                is HomeUiState.Content -> PullToRefreshBox(
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    HomeContent(
                        sport = s.sport,
                        articles = s.articles,
                        padding = padding,
                        lastUpdatedMs = lastUpdatedMs,
                        onLeagueSelected = onLeagueSelected,
                        onNavigateToScoreboard = onNavigateToScoreboard,
                        onNavigateToTeam = onNavigateToTeam,
                    )
                }
            }
        },
    )
}

@Composable
private fun HomeContent(
    sport: SportModel,
    articles: ArticlesListModel,
    padding: PaddingValues,
    lastUpdatedMs: Long?,
    onLeagueSelected: (sport: String, league: String) -> Unit,
    onNavigateToScoreboard: (sport: String, league: String) -> Unit,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
) {
    val relativeTime = rememberRelativeTime(lastUpdatedMs)

    Column(modifier = Modifier.fillMaxSize()) {
        LeagueSelectionRow(
            leagues = LIST_OF_LEAGUE_PAIRS,
            padding = padding,
            onLeagueSelected = onLeagueSelected,
        )
        if (relativeTime != null) {
            Text(
                text = relativeTime,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
            )
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                Button(
                    onClick = { onNavigateToScoreboard(sport.slug, sport.league.slug) },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = stringResource(AppText.scores_games))
                }
            }
            item {
                TeamsListCircleRow(
                    teams = sport.league.teams,
                    sport = sport.slug,
                    league = sport.league.slug,
                    onNavigateToTeam = onNavigateToTeam,
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { NewsRow(news = articles, modifier = Modifier.wrapContentSize()) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Standings(
                    sport = sport.slug,
                    league = sport.league.slug,
                    type = "0",
                )
            }
        }
    }
}

@Composable
private fun HomeContentSkeleton(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = padding.calculateTopPadding())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(Modifier.height(8.dp))
        // "Scores & Games" button placeholder
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
        )
        // Team circles row placeholder
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            repeat(6) {
                ShimmerBox(
                    modifier = Modifier.size(56.dp),
                    shape = CircleShape,
                )
            }
        }
        // News card placeholder
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            shape = RoundedCornerShape(12.dp),
        )
        // Standings card placeholder
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(12.dp),
        )
    }
}

@Composable
private fun TeamsListCircleRow(
    teams: List<TeamModel>,
    sport: String,
    league: String,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = league, emoji = sportEmoji(sport))
        NormalDivider()
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(teams) { team ->
                TeamItem(
                    team = team,
                    sport = sport,
                    league = league,
                    onNavigateToTeam = onNavigateToTeam,
                )
            }
        }
    }
}

@Composable
fun NewsRow(news: ArticlesListModel, modifier: Modifier) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = news.header, emoji = "📰")
        NormalDivider()
        ArticleRow(articleList = news.articles)
    }
}

@Composable
fun LabelText(@StringRes stringResId: Int) {
    Text(
        text = stringResource(id = stringResId),
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
private fun TeamItem(
    team: TeamModel,
    sport: String,
    league: String,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    val altColor = HexToJetpackColor2.getColor(team.alternateColor)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(68.dp)
            .clickable { onNavigateToTeam(team.abbreviation, sport, league) }
    ) {
        Box(contentAlignment = Alignment.Center) {
            // Colored circle background
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(teamColor, CircleShape)
            )
            // Thin alt-color ring
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color.Transparent, CircleShape)
                    .padding(2.dp)
                    .background(altColor.copy(alpha = 0.35f), CircleShape)
            )
            // Team logo
            BasicImage(
                imgUrl = team.logos,
                contentDescription = team.name,
                modifier = Modifier
                    .size(44.dp)
                    .padding(2.dp),
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                borderColor = Color.Transparent,
                borderWidth = 0.dp,
                shape = CircleShape
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        val emoji = teamEmoji(sport = sport, league = league, teamAbbreviation = team.abbreviation)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            if (emoji != null) {
                Text(text = emoji, fontSize = 11.sp)
            }
            Text(
                text = team.abbreviation,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = team.shortDisplayName,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenContentPreview() {
    Theme {
        HomeScreen(
            state = HomeUiState.Content(
                sport = SportModel(
                    name = "Baseball",
                    slug = "baseball",
                    league = LeagueModel(
                        name = "Major League Baseball",
                        abbreviation = "MLB",
                        slug = "mlb",
                        teams = emptyList(),
                    ),
                ),
                articles = ArticlesListModel(),
            ),
            errorMessage = null,
            isRefreshing = false,
            lastUpdatedMs = System.currentTimeMillis() - 120_000L,
            onRefresh = {},
            onClearError = {},
            onLeagueSelected = { _, _ -> },
            onNavigateToScoreboard = { _, _ -> },
            onNavigateToTeam = { _, _, _ -> },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenLoadingPreview() {
    Theme {
        HomeScreen(
            state = HomeUiState.Loading,
            errorMessage = null,
            isRefreshing = false,
            lastUpdatedMs = null,
            onRefresh = {},
            onClearError = {},
            onLeagueSelected = { _, _ -> },
            onNavigateToScoreboard = { _, _ -> },
            onNavigateToTeam = { _, _, _ -> },
        )
    }
}