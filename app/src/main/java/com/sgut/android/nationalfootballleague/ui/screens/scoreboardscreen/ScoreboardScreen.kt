package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.sgut.android.nationalfootballleague.StatusState
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.ScoreboardData
import com.sgut.android.nationalfootballleague.data.remote.network_responses.abs_scores.a_common.TennisScoreboard
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.SituationScoreboard
import com.sgut.android.nationalfootballleague.di.TopAppBarWithLogo
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticlesListModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.DefaultScoreboardEventModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitionModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitorsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardRecordModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.CardHeaderText
import com.sgut.android.nationalfootballleague.ui.commoncomps.EIGHT
import com.sgut.android.nationalfootballleague.ui.commoncomps.LeagueSelectionRow
import com.sgut.android.nationalfootballleague.ui.commoncomps.NormalDivider
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.BasicImage
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.SpacerDp
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.TeamLogoScoreboardImageLoader
import com.sgut.android.nationalfootballleague.ui.screens.homelistscreen.NewsRow
import com.sgut.android.nationalfootballleague.ui.screens.shared_viewmodels.SelectionViewModel
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.utils.Constants
import com.sgut.android.nationalfootballleague.utils.Constants.Companion.TENNIS
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate
import timber.log.Timber


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreboardScreen(
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    scoreboardViewModel: ScoreboardViewModel = hiltViewModel(),
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
    selectionViewModel: SelectionViewModel
) {

    val selectionUiState by selectionViewModel.selectionUiFullSportState.collectAsStateWithLifecycle()
    val selectionUiSport = selectionUiState.slug
    val selectionUiLeague = selectionUiState.league.slug

//    TODO this causes the switch when clicking different sport on scoreboard screen figure out what to do with selection viewmodel when selected tennis and errors bc tennis is different
    if (selectionUiLeague.isNotBlank()) {
        scoreboardViewModel.loadGenericScoreboard(selectionUiSport, selectionUiLeague)
    }

    Timber.d("SAL_GUT sportAll : $selectionUiSport")

    val newUiState by scoreboardViewModel.scoreboardModelState.collectAsStateWithLifecycle()
    val news = newUiState.currentArticles
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val sport = newUiState.currentSport // TODO MAYBE CHECK IF THIS WORKS
    val league = newUiState.currentLeague

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarWithLogo(
                title = newUiState.defaultScoreboardModelUiState.league.abbreviation ?: "",
                logo = newUiState.abstractScoreData?.league?.firstOrNull()?.logos?.firstOrNull()?.href ?: newUiState.defaultScoreboardModelUiState.league.logos.getOrNull(0)?.href ?: "",
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
//                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                LeagueSelectionRow(
                    leagues = Constants.LIST_OF_LEAGUE_PAIRS,
                    padding = innerPadding,
                    onLeagueSelected = { sport, league ->
//                        Timber.d("SAL_GUT SOMETHING SELECTED SPORT: $sport LEAGUE: $league")
                        if (sport == TENNIS) {
                            // TODO FIX bc first we call setDifferentSport so it can be null and show tennis
                            selectionViewModel.setDifferentSport(sport, league)
                            scoreboardViewModel.fetchAbstractScoreboard(sport, league)
                        } else {
                            selectionViewModel.setDifferentSport(sport, league)
                        }
                    }
                )

                Scoreboard(
                    events = newUiState.defaultScoreboardModelUiState.events,
                    modifier = modifier,
                    sport = sport,
                    league = league,
                    onNavigateToGame = onNavigateToGame,
                    scoreboardData = newUiState.abstractScoreData
                )


                Spacer(modifier = modifier.height(16.dp))

                NewsRow(news = news ?: ArticlesListModel(), modifier = modifier.wrapContentSize())

                Spacer(modifier = modifier.height(16.dp))


            }
        }

    )
}


//        TODO do

@Composable
fun MatchPreview() {

}

@Composable
fun Scoreboard(
    events: List<DefaultScoreboardEventModel>,
    sport: String,
    league: String,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
    modifier: Modifier,
    scoreboardData: ScoreboardData?
) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Scoreboard")
        NormalDivider()
        events.map { event ->
            if (event.competitions.isEmpty()) {
                TennisScoreboardHeader(scoreboardData)
            } else {
                NewEventMatchup(
                    event = event,
                    modifier = modifier,
                    sport = sport,
                    league = league,
                    onNavigateToGame = onNavigateToGame,
                )
            }
            NormalDivider()
        }
    }
}

//@Composable // TODO FIX THIS
//fun TennisScoreboardHeader(scoreboardData: ScoreboardData?) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        Text(text = scoreboardData?.league?.firstOrNull()?.name ?: "")
//        Text(text = scoreboardData?.day?.date ?: "")
//        Text(text = scoreboardData?.events?.firstOrNull()?.name ?: "")
//    }
//    Column (
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        scoreboardData?.events?.map { eventData ->
//            eventData.competitions?.map { competitions ->
//                Text(text = competitions.startDate)
//                Row {
//                    Text(text = competitions.competitors.firstOrNull()?.team?.name ?: "null")
//                    Text(text = competitions.competitors.firstOrNull()?.team?.name ?: "null")
//                }
//            }
//        }
//    }

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
fun TennisScoreboardHeader_OLD(scoreboardData: ScoreboardData?) {
    val tennisScoreboard = scoreboardData as? TennisScoreboard

    Text(text = tennisScoreboard?.events?.getOrNull(0)?.groupings .toString())
    tennisScoreboard?.events?.forEach { eventData ->
        eventData.groupings.forEach { groupingData ->
            groupingData.grouping.displayName
            Text(text = groupingData.grouping.displayName, fontWeight = FontWeight.Bold)
            groupingData.competitions.forEach { competitionsData ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Text(text = competitionsData.competitors.getOrNull(0)?.athlete?.shortName ?: "")
                    Text(text = competitionsData.startDate ?: "")
                    Text(text = competitionsData.competitors.getOrNull(1)?.athlete?.shortName ?: "")

                }
            }
        }
    }
}



//@Composable
//fun TennisScoreboardHeader(scoreboardData: ScoreboardData?) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        Text(text = scoreboardData?.league?.firstOrNull()?.name ?: "")
//        Text(text = scoreboardData?.day?.date ?: "")
//        Text(text = scoreboardData?.events?.firstOrNull()?.name ?: "")
//    }
//}





@Composable
fun TeamsMatchUpListFromEvents(
    events: List<DefaultScoreboardEventModel>,
    modifier: Modifier,
    sport: String,
    league: String,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Scores")
        events.map { event ->
            event.competitions.map { competition ->
                TeamComponent2(
                    compScoreboard = competition,
                    modifier = modifier,
                    onNavigateToGame = onNavigateToGame,
                    sport = sport,
                    league = league,
                )
            }
        }
    }
}


@Composable
fun Record(record: ScoreboardRecordModel) {
    Row() {
        Text(text = record.name)
    }
}


@Composable
fun TeamComponent(team: ScoreboardCompetitorsModel, modifier: Modifier) {
    val color = HexToJetpackColor2.getColor(team.team.color)

    Box(modifier = modifier
        .padding(8.dp)
        .background(
            Brush.horizontalGradient(
                listOf(color, Color.White)
            )
        )
        .fillMaxWidth(0.1f)) {


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            TeamLogoScoreboardImageLoader(team = team.team)


            if (team.winner == true) {
                Text(
                    text = team.team.name,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = team.score.toString(),
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
            } else {

                Text(
                    text = team.team.name,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = team.score.toString(),
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Light
                )
            }
        }

    }
}


@Composable
fun TeamComponent2(
    compScoreboard: ScoreboardCompetitionModel,
    modifier: Modifier,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
    sport: String,
    league: String,
) {
    val team1 = compScoreboard.competitors.first().team
    val team2 = compScoreboard.competitors.last().team
    val color1 = HexToJetpackColor2.getColor(team1.color)
    val color2 = HexToJetpackColor2.getColor(team2.color)
    val team1Score = compScoreboard.getHomeTeam().score
    val team2Score = compScoreboard.getAwayTeam().score
    val whiteColor = Color.White


    val date = compScoreboard.date.toDate()?.formatTo("K:mm aa")

    DefaultCard(
        modifier = modifier
            .fillMaxSize()
            .clickable { onNavigateToGame(sport, league, compScoreboard.id) }) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        listOf(color1, color2)
                    )
                )
                .fillMaxWidth(0.3f)) {

            Surface(color = Color.LightGray.copy(alpha = 0.3f), modifier = modifier.fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {

                        SpacerDp(modifier = modifier, width = EIGHT)
                        TeamLogoScoreboardImageLoader(team = team1)
                        SpacerDp(modifier = modifier, width = EIGHT)

                        Text(
                            text = team1.abbreviation,
                            style = TextStyle(fontSize = 12.sp),
                            textAlign = TextAlign.Left,
                            color = whiteColor
                        )

                        SpacerDp(modifier = modifier, width = EIGHT)

                        Text(
                            text = team1Score,
                            style = TextStyle(fontSize = 12.sp),
                            textAlign = TextAlign.Left,
                            fontWeight = FontWeight.Bold,
                            color = whiteColor

                        )
                    }
                }

                Column(horizontalAlignment = Alignment.Start) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {

                        Text(
                            text = team2Score,
                            style = TextStyle(fontSize = 12.sp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = whiteColor
                        )
                        SpacerDp(modifier = modifier, width = EIGHT)

                        Text(
                            text = team2.abbreviation,
                            style = TextStyle(fontSize = 12.sp),
                            textAlign = TextAlign.Center,
                            color = whiteColor
                        )
                        SpacerDp(modifier = modifier, width = EIGHT)
                        TeamLogoScoreboardImageLoader(team = team2)
                        SpacerDp(modifier = modifier, width = EIGHT)
                    }
                }
//                Game id for info or date

                GameInfoColumn(
                    description = compScoreboard.status?.type?.description ?: "",
                    date = date ?: "",
                    id = compScoreboard.id
                )
            }
        }
    }
}

@Composable
fun GameInfoColumn(description: String, date: String, id: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = description,
            style = TextStyle(fontSize = 12.sp),
            color = Color.White,
            textAlign = TextAlign.Center)
        Text(
            text = date,
            style = TextStyle(fontSize = 9.sp),
            color = Color.White,
            textAlign = TextAlign.Center)
        Text(
            text = id,
            style = TextStyle(fontSize = 9.sp),
            color = Color.White,
            textAlign = TextAlign.Center)
    }
}


@Composable
fun CompetitorRowPre(competitor: ScoreboardCompetitorsModel, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicImage(
                imgUrl = competitor.team.logo,
                contentDescription = competitor.team.name,
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                borderWidth = 0.dp,
                borderColor = Color.Transparent,
                shape = MaterialTheme.shapes.extraSmall,
                modifier = modifier
                    .size(30.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(text = competitor.team.shortDisplayName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp)
        }

        Row() {
            Text(text = competitor.records.getOrNull(0)?.summary ?: "")
        }

    }
}


@Composable
fun CompetitorRow(
    competitor: ScoreboardCompetitorsModel?,
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
        ) {
            BasicImage(
                imgUrl = competitor?.team?.logo ?: "",
                contentDescription = competitor?.team?.name ?: "",
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                borderWidth = 0.dp,
                borderColor = Color.Transparent,
                shape = MaterialTheme.shapes.extraSmall,
                modifier = modifier
                    .size(30.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(text = competitor?.team?.shortDisplayName ?: "",
                fontWeight = if (competitor?.winner == true) FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp)
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


@Composable
fun NewEventMatchup(
    event: DefaultScoreboardEventModel,
    modifier: Modifier,
    onNavigateToGame: (sport: String, league: String, event: String) -> Unit,
    sport: String,
    league: String,
) {
    Column() {
        Box(modifier = modifier.clickable { onNavigateToGame(sport, league, event.id) }) {
            Column() {
                // leaders
//                Text(text = event.competitions.first().competitors.first().leaders.toString())
                Text(text = event.competitions.firstOrNull()?.id.toString())
                if (sport.equals("baseball") && event.status.type?.state == StatusState.IN) {
                    CompetitionSituation(
                        event.competitions.firstOrNull()?.situation,
                        modifier = modifier
                    )
                }
                CompetitorRow(
                    competitor = event.competitions.firstOrNull()?.competitors?.lastOrNull(),
                    modifier = modifier
                ) {
                    Text(
                        text = (if (event.status.type?.state == StatusState.PRE) event.competitions.firstOrNull()?.competitors?.last()?.records?.getOrNull(
                            0
                        )?.summary ?: ""
                        else event.competitions.firstOrNull()?.competitors?.last()?.score).toString()
                    )
                }


                CompetitorRow(
                    competitor = event.competitions.firstOrNull()?.competitors?.firstOrNull(),
                    modifier = modifier
                ) {
                    Text(
                        text = (if (event.status.type?.state == StatusState.PRE) event.competitions.firstOrNull()?.competitors?.firstOrNull()?.records?.getOrNull(0
                        )?.summary ?: ""
                        else event.competitions.firstOrNull()?.competitors?.firstOrNull()?.score ?: ""))
                }
            }
            Text(text = event.competitions.firstOrNull()?.status?.type?.shortDetail ?: "",
                modifier = modifier.align(
                    Alignment.TopCenter),
                fontWeight = FontWeight.Bold)


        }
    }
}


@Composable
fun CompetitionSituation(situation: SituationScoreboard?, modifier: Modifier) {

    Text(text = "Balls: ${situation?.balls.toString()} Strikes: ${situation?.strikes.toString()} Outs: ${situation?.outs.toString()}")

}



