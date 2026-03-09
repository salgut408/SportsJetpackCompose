package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import android.widget.TextView
import android.widget.Toast
import androidx.compose.animation.animateContentSize
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
import com.sgut.android.nationalfootballleague.di.GameDetailsTopBar
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.*
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate
import java.util.*
import kotlin.math.nextUp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    event: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    gameDetailViewModel: GameDetailViewModel = hiltViewModel(),
) {

    gameDetailViewModel.loadGameDetails(sport, league, event)

    val gameDetailUiState by gameDetailViewModel.gameDetailUiState.collectAsStateWithLifecycle()
    val map by gameDetailViewModel.map.collectAsStateWithLifecycle()

    val eventName = gameDetailViewModel.returnTeamNamesForTopBar()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    SportScaffold(
        topBar = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                GameDetailsTopBar(
                    eventName = eventName,
                    canNavigateBack = canNavigateBack,
                    navigateUp = navigateUp,
                    scrollBehavior = scrollBehavior,
                )

                Header2(
                    modifier = modifier.background(Color.White),
                    headerModel = gameDetailUiState.currentGameUiState?.header ?: HeaderModel(),
                    middle = {
                        when (gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.statusState) {
                            StatusState.PRE -> {
                                Column() {
                                    Text(
                                        text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.date?.toDate()?.formatTo("K:mm aa") ?: "",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = gameDetailUiState.currentGameUiState?.pickcenter?.firstOrNull()?.details ?: "")
                                }
                            }
                            StatusState.IN -> {
                                Text(text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.description
                                    ?: "")
                                Text(text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.shortGameTimeDetail
                                    ?: "")
                            }
                            StatusState.POST -> {
                                Text(text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.description
                                    ?: "")
                            }
                            else -> Text(text = "")
                        }
                    },
                )
            }
        },
        content = { padding ->
//            Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                SpacerDp(modifier = modifier, height = EIGHT)
                Text(text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.periodPrefix?.name
                    ?: "l")
                Text(text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.id
                    ?: "l")
                SpacerDp(modifier = modifier, height = EIGHT)

                when (gameDetailUiState.currentSport) {
                    "basketball" -> AnimatedCircle(
                        modifier = modifier,
                        gameDetailModel = gameDetailUiState.currentGameUiState
                            ?: GameDetailsModel(),
                    )
                    "football" -> AnimatedCircle(
                        modifier = modifier,
                        gameDetailModel = gameDetailUiState.currentGameUiState
                            ?: GameDetailsModel(),

                        )
                    "baseball" -> BaseballSpecific(

                        modifier = modifier,
                        gameDetailSituation = gameDetailUiState.currentGameUiState?.baseballSituation
                            ?: SituationModel(),
                        gameDetailsModel = gameDetailUiState.currentGameUiState
                            ?: GameDetailsModel(),
                        teamMap = map,
                        gameState = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.statusState,
                    )
                }

                SpacerDp(modifier = modifier, height = EIGHT)

                WeightedRows(
                    modifier = modifier,
                    header = gameDetailUiState.currentGameUiState ?: GameDetailsModel()
                )
                SpacerDp(modifier = modifier, height = EIGHT)

                BoxScoreTeamStats(modifier = modifier,
                    boxscore = gameDetailUiState.currentGameUiState?.boxscore ?: BoxScoreModel())

                SpacerDp(modifier = modifier, height = EIGHT)

                PickCenterList(modifier = Modifier,
                    list = gameDetailUiState.currentGameUiState?.pickcenter ?: listOf())

                SpacerDp(modifier = modifier, height = EIGHT)

                CompetitionStatus(
                    modifier = modifier,
                    competitions = gameDetailUiState.currentGameUiState?.header?.competitions
                        ?: listOf()
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                SeasonLeaders(
                    modifier = modifier,
                    leaders = gameDetailUiState.currentGameUiState?.leaders ?: listOf()
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                NewVidList(
                    modifier = modifier,
                    vidList = gameDetailUiState.currentGameUiState?.videos ?: listOf()
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                TabsLastFiveGames(
                    modifier = modifier,
                    lastFiveGames = gameDetailUiState.currentGameUiState?.lastFiveGames ?: listOf()
                )
                SpacerDp(modifier = modifier, height = EIGHT)


                TabsSeasonLeaders(modifier = modifier,
                    leaders = gameDetailUiState.currentGameUiState?.leaders ?: listOf())

                SpacerDp(modifier = modifier, height = EIGHT)

                ExpandableGameArticle(
                    modifier = modifier,
                    gameDetailModel = gameDetailUiState.currentGameUiState ?: GameDetailsModel(),
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                FindTickets(
                    modifier = modifier,
                    ticketsInfo = gameDetailUiState.currentGameUiState?.ticketsInfo
                        ?: TicketsInfoModel(),
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                InjuriesReportCard(
                    modifier = modifier,
                    gameDetailModel = gameDetailUiState.currentGameUiState
                        ?: GameDetailsModel())

                SpacerDp(modifier = modifier, height = EIGHT)

                GameInformation(
                    modifier = modifier,
                    gameDetailModel = gameDetailUiState.currentGameUiState ?: GameDetailsModel(),
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                Header2(
                    modifier = modifier,
                    headerModel = gameDetailUiState.currentGameUiState?.header ?: HeaderModel(),
                    middle = {
                        when (gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.statusState) {
                            StatusState.PRE -> {
                                Column() {
                                    Text(
                                        text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.date?.toDate()
                                            ?.formatTo("K:mm aa") ?: "",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = gameDetailUiState.currentGameUiState?.pickcenter?.firstOrNull()?.details
                                        ?: "")
                                }
                            }
                            StatusState.IN -> {
                                Text(text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.description
                                    ?: "")

                            }
                            StatusState.POST -> {
                                Text(text = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()?.status?.type?.description
                                    ?: "")

                            }
                            else -> {}
                        }
                    },
                )

                SpacerDp(modifier = modifier, height = EIGHT)


                TeamStatCard3(
                    modifier = modifier,
                    boxscore = gameDetailUiState.currentGameUiState?.boxscore
                        ?: BoxScoreModel()
                )
                SpacerDp(modifier = modifier, height = EIGHT)


                HeaderStatusSlot(
                    modifier = modifier,
                    gameDetailModel = gameDetailUiState.currentGameUiState ?: GameDetailsModel()
                )

            }
//        }

        }
    )
}


@Composable
fun BaseballSpecific(
    modifier: Modifier,
    gameDetailSituation: SituationModel,
    gameDetailsModel: GameDetailsModel,
    teamMap: Map<String, GameDetailsAthleteDetailsModel>,
    gameState: StatusState?,
) {

    when (gameState) {
        StatusState.POST -> {
            PostOrPre(gameDetailModel = gameDetailsModel, modifier = modifier)
        }
        StatusState.IN -> {
            BaseballSituation(
                modifier = modifier,
                gameDetailSituation = gameDetailSituation,
                teamMap = teamMap,
                competition = gameDetailsModel.header?.competitions?.firstOrNull()
                    ?: GameDetailsCompetitionModel()
            )
            SpacerDp(modifier = modifier, height = EIGHT)
            PostOrPre(gameDetailModel = gameDetailsModel, modifier = modifier)
        }
        StatusState.PRE -> {
            PostOrPre(gameDetailModel = gameDetailsModel, modifier = modifier)
        }
        else -> {}
    }


}

@Composable
fun PostOrPre(gameDetailModel: GameDetailsModel, modifier: Modifier) {
    AnimatedCircle(
        modifier = modifier,
        gameDetailModel = gameDetailModel
    )
    SpacerDp(modifier = modifier, height = EIGHT)
    ProbablesList(list = gameDetailModel.header?.competitions?.getOrNull(0)?.competitors ?: listOf()
        ?: listOf(),
        modifier = modifier)

}


@Composable
fun RosterItem(rosterPerson: RosterModel) {
    Card() {
        Column() {
            Row() {
                GenericImageLoader(
                    obj = rosterPerson.athlete.headshot?.href ?: "",
                    modifier = Modifier.size(60.dp)
                )
                Text(text = "Name: $rosterPerson.athlete.displayName, ${rosterPerson.position.displayName}")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Bat order: $rosterPerson.batOrder.toString()")
            }
        }
    }
}


@Composable
fun CompetitionStatus(
    modifier: Modifier,
    competitions: List<GameDetailsCompetitionModel>,
) {

    DefaultCard(modifier = modifier) {
        competitions.map { status ->
            Text(text = status.status?.type?.shortGameTimeDetail ?: "")
        }
    }
}

// use for home and away
@Composable
fun LineUp(lineUp: List<RostersModel>) {

    lineUp.map { roster ->
        roster.roster.map { RosterItem(rosterPerson = it) }
    }
}


@Composable
fun BoxScore(boxscore: GameDetailsBoxscore) {
    boxscore.teams.map { team ->
        Text(text = team.team?.name ?: "")
        team.statistics.map { stats ->
            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = stats.displayValue ?: "")

                }
            }
        }
    }
}


@Composable
fun ExpandableGameArticle(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
    //take gamedeetail viewmodel out of here
) {
    var showMore by remember { mutableStateOf(false) }

    DefaultCard(modifier = modifier) {
        Column(modifier = modifier
            .animateContentSize(animationSpec = tween(100))
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { showMore = !showMore }) {


            if (gameDetailModel.singleGameArticle?.story?.isEmpty() == true) {
                Text(text = "")
            } else {
                if (showMore) {
                    CardHeaderText(text = "Preview:")
                    HtmlText(html = gameDetailModel.singleGameArticle?.story ?: "")
                } else {
                    Text(
                        text = gameDetailModel.singleGameArticle?.headline ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )


                    HtmlText(html = gameDetailModel.singleGameArticle?.description ?: "")
                    Row() {
                        Text(text = gameDetailModel.singleGameArticle?.published ?: "")

                        Text(text = " - ")
                        Text(text = gameDetailModel.singleGameArticle?.source ?: "")
                    }
                }
            }


            var isPressed by remember { mutableStateOf(false) }
            val context = LocalContext.current
            PressIconButton(
                onClick = {
//                    TODO fix removing viewmodel pass onClick
//                    gameDetailViewModel.onSaveArticleClick(gameDetailModel)
                    Toast.makeText(context, "Saved to list", Toast.LENGTH_SHORT).show()

                    Toast.makeText(context, "Added to articles for later", Toast.LENGTH_SHORT)
                        .show()
                    when (isPressed) {
                        true -> isPressed = false
                        false -> isPressed = true
                    }
                },
                icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                text = { Text(if (isPressed) "Saved" else "Save for later") },
                isPressed = isPressed
            )


        }
    }
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS) }
    )
}

@Composable
fun WeightedRows(
    modifier: Modifier,
    header: GameDetailsModel,
) {

    DefaultCard(modifier = modifier) {
        Row(modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            header.header?.competitions?.map { competitions ->
                Text(text = competitions.status?.type?.description ?: "")

                competitions.competitors.map { competitors ->
                    competitors.team?.record?.map { teamRecord ->
                        Column() {
                            Text(text = teamRecord.type)
                            Text(text = teamRecord.displayValue)
                            Text(text = teamRecord.summary)
                        }
                    }
                    Box() {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = competitors.team?.abbreviation ?: "",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            SpacerDp(modifier = modifier, width = SIXTEEN)
                            GenericImageLoader(
                                obj = competitors.team?.logos?.getOrNull(0)?.href ?: "",
                                modifier = Modifier.width(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LongGameTimeDetail(gameDetailModel: GameDetailsModel) {
    Text(text = gameDetailModel.header?.competitions?.getOrNull(0)?.status?.type?.gameTimeDetail
        ?: "", fontSize = 11.sp)

}

@Composable
fun ScoringPlay(scoringPlays: ScoringPlays) {
    Column() {
        Text(text = scoringPlays.team?.name ?: "")
        Text(text = scoringPlays.type?.text ?: "")
        Text(text = "Period: " + scoringPlays.period?.number.toString())

    }
}


@Composable
fun WinProbabilityGraph(winProbability: List<WinprobabilityModel>) {

    winProbability
//        .sortedBy { it.homeWinPercentage }
        .map { Text(text = it.homeWinPercentage.nextUp().toString()) }
}

@Composable
fun HeaderTeamLogo(team: GameDetailsTeamInfoModel) {
    GenericImageLoader(
        obj = team.logos.getOrNull(0)?.href ?: "",
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun HeaderTeamItem(competitor: GameDetailsCompetitorModel) {
    Column() {
        Row() {
            HeaderTeamLogo(competitor.team ?: GameDetailsTeamInfoModel())
        }
    }

}


@Composable
fun HeaderTeamSlot(
    modifier: Modifier,
    competitor: GameDetailsCompetitorModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(SIXTEEN.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = competitor.team?.abbreviation ?: "", fontSize = 12.sp)
            Text(text = competitor.record.getOrNull(0)?.summary ?: "", fontSize = 9.sp)
            Text(text = competitor.score.toString(), fontWeight = FontWeight.Bold)
        }

        SpacerDp(modifier = modifier, width = 8)

        HeaderTeamLogo(competitor.team ?: GameDetailsTeamInfoModel())

    }
}



@Composable
fun LastPlay(play: String) {
    Text(text = play)
}

@Composable
fun SeasonLeaders(
    modifier: Modifier,
    leaders: List<GameDetailsLeadersModel>,
) {
    DefaultCard(
        modifier = modifier,
        content = {
            CardHeaderText(text = "Season Leaders")
            NormalDivider()
            Row(
            ) {
                leaders.map { gameDetailsLeaders ->
                    Row() {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,

                                ) {
                                GenericImageLoader(
                                    obj = gameDetailsLeaders.team.logo,
                                    modifier = Modifier.size(50.dp)
                                )
                                Text(text = gameDetailsLeaders.team.abbreviation,
                                    fontWeight = FontWeight.Bold)
                            }

                            gameDetailsLeaders.leaders.map { gameDetailsLeaders ->
                                Text(text = gameDetailsLeaders.displayName,
                                    fontWeight = FontWeight.Bold)
                                Column() {
                                    Row() {
                                        gameDetailsLeaders.leadersAthlete.map { leaderAthlete ->
                                            SeasonLeadersPlayer(athlete = leaderAthlete)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun SeasonLeadersTabs(
    seasonLeaders: List<GameDetailsLeaders>,
    teamInt: Int,
    modifier: Modifier,
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf(
        seasonLeaders.getOrNull(0)?.team?.name,
        seasonLeaders.getOrNull(1)?.team?.name,
    )
}

@Composable
fun TabsSeasonLeaders(
    modifier: Modifier,
    leaders: List<GameDetailsLeadersModel>,
) {

    var tabIndex by remember { mutableStateOf(0) }

    val tabTitles = listOf(
        leaders.getOrNull(0)?.team,
        leaders.getOrNull(1)?.team)

    DefaultCard(modifier = modifier) {

        TabRow(
            selectedTabIndex = tabIndex,
        ) {
            tabTitles.forEachIndexed { index, team ->
                Tab(
                    modifier = Modifier.background(Color.LightGray),
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Box() {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                GenericImageLoader(
                                    obj = team?.logo.toString(),
                                    modifier = modifier.size(30.dp)
                                )
                                Spacer(modifier = modifier.width(8.dp))
                                Text(text = team?.abbreviation ?: "")

                            }
                        }
                    },
                )
            }
        }
        when (tabIndex) {
            0 -> Leads(leaders = leaders.getOrNull(0)?.leaders ?: listOf(), teamInt = 0)
            1 -> Leads(leaders = leaders.getOrNull(1)?.leaders ?: listOf(), teamInt = 1)
        }
    }

}

@Composable
fun Leads(
    leaders: List<GameLeadersModel>,
    teamInt: Int,
) {
    val teamInfo = leaders.getOrNull(teamInt)
    if (leaders.isEmpty()) {
        Text(text = "")
    } else {
        leaders.map {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = it.displayName)
                Text(text = it.leadersAthlete.first().athlete.displayName)

            }


        }
    }

}


@Composable
fun BoxScoreTeamStats(boxscore: BoxScoreModel, modifier: Modifier) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "TeamStats")
        boxscore.statistics.map { stats ->
            Text(text = boxscore.toString())
        }


    }

}

@Composable
fun Stat1(statistic: BoxscorePlayerStatisticModel, modifier: Modifier) {
    DefaultCard(modifier = modifier) {

        Text(text = statistic.athletes.first().athlete?.shortName ?: "")

        Row() {
            Column() {
                statistic.descriptions.map { Text(text = it) }
            }
            Column() {
                statistic.athletes.first().stats.map { Text(text = it) }
            }
        }
    }

}

@Composable
fun BoxScore(boxscorePlayer: BoxscorePlayerModel) {


}


@Composable
fun SeasonLeaderPlayerItem(athlete: AthleteLeaders) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GenericImageLoader(
            obj = athlete.athlete.headshot?.href ?: "",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)

                .border(width = 1.dp, color = Color.LightGray)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(text = athlete.athlete.shortName ?: "")
            Text(text = athlete.displayValue)
        }

    }
}

@Composable
fun NewVidList(
    modifier: Modifier,
    vidList: List<VideoModel>,
) {

    DefaultCard(modifier = modifier) {
        CardHeaderText(text = if (vidList.isEmpty()) "No Videos" else "Videos")
        NormalDivider()
        SpacerDp(modifier = modifier, width = SIXTEEN)

        LazyRow(contentPadding = PaddingValues(EIGHT.dp)) {
            items(items = vidList) { vid ->
                VideoPreview(video = vid, modifier = modifier.padding(EIGHT.dp))
            }
        }
    }


}



@Composable
fun DisplayLabels(list: List<GameDetailsStatisticModel>) {
    Column() {
        list.map {
            Text(text = it.name, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
            Text(text = it.displayValue, fontSize = 10.sp)
        }
    }
}


@Composable
fun TeamStatCard3(
    modifier: Modifier,
    boxscore: BoxScoreModel,
) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "TeamComm Stats")
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Row() {
                boxscore.statistics.map {
                    Column() {
                        Text(text = it.name)
                        Text(text = it.displayValue)
                    }
                }
            }

            boxscore.teams.map {
                Column(
                    modifier = modifier
                ) {
                    Text(text = it.team?.abbreviation ?: "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column() {
                            it.statistics.map { txt ->
                                Text(text = txt.name)
                                Text(text = txt.displayValue)
                            }
                            DisplayLabels(list = it.statistics)
                        }
                    }
                }

            }
        }


    }
}


@Composable
fun SeasonLeadersPlayer(athlete: AthleteLeaderModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,

        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)

    ) {
        Column() {
            Text(
                text = athlete.athlete.shortName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = athlete.displayValue,
                fontSize = 9.sp,
                color = Color.Gray,
                lineHeight = 10.sp,
                maxLines = 2,
                overflow = TextOverflow.Visible,
                modifier = Modifier.width(90.dp)
            )
        }
        Column() {
            GenericImageLoader(
                obj = athlete.athlete.headshot?.href ?: "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .align(Alignment.End)

            )
        }
    }
}

@Composable
fun RightToLeftLayout(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        content()
    }
}

@Composable
fun ProbablesList(list: List<GameDetailsCompetitorModel>, modifier: Modifier) {
    DefaultCard(modifier = Modifier) {
        CardHeaderText(text = "Probables")
        NormalDivider()
        Spacer(modifier = modifier.height(16.dp))
        PitcherMatchUp(competitor = list.first(), modifier = modifier)
        RightToLeftLayout {
            PitcherMatchUp(competitor = list.last(), modifier = modifier)
        }
    }
}

@Composable
fun PitcherMatchUp(competitor: GameDetailsCompetitorModel, modifier: Modifier) {
    val color = competitor.team?.color?.let { HexToJetpackColor2.getColor(it) }
    if (color != null) {
        Row(
            modifier = modifier
                .fillMaxWidth(1f)
                .background(color),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = competitor.team.abbreviation,
                fontSize = 80.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GenericImageLoader(obj = competitor.probables.getOrNull(0)?.athlete?.headshot?.href ?: "",
                    modifier = modifier.fillMaxWidth())
                Text(text = competitor.probables.getOrNull(0)?.athlete?.displayName ?: "TBD",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
            }
        }
    }


}

@Composable
fun Header2(
    headerModel: HeaderModel,
    modifier: Modifier,
    middle: @Composable () -> Unit,

    ) {

//    Text(text = headerModel.id)

    DefaultCard(
        modifier = modifier,
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            headerModel.competitions.map { competition ->
                CompetitorHeader(competitor = competition.competitors.first())
                Spacer(modifier = modifier.width(24.dp))
                DetailCol(
                    content = middle
                )
                Spacer(modifier = modifier.width(24.dp))
                RightToLeftLayout { CompetitorHeader(competitor = competition.competitors.last()) }
            }
        }
    }

}

@Composable
fun DetailCol(
    content: @Composable () -> Unit,

    ) {
    Column(

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Text(
//            text = competition.date.toDate()?.formatTo("K:mm aa") ?: "",
//
//            )
        content()


    }
}

@Composable
fun CompetitorHeader(competitor: GameDetailsCompetitorModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BasicImage(
                imgUrl = competitor.team?.logos?.first()?.href ?: "",
                contentDescription = competitor.team?.name,
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                borderWidth = 0.dp,
                borderColor = Color.Transparent,
                modifier = Modifier.size(40.dp)
            )
            Text(text = competitor.team?.abbreviation ?: "")
            Text(text = competitor.record.first().summary)
        }
        Text(text = competitor.score.toString(), style = MaterialTheme.typography.displaySmall)
    }
}


@Composable
fun HeaderStatusSlot(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
) {
    DefaultCard(
        modifier = modifier
    ) {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                gameDetailModel.header?.competitions!!.map { competition ->
                    HeaderTeamSlot(
                        modifier = modifier,
                        competitor =
                        competition.competitors.first()
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = competition.date.toDate()?.formatTo("MMM/dd") ?: "",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = competition.status?.type?.description ?: "",
                            fontSize = 12.sp
                        )
                        Text(
                            text = competition.date.toDate()?.formatTo("K:mm aa") ?: "",
                            fontSize = 9.sp
                        )
                    }
                    RightToLeftLayout {
                        HeaderTeamSlot(
                            modifier = modifier,
                            competitor = competition.competitors.last(),
                        )
                    }
                }
            }

        }
    }
}


@Composable
//multiple same name fields only last one will be used
fun InjuriesReportCard(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
) {
    val team1Display = gameDetailModel.injuries.getOrNull(0)?.team?.displayName
    val team2Display = gameDetailModel.injuries.getOrNull(1)?.team?.displayName
    val injuries1 = gameDetailModel.injuries.getOrNull(0)
    val injuries2 = gameDetailModel.injuries.getOrNull(1)
    val team1Logo = gameDetailModel.injuries.getOrNull(0)?.team
    val team2Logo = gameDetailModel.injuries.getOrNull(1)?.team

    if (injuries1?.injuries?.isEmpty() == true) {
        Text(text = "")
    } else {
        DefaultCard(
            modifier = modifier
        ) {

            CardHeaderText(text = "Injury Report")

            NormalDivider()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (team1Logo != null) {
                    GenericImageLoader(obj = team1Logo.logo, modifier = Modifier.size(35.dp))
                }
                Text(text = team1Display ?: "", fontWeight = FontWeight.Bold)
            }

            if (injuries1 != null) {
                InjuryColumn(modifier = modifier, injuries = injuries1)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (team2Logo != null) {
                    GenericImageLoader(obj = team2Logo.logo, modifier = Modifier.size(35.dp))
                }
                Text(text = team2Display ?: "", fontWeight = FontWeight.Bold)
            }
            if (injuries2 != null) {
                InjuryColumn(modifier = modifier, injuries = injuries2)
            }
        }
    }
}

@Composable
fun InjuryColumn(
    modifier: Modifier,
    injuries: GameDetailsInjuriesListModel,
) {
    Column() {

        injuries.injuries.map {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AthleteNameAndPosition(athlete = it.athlete, modifier = modifier)
                Text(text = it.status, textAlign = TextAlign.Right)
            }
        }
    }

}


@Composable
fun AthleteNameAndPosition(athlete: GameDetailsAthleteDetailsModel, modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        GenericImageLoader(obj = athlete.headshot?.href ?: "", modifier = modifier.size(40.dp))
        Spacer(modifier = Modifier.width(16.dp))

        Text(text = athlete.displayName)
        Spacer(modifier = Modifier.width(8.dp))

        Text(text = athlete.position?.abbreviation ?: "", color = Color.Blue)
    }
}

@Composable
fun BaseBallRosterLineUp(rosters: List<RostersModel>) {
    Card() {
        rosters.map { team ->
            Text(text = team.team.abbreviation)
        }

    }
}

@Composable
fun BaseballSituation(
    modifier: Modifier,
    gameDetailSituation: SituationModel,
    competition: GameDetailsCompetitionModel,
    teamMap: Map<String, GameDetailsAthleteDetailsModel>,
//    onPlayerId: (String) -> GameDetailsAthleteDetailsModel
) {

//    TOP/MIDDLE/BOTTOM/END are Competition status types
    DefaultCard(modifier = modifier.fillMaxWidth()) {
        CardHeaderText(text = "Current Situation")
        InningText(competition = competition)
        Divider()
        OutsBallsStrikes(gameDetailSituation = gameDetailSituation, modifier = modifier)


        Divider()

        if (
            competition.status?.periodPrefix == InningPrefix.END) {
            Text(text = "Due up", fontWeight = FontWeight.Bold)

            gameDetailSituation.dueUp.map {
                teamMap[it.playerId]?.let { it1 -> Player(player = it1) }
            }
            Spacer(modifier = Modifier.width(20.dp))

        } else {

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Player(
                    player = teamMap[gameDetailSituation.batter?.playerId.toString()]
                        ?: GameDetailsAthleteDetailsModel())

                Text(text = "Vs", fontWeight = FontWeight.Bold, fontSize = 20.sp)

                Player(player = teamMap[gameDetailSituation.pitcher?.playerId.toString()]
                    ?: GameDetailsAthleteDetailsModel())
            }
        }
        NormalDivider()

        Text(text = "On First", fontWeight = FontWeight.Bold)
        Row() {
            teamMap[gameDetailSituation.onFirst?.playerId.toString()]?.let { Player(player = it) }
        }
        Text(text = "On Second", fontWeight = FontWeight.Bold)
        Row() {
            teamMap[gameDetailSituation.onSecond?.playerId.toString()]?.let { Player(player = it) }
        }
        Text(text = "On Third", fontWeight = FontWeight.Bold)
        Row() {
            teamMap[gameDetailSituation.onThird?.playerId.toString()]?.let { Player(player = it) }
        }

        Text(text = "Due up", fontWeight = FontWeight.Bold)


        gameDetailSituation.dueUp.map {
            Row {
                teamMap[it.playerId]?.let { it1 -> Player(player = it1) }

            }
        }
    }
}


@Composable
fun Tracker(tracking: String, count: Int, modifier: Modifier) {
    val color = Color(0xFFFC0000)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = tracking.uppercase(Locale.getDefault()))


        for (i in 1..count) {
            Canvas(
                modifier = modifier
                    .size(20.dp)
                    .padding(8.dp),
                onDraw = {
                    drawCircle(color)
                }
            )
            Spacer(modifier = modifier.width(8.dp))
        }
    }


}

@Composable
fun Player(player: GameDetailsAthleteDetailsModel) {
    Box(modifier = Modifier.wrapContentSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BasicImage(
                imgUrl = player.headshot?.href ?: "",
                contentDescription = player.displayName,
                elevation = 2.dp,
                backgroundColor = Color.White,
                borderWidth = 1.dp,
                borderColor = Color.Black,
                modifier = Modifier.size(80.dp)
            )

            Text(text = player.shortName, fontSize = 10.sp)
            SpacerDp(modifier = Modifier, width = SIXTEEN)
            Text(text = player.position?.abbreviation ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp)


        }
    }
}

@Composable
fun PitcherVsBatter(gameDetailSituation: SituationModel) {

}

@Composable
fun OutsBallsStrikes(
    gameDetailSituation: SituationModel,
    modifier: Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly,

        ) {

        Tracker(tracking = "balls", count = gameDetailSituation.balls, modifier = modifier)
        Tracker(tracking = "strikes", count = gameDetailSituation.strikes, modifier = modifier)
        Tracker(tracking = "outs", count = gameDetailSituation.outs, modifier = modifier)
    }
}

@Composable
fun InningText(competition: GameDetailsCompetitionModel) {

    Text(text = competition.status?.type?.gameTimeDetail ?: "",
        style = MaterialTheme.typography.bodyMedium)
    NormalDivider()
    Text(text = competition.status?.periodPrefix?.name ?: "")

}

@Composable
fun DoughnutChart2(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
    size: Dp = 200.dp,
    thickness: Dp = 20.dp,
) {
    val colors = mutableListOf<Color>()
    val legends = mutableListOf<String>()
    val teams = gameDetailModel.boxscore?.teams


    teams?.map {
        colors.add(HexToJetpackColor2.getColor(it.team?.color ?: ""))
        legends.add(it.team?.name ?: "")
    }

    colors.reverse()
    legends.reverse()

    colors.add(Color.LightGray)
    legends.add("Tie")

//for nba only response is "AWAYTEAM"

    val gameProjection = gameDetailModel.predictor?.homeTeam?.gameProjection ?: 0f
    val teamChanceLoss = gameDetailModel.predictor?.homeTeam?.teamChanceLoss ?: 0f
    val teamChanceTie = gameDetailModel.predictor?.homeTeam?.teamChanceTie ?: 0f
    val values = listOf(gameProjection, teamChanceLoss, teamChanceTie)

    val sumOfValues = values.sum()
    val proportions = values.map { it * 100 / sumOfValues }
    val sweepAngles = proportions.map { it * 360 / 100 }

    DefaultCard(modifier = modifier) {

        CardHeaderText(text = "Matchup Predictor")

        NormalDivider()
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = "$gameProjection%",
                style = MaterialTheme.typography.headlineSmall,
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
            )
            Text(
                text = "$teamChanceLoss%",
                style = MaterialTheme.typography.headlineSmall,
                modifier = modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
            )

            Box(
                modifier = modifier
                    .height(IntrinsicSize.Max)
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = teams?.first()?.team?.abbreviation ?: "",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Divider(
                        color = Color.Black,
                        modifier = modifier
                            .height(100.dp)
                            .width(1.dp)
                    )
                    Spacer(modifier = modifier.width(8.dp))

                    Text(
                        text = teams?.last()?.team?.abbreviation ?: "",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Canvas(
                modifier = modifier
                    .size(size = size)
                    .padding(16.dp)
                    .align(Alignment.Center),
            ) {

                var startAngle = -90f
                for (i in values.indices) {

                    drawArc(
                        color = colors.getOrElse(i) { color -> Color.White },
                        startAngle = startAngle,
                        sweepAngle = sweepAngles[i],
                        useCenter = false,
                        style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt)
                    )
                    startAngle += sweepAngles[i]
                }
            }
        } //end of box

        SpacerDp(modifier = modifier, height = EIGHT)


        Column() {

            Text(
                text = "Tie: $teamChanceTie%",
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            for (i in values.indices) {
                DisplayLegend(
                    color = colors.getOrElse(i, { color -> Color.White }),
                    legend = legends.getOrElse(i, { word -> "" }))
            }
        }
    }
}


@Composable
fun DisplayLegend(color: Color, legend: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color = color, shape = CircleShape)
        )
        SpacerDp(modifier = Modifier, width = 4)

        Text(text = legend, color = Color.Blue, fontSize = 12.sp)

    }

}

@Composable
fun DoughnutChartForBasketball(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
    size: Dp = 200.dp,
    thickness: Dp = 20.dp,
) {
    val colors = mutableListOf<Color>()
    val legends = mutableListOf<String>()
    val teams = gameDetailModel.boxscore?.teams

    teams?.map {
        colors.add(HexToJetpackColor2.getColor(it.team?.color ?: ""))
        legends.add(it.team?.name ?: "")
    }
    colors.add(Color.LightGray)
    legends.add("Tie")

//for nba only response is "AWAYTEAM"

    val gameProjection = gameDetailModel.predictor?.awayTeam?.gameProjection ?: 0f
    val teamChanceLoss = gameDetailModel.predictor?.awayTeam?.teamChanceLoss ?: 0f
    val teamChanceTie = gameDetailModel.predictor?.awayTeam?.teamChanceTie ?: 0f
    val values = listOf(gameProjection, teamChanceLoss, teamChanceTie)

    val sumOfValues = values.sum()
    val proportions = values.map { it * 100 / sumOfValues }
    val sweepAngles = proportions.map { it * 360 / 100 }


    DefaultCard(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            CardHeaderText(text = "Matchup Predictor")
        }
        NormalDivider()
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = "$gameProjection%",
                style = MaterialTheme.typography.headlineSmall,
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
            )
            Text(
                text = "$teamChanceLoss%",
                style = MaterialTheme.typography.headlineSmall,
                modifier = modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
            )
            Box(
                modifier = modifier
                    .height(IntrinsicSize.Max)
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = teams?.get(1)?.team?.abbreviation ?: "",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                    )
                    SpacerDp(modifier = modifier, width = EIGHT)
                    NormalDivider(
                        color = Color.Black,
                        modifier = modifier
                            .height(100.dp)
                            .width(1.dp)
                    )
                    SpacerDp(modifier = modifier, width = EIGHT)
                    Text(
                        text = teams?.get(0)?.team?.abbreviation ?: "",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Canvas(
                modifier = modifier
                    .size(size = size)
                    .padding(16.dp)
                    .align(Alignment.Center),
            ) {
                var startAngle = -90f
                for (i in values.indices) {
                    drawArc(
                        color = colors.getOrElse(i) { color -> Color.White },
                        startAngle = startAngle,
                        sweepAngle = sweepAngles[i],
                        useCenter = false,
                        style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt),
                    )
                    startAngle += sweepAngles[i]
                }

            }
        }

        SpacerDp(modifier = modifier, height = THIRTYSIX)

        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Win Prediction", textAlign = TextAlign.Center)
            values.indices.map { int ->
                DisplayLegend(
                    color = colors.getOrElse(int, { color -> Color.White }),
                    legend = legends.getOrElse(int, { word -> "" })
                )
            }

        }
    }

}


@Composable
fun PickCenter(
    pickCenterInfo: PickcenterModel,
    modifier: Modifier,

    ) {
    Column() {
        Row() {
            Text(text = pickCenterInfo.provider.name)
        }
    }

}

@Composable
fun PickCenterList(
    modifier: Modifier,
    list: List<PickcenterModel>,
) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Pick Center")

        NormalDivider()

        list.map { pickCenter ->
            Row(
                modifier = modifier.fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(text = pickCenter.provider.name)
                Column(modifier = modifier) {

                    Text(text = "Favorite: ${pickCenter.details}")
                    Text(text = "O/U ${pickCenter.overUnder.toString()}")
                    Text(text = "Spread ${pickCenter.spread}")

                }
            }
        }
    }
}


@Composable
fun GameInformation(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
) {
    DefaultCard(
        modifier = modifier
    ) {
        CardHeaderText(text = "Game Information")
        NormalDivider()

        GameInfoCardVenueImage(gameDetailModel = gameDetailModel, modifier = modifier)

        LongGameTimeDetail(gameDetailModel = gameDetailModel)

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AddressComp(
                city = gameDetailModel.gameInfo.venue.address.city,
                state = gameDetailModel.gameInfo.venue.address.city
            )
            Text(
                text = gameDetailModel.gameInfo?.weather?.temperature ?: "",
                fontWeight = FontWeight.Bold
            )
        }
        Divider()
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(text = "CAPACITY: ", fontSize = 12.sp, fontWeight = FontWeight.Bold)

            Text(text = gameDetailModel.gameInfo?.venue?.capacity.toString(), fontSize = 12.sp)

        }

        NormalDivider()

        Column() {

            if (gameDetailModel.gameInfo.officials.isNotEmpty()) {
                Text(text = "Officials", fontSize = 20.sp)
            } else Text(text = "")

            gameDetailModel.gameInfo.officials.map { official ->
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = official.displayName ?: "", fontWeight = FontWeight.Bold)
                    Text(text = official.position.displayName ?: "")
                }
            }
        }
    }
}

@Composable
fun AddressComp(
    city: String,
    state: String,
) {
    Row() {
        Text(text = "${city} ${state}")
    }
}


@Composable
fun TeamVsComponent() {

}





@Composable
fun FindTickets(
    modifier: Modifier,
    ticketsInfo: TicketsInfoModel,
) {
    val team1 = ticketsInfo.seatSituation?.opponentTeamName
    val team2 = ticketsInfo.seatSituation?.currentTeamName
    val venueName = ticketsInfo.seatSituation?.venueName
    val shortDate = ticketsInfo.seatSituation?.dateShort
    val dateDay = ticketsInfo.seatSituation?.dateDay
    val dropDownOptions = ticketsInfo.tickets

    if (ticketsInfo.tickets.isEmpty()) {
        Text(text = "")
    } else {

        DefaultCard(
            modifier = modifier,

            content = {
                CardHeaderText(text = "Find Tickets")

                NormalDivider()

                Row(
                ) {
                    Text(
                        text = "$team1 vs $team2",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                ) {
                    Text(
                        text = "$venueName - $dateDay $shortDate",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 2.sp,
                        color = Color.Gray
                    )
                }
                Row(
//                modifier = modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = ticketsInfo.seatSituation?.summary ?: "",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Blue
                    )
                }
                NormalDivider()
                Row(
//                modifier = modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Buy $team2 tickets with VividSeats",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Blue
                    )
                }
                NormalDivider()
                // dropdowm
                DropDownFun(dropDownOptions)
            })
    }


}

@Composable
fun DropDownFun(tickets: List<TicketModel>) {
    val listItems = tickets
    val disabledItem = 1
    var expanded by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {
        IconButton(onClick = {
            expanded = true
        }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = ""
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            listItems.forEachIndexed { itemIndex, itemValue ->
                DropDownMenuItem(
                    onClick = {
                        expanded = false
                    },
                    enabled = (itemIndex != disabledItem)
                ) {
                    Text(text = itemValue.ticketName.toString())
                }
            }

        }
    }
}


@Composable
fun DropDownMenuItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = MenuDefaults.DropdownMenuItemContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {

}

@Composable
fun RosterTabs(
    modifier: Modifier,
    rosters: List<RostersModel>,
) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf(
        rosters.getOrNull(0)?.team?.abbreviation,
        rosters.getOrNull(1)?.team?.abbreviation
    )


}

@Composable
fun TabsLastFiveGames(
    modifier: Modifier,
    lastFiveGames: List<LastFiveGamesModel>,
) {

    var tabIndex by remember { mutableStateOf(0) }

    val tabTitles = listOf(
        lastFiveGames.getOrNull(0)?.team,
        lastFiveGames.getOrNull(1)?.team)

    DefaultCard(modifier = modifier) {

        TabRow(
            selectedTabIndex = tabIndex,
        ) {
            tabTitles.forEachIndexed { index, team ->
                Tab(
                    modifier = Modifier.background(Color.LightGray),
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Box() {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                GenericImageLoader(
                                    obj = team?.logo.toString(),
                                    modifier = modifier.size(30.dp)
                                )
                                Spacer(modifier = modifier.width(8.dp))
                                Text(text = team?.abbreviation ?: "")

                            }
                        }
                    },
                )
            }
        }
        when (tabIndex) {
            0 -> LastFiveGames2(lastFiveGames = lastFiveGames, teamInt = 0)
            1 -> LastFiveGames2(lastFiveGames = lastFiveGames, teamInt = 1)
        }
    }

}


@Composable
fun LastFiveGameRow(lastEvents: GameDetailsEventModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = lastEvents.gameDate.toDate()?.formatTo("MM-dd-yyyy") ?: "", fontSize = 10.sp)
        Spacer(modifier = Modifier.width(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = lastEvents.atVs,
                fontSize = 16.sp
            )
            GenericImageLoader(obj = lastEvents.opponent.logo, modifier = Modifier.size(30.dp))
            Text(
                text = lastEvents.opponent.abbreviation,
                fontSize = 12.sp,
                textAlign = TextAlign.Start)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = lastEvents.gameResult, fontSize = 12.sp, textAlign = TextAlign.Start)
            Text(text = lastEvents.score, fontSize = 12.sp, textAlign = TextAlign.Start)
        }
    }
}


@Composable
fun LastFiveGames2(
    lastFiveGames: List<LastFiveGamesModel>,
    teamInt: Int,
) {
    val team1Info = lastFiveGames.getOrNull(teamInt)

    if (lastFiveGames.isEmpty()) {
        Text(text = "")
    } else {

        CardHeaderText(text = "Last Five Games")
        NormalDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Text(text = "DATE")
            Text(text = "OPP")
            Text(text = "RESULT")
        }
        team1Info?.lastEvents?.map { LastFiveGameRow(lastEvents = it) }

    }


}

@Composable
fun GameInfoCardVenueImage(
    gameDetailModel: GameDetailsModel,
    modifier: Modifier,
) {
    Box(modifier = modifier.height(200.dp)) {
        DetailVenueCardImageLoader(
            venue = gameDetailModel.gameInfo?.venue ?: GameDetailsVenueModel()
        )
        if (gameDetailModel.gameInfo?.venue?.images?.isNotEmpty() == true) {
            GenericImageLoader(obj = gameDetailModel.gameInfo.venue.images.first().href ?: "",
                modifier = modifier)

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Row() {
                val offset = Offset(5.0f, 5.0f)
                Text(
                    text = gameDetailModel.gameInfo?.venue?.fullName ?: "",
                    style = TextStyle(
                        fontSize = 50.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = offset,
                            blurRadius = 3f
                        )
                    ),
                    textAlign = TextAlign.Right,
                    color = Color.White
                )
            }

        }
    }
}







