package com.sgut.android.nationalfootballleague.ui.screens.homelistscreen

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import com.sgut.android.nationalfootballleague.di.ToolBar3
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_teams_list.TeamModel
import com.sgut.android.nationalfootballleague.homelistscreen.ArticleRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.LeagueSelectionRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.SportScaffold
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.*
import com.sgut.android.nationalfootballleague.ui.navigation.NavigationScreens
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
    navController: NavController,
    ) {
    val uiStateBySelectionVm by selectionViewModel.selectionUiFullSportState.collectAsStateWithLifecycle()
    val news = selectionViewModel.articleList
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    SportScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ToolBar3(
                title = uiStateBySelectionVm.league.name,
                scrollBehavior = scrollBehavior
            )
        },

        content = { padding ->
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
                        navController.navigate(
                            NavigationScreens.ScoreboardScreen.withArgs(uiStateBySelectionVm.slug, uiStateBySelectionVm.league.slug)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "scores_games")
                }


                    TeamsListCircleRow(
                        teams = uiStateBySelectionVm.league.teams,
                        sport = uiStateBySelectionVm.slug,
                        league = uiStateBySelectionVm.league.slug,
                        onTeamClick = {},
                        navController = navController
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    NewsRow(news = news.value, modifier = Modifier.wrapContentSize())

                    Spacer(modifier = Modifier.height(16.dp))

                    Standings(
                        sport = uiStateBySelectionVm.slug,
                        league = uiStateBySelectionVm.league.slug,
                        type = "0"
                    )
            }
        },
        )
}


@Composable
fun TeamsListCircleRow(
    teams: List<TeamModel>,
    onTeamClick: () -> Unit,
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    navController: NavController,
) {

    DefaultCard(modifier = modifier) {
        CardHeaderText(text = league)
        NormalDivider()

        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(teams) { team ->
                TeamItem(
                    team = team,
                    onTeamClick = onTeamClick,
                    modifier = modifier,
                    sport = sport,
                    league = league,
                    navController = navController
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
    onTeamClick: () -> Unit,
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    navController: NavController,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)

    SportSurface(
        shape = MaterialTheme.shapes.medium,
        color = Color.LightGray

    ) {
        Box(modifier = modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .clickable {
                        navController.navigate(
                            NavigationScreens.DetailScreenTeam.withArgs(
                                team.abbreviation,
                                sport,
                                league
                            )
                        )
                    }
                    .padding(4.dp)
            ) {
                BasicImage(
                    imgUrl = team.logos,
                    contentDescription = team.name,
                    modifier = modifier.size(100.dp),
                    elevation = 1.dp,
                    backgroundColor = teamColor,
                    borderColor = Color.Black,
                    borderWidth = 1.dp,
                    shape = RoundedCornerShape(8.dp)
                )
            }
            ToggleFollowIconButton(
                isFollowed = team.isFavorite,
                onClick = { onTeamClick },
                modifier = Modifier.align(Alignment.TopEnd)
            )

        }
    }
}

@Composable
fun ShowToast(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
}




