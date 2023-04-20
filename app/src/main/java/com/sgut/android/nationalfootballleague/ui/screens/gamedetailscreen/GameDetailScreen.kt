package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import android.util.Log
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
import androidx.navigation.NavController
import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.domain.domainmodels.GameDetailModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.*
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.*
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2
import com.sgut.android.nationalfootballleague.utils.formatTo
import com.sgut.android.nationalfootballleague.utils.toDate
import kotlin.math.nextUp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    modifier: Modifier = Modifier,
    sport: String,
    league: String,
    navController: NavController,
    event: String,
    gameDetailViewModel: GameDetailViewModel = hiltViewModel(),
) {

    gameDetailViewModel.loadGameDetails(sport, league, event)

    val gameDetailUiState by gameDetailViewModel.gameDetailUiState.collectAsState()
//    var state by remember { mutableStateOf(gameDetailViewModel.gameDetailUiState.value)}


//    val map by remember {gameDetailViewModel.teamMap}

    Scaffold(
        content = { padding ->
            Column(
                modifier
                    .verticalScroll(rememberScrollState())
                    .padding(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

//                LastPlay(play = gameDetailUiState.currentGameUiState?.plays?.last()?.text ?: "")

                CompetitionStatus(
                    modifier = modifier,
                    competitions = gameDetailUiState.currentGameUiState?.header?.competitions
                        ?: listOf()
                )




//                Text(text = gameDetailUiState.currentGameUiState.header.competitions.f)

                SpacerDp(modifier = modifier, height = EIGHT)

                HeaderStatusSlot(
                    modifier = modifier,
                    gameDetailModel = gameDetailUiState.currentGameUiState ?: GameDetailsModel()
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                WeightedRows(
                    modifier = modifier,
                    header = gameDetailUiState.currentGameUiState ?: GameDetailsModel()
                )

                SpacerDp(modifier = modifier, height = EIGHT)

                when (gameDetailUiState.currentSport) {
                    "basketball" -> DoughnutChartForBasketball(
                        modifier = modifier,
                        gameDetailModel = gameDetailUiState.currentGameUiState
                            ?: GameDetailsModel(),
                    )
                    "football" -> DoughnutChart2(
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
                        teamMap = gameDetailViewModel.teamMap
                    )
                }

                SpacerDp(modifier = modifier, height = EIGHT)

                SeasonLeaders(
                    modifier = modifier,
                    gameDetailModel = gameDetailUiState.currentGameUiState ?: GameDetailsModel()
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

                ExpandableGameArticle(
                    modifier =  modifier,
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

                TeamStatCard3(
                    modifier = modifier,
                    boxscore = gameDetailUiState.currentGameUiState?.boxscore
                    ?: BoxScoreModel()
                )
            }

        }
    )
}



@Composable
fun BaseballSpecific(
    modifier: Modifier,
    gameDetailSituation: SituationModel,
    gameDetailsModel: GameDetailsModel,
    teamMap: Map<String, GameDetailsAthleteDetailsModel>,
) {
    BaseballSituation(
        modifier = modifier,
        gameDetailSituation = gameDetailSituation,
        teamMap = teamMap,
        competition = gameDetailsModel.header?.competitions?.first()
            ?: GameDetailsCompetitionModel()
    )

    SpacerDp(modifier = modifier, height = EIGHT)

    DoughnutChart2(
        modifier = modifier,
        gameDetailModel = gameDetailsModel
    )

    SpacerDp(modifier = modifier, height = EIGHT)


    ProbablesList(list = gameDetailsModel.header?.competitions?.first()?.competitors ?: listOf(), modifier = modifier)

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
                                obj = competitors.team?.logos?.href ?: "",
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
        obj = team.logos.href,
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
fun GameArticle(gameDetailModel: GameDetailModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)) {
            Text(
                text = gameDetailModel.singleArticle?.headline ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = gameDetailModel.singleArticle?.description ?: "",
                fontSize = 14.sp
            )

            Row() {

//                Text(text = gameDetailModel.singleArticle?.story ?: "") for expand anim
                Text(text = gameDetailModel.singleArticle?.published ?: "")
                Text(text = " - ")
                Text(text = gameDetailModel.singleArticle?.source ?: "")
            }
        }
    }
}

@Composable
fun LastPlay(play: String) {
    Text(text = play)
}

@Composable
fun SeasonLeaders(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
) {
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Season Leaders")
        NormalDivider()
        Row(
        ) {
            gameDetailModel.leaders.map { gameDetailsLeaders ->
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

        SpacerDp(modifier = modifier, width = SIXTEEN)

        LazyRow(contentPadding = PaddingValues(EIGHT.dp)) {
            items(items = vidList) { vid ->
                VideoPreview(video = vid, modifier = modifier.padding(EIGHT.dp))
            }
        }
    }


}


@Composable
fun VideoPreview(
    video: VideoModel,
    modifier: Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Column(modifier = modifier.fillMaxWidth()) {

                VideoPlayer(video)
                Log.d("Video", video.links.source?.mezzanine?.href ?: "")

                GenericImageLoader(
                    obj = video.thumbnail,
                    modifier = modifier.width(200.dp)
                )

                Text(
                    text = video.headline,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(8.dp),
                    textAlign = TextAlign.Left,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
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
    boxscore: BoxScoreModel
) {
    DefaultCard(modifier = modifier) {
            CardHeaderText(text = "Team Stats")
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
    DefaultCard(modifier = Modifier, ) {

        CardHeaderText(text = "Probables")
        NormalDivider()
            Text(text = list.first().probables.first().displayName, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = modifier.height(16.dp))
            list.map { competitor ->
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = competitor.team?.displayName ?: "", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GenericImageLoader(obj = competitor.probables.first().athlete?.headshot?.href ?: "", modifier = modifier.fillMaxWidth())
                        Text(text = competitor.probables.first().athlete?.displayName ?: "", fontSize = 15.sp)

                    }
                }
            }


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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AthleteNameAndPosition(athlete = it.athlete, modifier = modifier)
                Text(text = it.status, textAlign = TextAlign.Right)
            }
        }
    }

}

@Composable
fun AthleteNameAndPosition(athlete: GameDetailsAthleteDetailsModel, modifier: Modifier) {
    Row() {
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
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Current Situation")
        InningText(competition = competition)
        Divider()
        OutsBallsStrikes(gameDetailSituation = gameDetailSituation)
        Divider()

        if (
            competition.status?.periodPrefix.equals("End") ) {
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
                Player(player = teamMap[gameDetailSituation.batter?.playerId.toString()]
                    ?: GameDetailsAthleteDetailsModel())

                Text(text = "Vs", fontWeight = FontWeight.Bold, fontSize = 20.sp)

                Player(player = teamMap[gameDetailSituation.pitcher?.playerId.toString()]
                    ?: GameDetailsAthleteDetailsModel())
            }
        }
//            Text(text = gameDetailSituation.toString())
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
            Row{
                teamMap[it.playerId]?.let { it1 -> Player(player = it1) }

            }
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

            GenericImageLoader(
                obj = player.headshot?.href ?: "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(
                        CircleShape)
                    .background(Color.LightGray))
            Row() {
                Text(text = player.shortName, fontSize = 10.sp)
                SpacerDp(modifier = Modifier, width = SIXTEEN)
                Text(text = player.position?.abbreviation ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp)


            }

        }
    }
}

@Composable
fun PitcherVsBatter(gameDetailSituation: SituationModel) {

}

@Composable
fun OutsBallsStrikes(
    gameDetailSituation: SituationModel,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Outs " + gameDetailSituation.outs.toString(),
            style = MaterialTheme.typography.bodyMedium)
        Text(text = "Balls " + gameDetailSituation.balls.toString(),
            style = MaterialTheme.typography.bodyMedium)
        Text(text = "Strikes " + gameDetailSituation.strikes.toString(),
            style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun InningText(competition: GameDetailsCompetitionModel) {

    Text(text = competition.status?.type?.gameTimeDetail ?: "",
        style = MaterialTheme.typography.bodyMedium)
    NormalDivider()
    Text(text = competition.status?.periodPrefix ?: "")

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
                    color = colors.getOrElse(int, {color -> Color.White}),
                    legend = legends.getOrElse(int, { word -> "" })
                )
            }

        }
    }

}




@Composable
fun PickCenter(
    pickCenterInfo: PickcenterModel,
) {
    Card() {
        Text(text = pickCenterInfo.provider.name)
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
                AddressComp(gameDetailModel = gameDetailModel)
                Text(
                    text = gameDetailModel.gameInfo?.weather?.temperature ?: "",
                    fontWeight = FontWeight.Bold)
            }
            Divider()
            Row(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(text = "CAPACITY: ", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = gameDetailModel.gameInfo?.venue?.capacity.toString(), fontSize = 12.sp)
            }
    }
}

@Composable
fun AddressComp(gameDetailModel: GameDetailsModel) {
    Row() {
        Text(text = gameDetailModel.gameInfo?.venue?.address?.city ?: "")
        Text(text = ", ")
        Text(text = gameDetailModel.gameInfo?.venue?.address?.state ?: "")
    }
}


@Composable
fun TeamVsComponent() {

}


fun getTeamsColorsList(gameDetailModel: GameDetailModel): List<Color?> {
    var list = listOf<Color?>()
    for (i in gameDetailModel.boxscore?.teams!!) {
        list = listOf(HexToJetpackColor2.getColor(i.team?.color ?: "")

        )
    }
    return list
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

        Card(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.LightGray),

        ) {

                CardHeaderText(text = "Find Tickets")


            NormalDivider()

            Row(
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "$team1 vs $team2",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "$venueName - $dateDay $shortDate",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
            Row(
                modifier = modifier.align(Alignment.CenterHorizontally)
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
                modifier = modifier.align(Alignment.CenterHorizontally)
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
        }
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
    rosters: List<RostersModel>
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
    lastFiveGames: List<LastFiveGamesModel>) {

    var tabIndex by remember { mutableStateOf(0) }

    val tabTitles = listOf(
        lastFiveGames.getOrNull(0)?.team,
        lastFiveGames.getOrNull(1)?.team)

    DefaultCard(modifier = modifier) {

        TabRow(selectedTabIndex = tabIndex) {
            tabTitles.forEachIndexed { index, team ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Box() {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                GenericImageLoader(obj = team?.logo.toString(),
                                    modifier = modifier.size(30.dp))
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
        modifier = Modifier.fillMaxWidth(1f),
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
    val team2Info = lastFiveGames.getOrNull(1)

    if (lastFiveGames.isEmpty()) {
        Text(text = "")
    } else {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            CardHeaderText(text = "Last Five Games")
            NormalDivider()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "DATE")
                Text(text = "OPP")
                Text(text = "RESULT")
            }
            team1Info?.lastEvents?.map { LastFiveGameRow(lastEvents = it) }
        }
    }


}

@Composable
fun GameInfoCardVenueImage(
    gameDetailModel: GameDetailsModel,
    modifier: Modifier,
) {
    Box(modifier = Modifier.height(200.dp)) {
        DetailVenueCardImageLoader(venue = gameDetailModel.gameInfo?.venue
            ?: GameDetailsVenueModel())

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






