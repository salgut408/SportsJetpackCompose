package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.sgut.android.nationalfootballleague.di.ToolBar3
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamModel
import com.sgut.android.nationalfootballleague.homelistscreen.ArticleRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.LeagueSelectionRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.SportScaffold
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.*

import com.sgut.android.nationalfootballleague.ui.newComponents.FilledButton
import com.sgut.android.nationalfootballleague.ui.screens.shared_viewmodels.SelectionViewModel
import com.sgut.android.nationalfootballleague.ui.screens.standings_screen.Standings
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.uiStyleDefinitions.design.style.Theme
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.LIST_OF_LEAGUE_PAIRS
import com.sgut.android.nationalfootballleague.utils.basicButton
import timber.log.Timber
import com.sgut.android.nationalfootballleague.R.string as AppText


//Home Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTeamCardsListScreen(
    selectionViewModel: SelectionViewModel,
    onNavigateToScoreboard: (sport: String, league: String) -> Unit,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
) {
    val uiStateBySelectionVm by selectionViewModel.selectionUiFullSportState.collectAsStateWithLifecycle()
    val news by selectionViewModel.articleList.collectAsStateWithLifecycle()
    val isLoading by selectionViewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by selectionViewModel.errorMessage.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            snackbarHostState.showSnackbar(
                message = errorMessage!!,
                duration = SnackbarDuration.Long
            )
            selectionViewModel.clearError()
        }
    }

    SportScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ToolBar3(
                title = uiStateBySelectionVm.league.name,
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { padding ->
            if (isLoading) {
                DataLoadingComponent()
            } else {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    LeagueSelectionRow(
                        leagues = LIST_OF_LEAGUE_PAIRS,
                        padding = padding,
                        onLeagueSelected = { sport, league ->
                            selectionViewModel.setDifferentSport(sport, league)
                        }
                    )
                    FilledButton(
                        onClick = {
                            onNavigateToScoreboard(
                                uiStateBySelectionVm.slug,
                                uiStateBySelectionVm.league.slug
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(text = "Scores & Games", color = Color.Black)
                    }

                    TeamsListCircleRow(
                        teams = uiStateBySelectionVm.league.teams,
                        sport = uiStateBySelectionVm.slug,
                        league = uiStateBySelectionVm.league.slug,
                        onNavigateToTeam = onNavigateToTeam,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    NewsRow(news = news, modifier = Modifier.wrapContentSize())

                    Spacer(modifier = Modifier.height(16.dp))

                    Standings(
                        sport = uiStateBySelectionVm.slug,
                        league = uiStateBySelectionVm.league.slug,
                        type = "0"
                    )
                }
            }
        },
    )
}


@Composable
fun TeamsListCircleRow(
    teams: List<TeamModel>,
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = league)
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
        CardHeaderText(text = news.header)
        NormalDivider()
        ArticleRow(articleList = news.articles)
    }
}

@Composable
fun LabelText(@StringRes stringResId: Int) {
    val resources = LocalContext.current.resources
    Text(
        text = stringResource(id = stringResId),
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
fun TeamItem(
    team: TeamModel,
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    onNavigateToTeam: (team: String, sport: String, league: String) -> Unit,
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

        Text(
            text = team.abbreviation,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
        )
        Text(
            text = team.shortDisplayName,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun ShowToast(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
}




