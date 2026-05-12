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
import timber.log.Timber
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.Dialog
import java.text.NumberFormat


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

    val competition = gameDetailUiState.currentGameUiState?.header?.competitions?.firstOrNull()
    LaunchedEffect(competition) {
        Timber.d("SAL_GUT periodPrefix=${competition?.status?.periodPrefix?.name} competitionId=${competition?.id}")
    }


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

    if (gameDetailsModel.rosters.isNotEmpty()) {
        SpacerDp(modifier = modifier, height = EIGHT)
        BaseballRosterCard(modifier = modifier, rosters = gameDetailsModel.rosters)
    }

}

// Baseball-specific stat abbreviations to display in dropdown
private val BASEBALL_BATTING_STATS = setOf("AB", "H", "RBI", "SB", "BB", "SO", "HR", "AVG", "OBP", "SLG", "OPS", "R", "2B", "3B")
private val BASEBALL_PITCHING_STATS = setOf("IP", "H", "R", "ER", "BB", "SO", "HR", "ERA", "PC-ST", "BF")

@Composable
fun BaseballRosterCard(
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

@Composable
fun BaseballRosterPlayerRow(
    player: RosterModel,
) {
    var expanded by remember { mutableStateOf(false) }

    val battingStats = player.stats.filter { it.abbreviation in BASEBALL_BATTING_STATS }
    val pitchingStats = player.stats.filter { it.abbreviation in BASEBALL_PITCHING_STATS }
    val displayStats = battingStats.ifEmpty { pitchingStats }
    val hasStats = displayStats.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = hasStats) { expanded = !expanded }
            .animateContentSize(animationSpec = tween(200))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Bat order badge
            Text(
                text = if (player.batOrder > 0) player.batOrder.toString() else "",
                modifier = Modifier.width(28.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            // Headshot
            GenericImageLoader(
                obj = player.athlete.headshot?.href ?: "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Name + position
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = player.athlete.displayName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = player.position.displayName,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f)
                )
            }

            // Position abbreviation + expand indicator
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = player.position.abbreviation,
                    modifier = Modifier.width(32.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.End
                )
                if (hasStats) {
                    Text(
                        text = if (expanded) "▴" else "▾",
                        modifier = Modifier.padding(start = 6.dp),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )
                }
            }
        }

        // Expanded stats
        if (expanded && hasStats) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
            ) {
                Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                    Text(
                        text = if (battingStats.isNotEmpty()) "BATTING STATS" else "PITCHING STATS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    // Stats in a wrapping row grid
                    val chunked = displayStats.chunked(3)
                    chunked.forEach { rowStats ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            rowStats.forEach { stat ->
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = stat.displayValue,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = stat.abbreviation,
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.55f),
                                        letterSpacing = 0.5.sp
                                    )
                                }
                            }
                            // Fill remaining columns if row is not full
                            repeat(3 - rowStats.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
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
    val competition = header.header?.competitions?.firstOrNull() ?: return
    val competitors = competition.competitors
    if (competitors.isEmpty()) return

    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Team Records")
        NormalDivider()
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            competitors.forEach { competitor ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        GenericImageLoader(
                            obj = competitor.team?.logos?.getOrNull(0)?.href ?: "",
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = competitor.team?.abbreviation ?: "",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    competitor.team?.record?.firstOrNull()?.let { record ->
                        Text(
                            text = record.summary,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
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
    if (leaders.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Season Leaders")
        NormalDivider()
        leaders.forEach { teamLeaders ->
            // Team header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GenericImageLoader(
                    obj = teamLeaders.team.logo,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = teamLeaders.team.abbreviation,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            teamLeaders.leaders.forEach { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, top = 4.dp),
                ) {
                    Text(
                        text = category.displayName.uppercase(),
                        fontSize = 9.sp,
                        color = Color.Gray,
                        letterSpacing = 0.6.sp
                    )
                }
                category.leadersAthlete.forEach { athlete ->
                    SeasonLeadersPlayer(athlete = athlete)
                }
                NormalDivider()
            }
        }
    }
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
    if (leaders.isEmpty()) return
    Column {
        leaders.forEach { category ->
            val topAthlete = category.leadersAthlete.firstOrNull() ?: return@forEach
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                EnlargeableAthleteImage(
                    imageUrl = topAthlete.athlete.headshot?.href ?: "",
                    contentDescription = topAthlete.athlete.displayName,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = category.displayName.uppercase(),
                        fontSize = 9.sp,
                        color = Color.Gray,
                        letterSpacing = 0.6.sp
                    )
                    Text(
                        text = topAthlete.athlete.displayName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = topAthlete.athlete.position?.abbreviation ?: "",
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
                Text(
                    text = topAthlete.displayValue,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 0.5.dp)
        }
    }
}


@Composable
fun BoxScoreTeamStats(boxscore: BoxScoreModel, modifier: Modifier) {
    if (boxscore.statistics.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Team Stats")
        NormalDivider()
        boxscore.statistics.map { stats ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stats.name, fontSize = 13.sp)
                Text(text = stats.displayValue, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            }
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        EnlargeableAthleteImage(
            imageUrl = athlete.athlete.headshot?.href ?: "",
            contentDescription = athlete.athlete.shortName ?: "",
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = athlete.athlete.shortName ?: "",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = athlete.athlete.position?.abbreviation ?: "",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
        Text(
            text = athlete.displayValue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
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
    Column(modifier = Modifier.padding(top = 4.dp)) {
        list.forEach { stat ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stat.name.uppercase(),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray,
                    letterSpacing = 0.6.sp
                )
                Text(
                    text = stat.displayValue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 0.5.dp)
        }
    }
}


@Composable
fun TeamStatCard3(
    modifier: Modifier,
    boxscore: BoxScoreModel,
) {
    if (boxscore.teams.isEmpty()) return
    val team0 = boxscore.teams.getOrNull(0)
    val team1 = boxscore.teams.getOrNull(1)
    val stats0 = team0?.statistics ?: emptyList()
    val stats1 = team1?.statistics ?: emptyList()

    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Team Stats")
        NormalDivider()

        // Team header row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GenericImageLoader(
                    obj = team0?.team?.logos?.firstOrNull()?.href ?: "",
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = team0?.team?.abbreviation ?: "",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "STAT",
                fontSize = 9.sp,
                color = Color.Gray,
                letterSpacing = 1.sp
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = team1?.team?.abbreviation ?: "",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                GenericImageLoader(
                    obj = team1?.team?.logos?.firstOrNull()?.href ?: "",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
        NormalDivider()

        // Stat comparison rows
        stats0.forEachIndexed { index, stat ->
            val stat1 = stats1.getOrNull(index)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 7.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stat.displayValue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stat.name.uppercase(),
                    fontSize = 9.sp,
                    color = Color.Gray,
                    letterSpacing = 0.5.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1.5f)
                )
                Text(
                    text = stat1?.displayValue ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 0.5.dp)
        }
    }
}


@Composable
fun SeasonLeadersPlayer(athlete: AthleteLeaderModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        EnlargeableAthleteImage(
            imageUrl = athlete.athlete.headshot?.href ?: "",
            contentDescription = athlete.athlete.displayName,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = athlete.athlete.displayName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = athlete.athlete.position?.abbreviation ?: "",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
        Text(
            text = athlete.displayValue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
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
    val home = list.getOrNull(0) ?: return
    val away = list.getOrNull(1) ?: return
    val homeColor = home.team?.color?.let { HexToJetpackColor2.getColor(it) } ?: Color.DarkGray
    val awayColor = away.team?.color?.let { HexToJetpackColor2.getColor(it) } ?: Color.Gray
    val homeProbable = home.probables.getOrNull(0)
    val awayProbable = away.probables.getOrNull(0)

    DefaultCard(modifier = modifier) {
        CardHeaderText(text = "Probable Starters")
        NormalDivider()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        ) {
            // Diagonal split background
            Canvas(modifier = Modifier.fillMaxSize()) {
                val diagStart = size.width * 0.44f
                val diagEnd   = size.width * 0.56f

                val leftPath = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(diagEnd, 0f)
                    lineTo(diagStart, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(leftPath, homeColor)

                val rightPath = Path().apply {
                    moveTo(diagEnd, 0f)
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height)
                    lineTo(diagStart, size.height)
                    close()
                }
                drawPath(rightPath, awayColor)
            }

            // Content row over the diagonal background
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home side
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    EnlargeableAthleteImage(
                        imageUrl = homeProbable?.athlete?.headshot?.href ?: "",
                        contentDescription = homeProbable?.athlete?.displayName,
                        modifier = Modifier.size(64.dp).clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Text(
                        text = home.team?.abbreviation ?: "",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Text(
                        text = homeProbable?.athlete?.displayName ?: "TBD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White.copy(alpha = 0.9f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 120.dp)
                    )
                }

                // VS badge
                Text(
                    text = "VS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.6f),
                            offset = Offset(1f, 1f),
                            blurRadius = 4f
                        )
                    )
                )

                // Away side
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    EnlargeableAthleteImage(
                        imageUrl = awayProbable?.athlete?.headshot?.href ?: "",
                        contentDescription = awayProbable?.athlete?.displayName,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                    Text(
                        text = away.team?.abbreviation ?: "",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = awayProbable?.athlete?.displayName ?: "TBD",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White.copy(alpha = 0.9f),
                        textAlign = TextAlign.End,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 120.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PitcherMatchUp(competitor: GameDetailsCompetitorModel, modifier: Modifier) {
    val teamColor = competitor.team?.color?.let { HexToJetpackColor2.getColor(it) } ?: return
    val probable = competitor.probables.getOrNull(0)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(teamColor)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = competitor.team.abbreviation,
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = Color.White.copy(alpha = 0.9f)
            )
            Text(
                text = "SP",
                fontSize = 11.sp,
                color = Color.White.copy(alpha = 0.6f),
                letterSpacing = 1.sp
            )
            Text(
                text = probable?.athlete?.displayName ?: "TBD",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        BasicImage(
            imgUrl = probable?.athlete?.headshot?.href ?: "",
            contentDescription = probable?.athlete?.displayName,
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            borderWidth = 0.dp,
            borderColor = Color.Transparent,
            modifier = Modifier.size(72.dp).clip(CircleShape)
        )
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
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BasicImage(
                imgUrl = competitor.team?.logos?.firstOrNull()?.href ?: "",
                contentDescription = competitor.team?.name,
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                borderWidth = 0.dp,
                borderColor = Color.Transparent,
                modifier = Modifier.size(44.dp)
            )
            Text(
                text = competitor.team?.abbreviation ?: "",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = competitor.record.firstOrNull()?.summary ?: "",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
        Text(
            text = competitor.score.toString(),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )
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
    Column {
        injuries.injuries.forEach { injury ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AthleteNameAndPosition(athlete = injury.athlete, modifier = modifier)
                val statusColor = when (injury.status.lowercase()) {
                    "out" -> Color(0xFFD32F2F)
                    "doubtful" -> Color(0xFFE64A19)
                    "questionable" -> Color(0xFFF57C00)
                    "probable" -> Color(0xFF388E3C)
                    else -> Color.Gray
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = statusColor.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = injury.status,
                        color = statusColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f), thickness = 0.5.dp)
        }
    }
}


@Composable
fun AthleteNameAndPosition(athlete: GameDetailsAthleteDetailsModel, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        EnlargeableAthleteImage(
            imageUrl = athlete.headshot?.href ?: "",
            contentDescription = athlete.displayName,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
        Column {
            Text(
                text = athlete.displayName,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "#${athlete.jersey}  ${athlete.position?.abbreviation ?: ""}",
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
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
) {
    val isEndOfInning = competition.status?.periodPrefix == InningPrefix.END
    val inningDetail = competition.status?.type?.gameTimeDetail ?: ""
    val onFirst = gameDetailSituation.onFirst?.playerId != null
    val onSecond = gameDetailSituation.onSecond?.playerId != null
    val onThird = gameDetailSituation.onThird?.playerId != null

    DefaultCard(modifier = modifier.fillMaxWidth()) {

        // Header: inning info + outs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "CURRENT SITUATION",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = inningDetail,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            // Outs indicator
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "OUTS",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    letterSpacing = 1.5.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    repeat(3) { idx ->
                        val filled = idx < gameDetailSituation.outs
                        Canvas(modifier = Modifier.size(14.dp)) {
                            drawCircle(
                                color = if (filled) Color(0xFFE53935) else Color(0xFFBDBDBD),
                                radius = size.minDimension / 2f
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))

        // Count row: Balls + Strikes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Balls
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "B",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF43A047),
                    letterSpacing = 1.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    repeat(4) { idx ->
                        val filled = idx < gameDetailSituation.balls
                        Canvas(modifier = Modifier.size(13.dp)) {
                            drawCircle(
                                color = if (filled) Color(0xFF43A047) else Color(0xFFBDBDBD),
                                radius = size.minDimension / 2f
                            )
                        }
                    }
                }
            }
            // Strikes
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "S",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFF9A825),
                    letterSpacing = 1.sp
                )
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    repeat(3) { idx ->
                        val filled = idx < gameDetailSituation.strikes
                        Canvas(modifier = Modifier.size(13.dp)) {
                            drawCircle(
                                color = if (filled) Color(0xFFF9A825) else Color(0xFFBDBDBD),
                                radius = size.minDimension / 2f
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))

        // Baseball diamond + batter/pitcher section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Batter or Due Up column
            if (isEndOfInning) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "DUE UP",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        gameDetailSituation.dueUp.take(3).forEach { dueUpItem ->
                            val player = teamMap[dueUpItem.playerId]
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                EnlargeableAthleteImage(
                                    imageUrl = player?.headshot?.href ?: "",
                                    contentDescription = player?.displayName,
                                    modifier = Modifier
                                        .size(52.dp)
                                        .clip(CircleShape)
                                        .border(2.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), CircleShape)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = player?.shortName ?: "TBD",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = dueUpItem.batOrder,
                                    fontSize = 9.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
            } else {
                // Batter
                val batter = teamMap[gameDetailSituation.batter?.playerId?.toString()]
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "AT BAT",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF43A047),
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    EnlargeableAthleteImage(
                        imageUrl = batter?.headshot?.href ?: "",
                        contentDescription = batter?.displayName,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFF43A047), CircleShape)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = batter?.shortName ?: "—",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = batter?.position?.abbreviation ?: "",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }

            // Baseball Diamond (canvas)
            BaseballDiamond(
                onFirst = onFirst,
                onSecond = onSecond,
                onThird = onThird,
                modifier = Modifier.size(110.dp)
            )

            // Pitcher
            if (!isEndOfInning) {
                val pitcher = teamMap[gameDetailSituation.pitcher?.playerId?.toString()]
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "PITCHING",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE53935),
                        letterSpacing = 1.5.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    EnlargeableAthleteImage(
                        imageUrl = pitcher?.headshot?.href ?: "",
                        contentDescription = pitcher?.displayName,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFFE53935), CircleShape)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = pitcher?.shortName ?: "—",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = pitcher?.position?.abbreviation ?: "",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // Runners on base labels
        if (!isEndOfInning && (onFirst || onSecond || onThird)) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                listOf(
                    Triple("1ST", gameDetailSituation.onFirst?.playerId, onFirst),
                    Triple("2ND", gameDetailSituation.onSecond?.playerId, onSecond),
                    Triple("3RD", gameDetailSituation.onThird?.playerId, onThird)
                ).filter { it.third }.forEach { (label, playerId, _) ->
                    val runner = teamMap[playerId.toString()]
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .background(Color(0xFFF9A825), RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = label,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }
                        EnlargeableAthleteImage(
                            imageUrl = runner?.headshot?.href ?: "",
                            contentDescription = runner?.displayName,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color(0xFFF9A825), CircleShape)
                        )
                        Text(
                            text = runner?.shortName ?: "Runner",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        // Last play
        val lastPlayText = gameDetailSituation.lastPlay?.text
        if (!lastPlayText.isNullOrBlank()) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
            Text(
                text = lastPlayText,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
fun BaseballDiamond(
    onFirst: Boolean,
    onSecond: Boolean,
    onThird: Boolean,
    modifier: Modifier = Modifier,
) {
    val baseColor = Color(0xFFF9A825)
    val emptyColor = Color(0xFFBDBDBD)
    val homeColor = Color(0xFF757575)

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        // Diamond corners: home=bottom, first=right, second=top, third=left
        val homeX = cx; val homeY = h * 0.85f
        val firstX = w * 0.85f; val firstY = cy
        val secondX = cx; val secondY = h * 0.15f
        val thirdX = w * 0.15f; val thirdY = cy

        val baseSizeRatio = 0.10f
        val bw = w * baseSizeRatio
        val bh = h * baseSizeRatio

        fun drawDiamond(centerX: Float, centerY: Float, filled: Boolean) {
            val p = Path().apply {
                moveTo(centerX, centerY - bh)
                lineTo(centerX + bw, centerY)
                lineTo(centerX, centerY + bh)
                lineTo(centerX - bw, centerY)
                close()
            }
            drawPath(p, color = if (filled) baseColor else emptyColor)
        }

        // Draw baselines
        val lineColor = emptyColor.copy(alpha = 0.4f)
        drawLine(lineColor, Offset(homeX, homeY), Offset(firstX, firstY), strokeWidth = 2f)
        drawLine(lineColor, Offset(firstX, firstY), Offset(secondX, secondY), strokeWidth = 2f)
        drawLine(lineColor, Offset(secondX, secondY), Offset(thirdX, thirdY), strokeWidth = 2f)
        drawLine(lineColor, Offset(thirdX, thirdY), Offset(homeX, homeY), strokeWidth = 2f)

        // Draw bases
        drawDiamond(secondX, secondY, onSecond)
        drawDiamond(firstX, firstY, onFirst)
        drawDiamond(thirdX, thirdY, onThird)

        // Home plate
        val hp = Path().apply {
            moveTo(homeX, homeY - bh * 0.8f)
            lineTo(homeX + bw * 0.8f, homeY)
            lineTo(homeX + bw * 0.5f, homeY + bh * 0.6f)
            lineTo(homeX - bw * 0.5f, homeY + bh * 0.6f)
            lineTo(homeX - bw * 0.8f, homeY)
            close()
        }
        drawPath(hp, color = homeColor)
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
fun EnlargeableAthleteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    var enlarged by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures(onLongPress = { enlarged = true })
        }
    ) {
        GenericImageLoader(obj = imageUrl, modifier = Modifier.fillMaxSize())
    }

    if (enlarged) {
        Dialog(onDismissRequest = { enlarged = false }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { enlarged = false }
                    .padding(bottom = 16.dp)
            ) {
                GenericImageLoader(
                    obj = imageUrl,
                    modifier = Modifier
                        .size(280.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                )
                contentDescription?.takeIf { it.isNotEmpty() }?.let {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
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

            EnlargeableAthleteImage(
                imageUrl = player.headshot?.href ?: "",
                contentDescription = player.displayName,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.LightGray, CircleShape)
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
                    VerticalDivider(
                        color = Color.Black,
                        modifier = modifier.height(100.dp),
                        thickness = 1.dp
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

        list.forEach { pickCenter ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = pickCenter.provider.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = pickCenter.details, fontSize = 12.sp)
                    Text(text = "O/U  ${pickCenter.overUnder}", fontSize = 12.sp)
                    Text(text = "Spread  ${pickCenter.spread}", fontSize = 12.sp)
                }
            }
            NormalDivider()
        }
    }
}


@Composable
fun GameInformation(
    modifier: Modifier,
    gameDetailModel: GameDetailsModel,
) {
    val info = gameDetailModel.gameInfo
    val venue = info.venue
    val weather = info.weather
    val numFormat = NumberFormat.getNumberInstance()

    DefaultCard(modifier = modifier) {

        // Hero venue image with gradient overlay + weather badge
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        ) {
            if (venue.images.isNotEmpty()) {
                GenericImageLoader(
                    obj = venue.images.first().href ?: "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            // bottom gradient for venue text legibility
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.82f))
                        )
                    )
            )
            // top gradient for weather badge legibility
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0f to Color.Black.copy(alpha = 0.35f),
                                0.4f to Color.Transparent
                            )
                        )
                    )
            )

            // Weather badge — top right
            if (weather.temperature.isNotBlank() || weather.conditionId.isNotBlank()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    if (weather.temperature.isNotBlank()) {
                        Text(
                            text = "${weather.temperature}°F",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    if (weather.conditionId.isNotBlank()) {
                        Text(
                            text = weather.conditionId,
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp
                        )
                    }
                    if (weather.highTemperature > 0 || weather.lowTemperature > 0) {
                        Text(
                            text = "H:${weather.highTemperature}°  L:${weather.lowTemperature}°",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 10.sp
                        )
                    }
                    if (weather.gust > 0 || weather.precipitation > 0) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (weather.gust > 0) {
                                Text(
                                    text = "\uD83C\uDF2C ${weather.gust}mph",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 10.sp
                                )
                            }
                            if (weather.precipitation > 0) {
                                Text(
                                    text = "\uD83C\uDF27 ${weather.precipitation}%",
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }

            // Venue name + location — bottom left
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp)
            ) {
                Text(
                    text = venue.fullName,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${venue.address.city}, ${venue.address.state}",
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 12.sp
                )
            }
        }

        // Venue stats row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            if (venue.capacity > 0) {
                Column {
                    Text(
                        text = "CAPACITY",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = numFormat.format(venue.capacity),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Column {
                Text(
                    text = "SURFACE",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    letterSpacing = 1.sp
                )
                Text(
                    text = if (venue.grass) "Grass" else "Turf",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            if (info.attendance > 0) {
                Column {
                    Text(
                        text = "ATTENDANCE",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = numFormat.format(info.attendance),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Officials section
        if (info.officials.isNotEmpty()) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(
                    text = "OFFICIALS",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                info.officials.forEach { official ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = official.displayName ?: "",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = official.position.displayName ?: "",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
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
    val resultColor = when (lastEvents.gameResult.uppercase()) {
        "W" -> Color(0xFF2E7D32)
        "L" -> Color(0xFFC62828)
        else -> Color.Gray
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = lastEvents.gameDate.toDate()?.formatTo("MMM d") ?: "",
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier.width(40.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = lastEvents.atVs, fontSize = 11.sp, color = Color.Gray)
            GenericImageLoader(obj = lastEvents.opponent.logo, modifier = Modifier.size(26.dp))
            Text(
                text = lastEvents.opponent.abbreviation,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = resultColor.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 7.dp, vertical = 2.dp)
            ) {
                Text(
                    text = lastEvents.gameResult.uppercase(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = resultColor
                )
            }
            Text(
                text = lastEvents.score,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
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
        team1Info?.lastEvents?.forEach { event ->
            LastFiveGameRow(lastEvents = event)
            NormalDivider()
        }

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
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
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







