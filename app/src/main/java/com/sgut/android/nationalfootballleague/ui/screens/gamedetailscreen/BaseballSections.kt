package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.GameDetailsBroadcasts
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_article.ArticleDomainModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.AgainstTheSpreadModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.BaseballPlayModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsFormatModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameMetaModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.NewsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.OddsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.PredictorModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.ScoringPlayModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.SeasonSeriesModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.DefaultCard
import com.sgut.android.nationalfootballleague.ui.commoncomps.GenericImageLoader
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.Instant

/**
 * Section composables for [GameDetailsModel] properties that weren't previously
 * surfaced for baseball games:
 *
 *   • notes               → [NotesBanner]
 *   • format              → [GameFormatCard]
 *   • broadcasts          → [BroadcastsCard]
 *   • predictor           → [PredictorCard]
 *   • odds                → [OddsCard]
 *   • againstTheSpread    → [AgainstTheSpreadCard]
 *   • news                → [NewsRailCard]
 *   • scoringPlays        → [ScoringPlaysCard]
 *   • seasonseries        → [SeasonSeriesCard]
 *   • baseballPlays       → [BaseballPlaysCard]
 *   • meta                → [MetaFooter]
 *
 * Each section is conditional on its data being non-empty so off-season /
 * unavailable data never produces orphan headers. Visual style follows the
 * Swiss-typographic patterns in [GameDetailsPolish] (tracked uppercase
 * labels, hairline dividers, restrained palette, team-color accents).
 */

// ----- Notes (rain delay, etc.) -------------------------------------------

/**
 * Warning-style banner used for short status notes ESPN appends to the game —
 * most commonly "Rain delay" or "Postponed" reasons. Single-line items get a
 * leading caution emoji and live on a soft tinted background so they read as
 * "operational note" rather than another stat card.
 */
@Composable
fun NotesBanner(
    notes: List<String>,
    modifier: Modifier = Modifier,
) {
    if (notes.isEmpty()) return
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.6f)),
    ) {
        notes.forEach { note ->
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(text = "⚠", fontSize = 14.sp)
                Text(
                    text = note,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
        }
    }
}

// ----- Game Format ---------------------------------------------------------

/**
 * Surfaces the rules-of-this-game metadata: regulation period count + slug
 * + clock length. Mostly informational, but useful for postseason vs regular
 * season MLB and for spring training rules variations.
 */
@Composable
fun GameFormatCard(
    format: GameDetailsFormatModel,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    val reg = format.regulation
    if (reg.periods == 0 && reg.displayName.isBlank()) return

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "📐",
            title = "Game Format",
            accentColors = teamColors,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FormatStat(label = "Periods", value = reg.periods.toString())
            if (reg.displayName.isNotBlank()) {
                FormatStat(label = "Type", value = reg.displayName)
            }
            if (reg.clock > 0) {
                FormatStat(label = "Clock", value = "${reg.clock}s")
            }
        }
    }
}

@Composable
private fun FormatStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        SectionLabel(text = label)
        SectionValue(text = value)
    }
}

// ----- Broadcasts ----------------------------------------------------------

/**
 * Renders broadcasters as chip-style pills. Multi-network games (national +
 * regional) just get more chips.
 */
@Composable
fun BroadcastsCard(
    broadcasts: List<GameDetailsBroadcasts>,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    val networks = broadcasts.mapNotNull { it.station?.takeIf { s -> s.isNotBlank() } }
        .ifEmpty { broadcasts.mapNotNull { it.media?.shortName?.takeIf { s -> s.isNotBlank() } } }
    if (networks.isEmpty()) return

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "📺",
            title = "Broadcasts",
            accentColors = teamColors,
        )
        LazyRow(
            modifier = Modifier.padding(top = 4.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(networks) { network ->
                BroadcastChip(text = network)
            }
        }
    }
}

@Composable
private fun BroadcastChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 12.dp, vertical = 6.dp),
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

// ----- Predictor (model-implied prediction) --------------------------------

/**
 * ESPN's pre-game predictor. Different from [WinProbabilityBar] which is
 * live-state; this one is the static "model said X% before the game started"
 * number. Visualized as two stacked team rows with progress bars.
 */
@Composable
fun PredictorCard(
    predictor: PredictorModel,
    awayTeamName: String,
    homeTeamName: String,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    val awayProj = predictor.awayTeam?.gameProjection ?: 0f
    val homeProj = predictor.homeTeam?.gameProjection ?: 0f
    if (awayProj == 0f && homeProj == 0f) return

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🔮",
            title = "Pre-Game Prediction",
            subtitle = predictor.header?.takeIf { it.isNotBlank() } ?: "ESPN model implied chance to win",
            accentColors = teamColors,
        )
        Column(
            modifier = Modifier.padding(top = 4.dp, bottom = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            PredictorRow(
                teamName = awayTeamName,
                projection = awayProj,
                color = teamColors?.awayPrimary ?: MaterialTheme.colorScheme.primary,
            )
            PredictorRow(
                teamName = homeTeamName,
                projection = homeProj,
                color = teamColors?.homePrimary ?: MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Composable
private fun PredictorRow(teamName: String, projection: Float, color: Color) {
    val pct = (projection / 100f).coerceIn(0f, 1f)
    val animated by animateFloatAsState(
        targetValue = pct,
        animationSpec = tween(durationMillis = 800),
        label = "predictor-row",
    )
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = teamName.uppercase().ifBlank { "—" },
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.5.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "${projection.toInt()}%",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = color,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animated.coerceAtLeast(0.001f))
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(color),
            )
        }
    }
}

// ----- Odds ----------------------------------------------------------------

/**
 * Sportsbook odds list. Different from [PickCenterList] (pickcenter is the
 * "consensus + expert picks" combined view); this is the raw provider odds.
 */
@Composable
fun OddsCard(
    odds: List<OddsModel>,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    if (odds.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "💰",
            title = "Sportsbook Odds",
            subtitle = "Raw lines from individual books",
            accentColors = teamColors,
        )
        Column(modifier = Modifier.padding(bottom = 12.dp)) {
            odds.forEachIndexed { index, line ->
                if (index > 0) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = line.provider.name,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f),
                    )
                    SectionLabel(text = "Priority ${line.provider.priority}")
                }
            }
        }
    }
}

// ----- Against the Spread --------------------------------------------------

/**
 * Each team's record vs the spread. Displayed as two centered columns with a
 * team logo + abbreviation header and the records list below.
 */
@Composable
fun AgainstTheSpreadCard(
    ats: List<AgainstTheSpreadModel>,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    if (ats.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "📐",
            title = "Against The Spread",
            subtitle = "Team records vs the spread",
            accentColors = teamColors,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ats.forEach { teamAts ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    GenericImageLoader(
                        obj = teamAts.team?.logos?.firstOrNull()?.href.orEmpty(),
                        modifier = Modifier.size(32.dp),
                    )
                    Text(
                        text = teamAts.team?.abbreviation.orEmpty(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    teamAts.records.forEach { record ->
                        Text(
                            text = record,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

// ----- News Rail -----------------------------------------------------------

/**
 * Horizontal-scrolling rail of news articles. Each card has a leading image,
 * headline, and metadata. ESPN often returns 4-8 items here for big games.
 */
@Composable
fun NewsRailCard(
    news: NewsModel,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    if (news.articles.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🗞",
            title = news.header.ifBlank { "Latest News" },
            accentColors = teamColors,
        )
        LazyRow(
            modifier = Modifier.padding(top = 6.dp, bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(news.articles) { article ->
                NewsArticleCard(article = article)
            }
        }
    }
}

@Composable
private fun NewsArticleCard(article: ArticleDomainModel) {
    Column(
        modifier = Modifier
            .width(220.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val firstImage = article.images.firstOrNull()?.url.orEmpty()
        if (firstImage.isNotBlank()) {
            GenericImageLoader(
                obj = firstImage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(6.dp)),
            )
        }
        Text(
            text = article.headline,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 17.sp,
        )
        if (!article.byline.isNullOrBlank()) {
            SectionLabel(text = article.byline.orEmpty())
        }
    }
}

// ----- Scoring Plays -------------------------------------------------------

/**
 * Vertical timeline of run/score events. Each play has a team logo on its
 * side and the running score on the other — feels like the broadcast
 * "scoring update" graphic.
 */
@Composable
fun ScoringPlaysCard(
    scoringPlays: List<ScoringPlayModel>,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    if (scoringPlays.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "💥",
            title = "Scoring Plays",
            subtitle = "Every run, in order",
            accentColors = teamColors,
        )
        Column(modifier = Modifier.padding(bottom = 12.dp)) {
            scoringPlays.forEachIndexed { index, play ->
                if (index > 0) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    GenericImageLoader(
                        obj = play.team?.logos?.firstOrNull()?.href.orEmpty(),
                        modifier = Modifier.size(28.dp),
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        SectionLabel(
                            text = "Period ${play.period}${if (play.clock.isNotBlank()) " · ${play.clock}" else ""}",
                        )
                        Text(
                            text = play.text,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 17.sp,
                        )
                    }
                    Text(
                        text = "${play.awayScore}-${play.homeScore}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

// ----- Season Series (H2H) -------------------------------------------------

/**
 * Head-to-head series this season. Each completed game gets a compact row
 * with both team scores and a winner indicator.
 */
@Composable
fun SeasonSeriesCard(
    seasonSeries: List<SeasonSeriesModel>,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
) {
    val series = seasonSeries.firstOrNull() ?: return
    if (series.events.isEmpty() && series.summary.isBlank()) return

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🤝",
            title = "Season Series",
            subtitle = series.summary.takeIf { it.isNotBlank() }
                ?: series.title.takeIf { it.isNotBlank() },
            accentColors = teamColors,
        )
        Column(modifier = Modifier.padding(bottom = 12.dp)) {
            if (series.seriesScore.isNotBlank()) {
                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SectionLabel(text = "Series")
                    SectionValue(text = series.seriesScore)
                }
            }
            series.events.forEachIndexed { index, event ->
                if (index > 0) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SectionLabel(text = event.date.take(10))  // first 10 chars of ISO-8601 = yyyy-MM-dd
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        event.competitors.forEach { comp ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                            ) {
                                Text(
                                    text = comp.teamAbbreviation,
                                    fontSize = 11.sp,
                                    fontWeight = if (comp.winner) FontWeight.Black else FontWeight.Medium,
                                    color = if (comp.winner) MaterialTheme.colorScheme.onSurface
                                    else MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                                Text(
                                    text = comp.score,
                                    fontSize = 13.sp,
                                    fontWeight = if (comp.winner) FontWeight.Black else FontWeight.Bold,
                                    color = if (comp.winner) MaterialTheme.colorScheme.onSurface
                                    else MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ----- Baseball Plays (play-by-play) ---------------------------------------

/**
 * Recent play-by-play (most-recent first). Each play row shows inning, what
 * happened, the count after the pitch, and the running score.
 */
@Composable
fun BaseballPlaysCard(
    plays: List<BaseballPlayModel>,
    modifier: Modifier = Modifier,
    teamColors: GameTeamColors? = null,
    maxShown: Int = 12,
) {
    if (plays.isEmpty()) return
    val recent = plays.takeLast(maxShown).reversed()

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "⚾",
            title = "Play by Play",
            subtitle = "Most recent ${recent.size} plays",
            accentColors = teamColors,
        )
        Column(modifier = Modifier.padding(bottom = 12.dp)) {
            recent.forEachIndexed { index, play ->
                if (index > 0) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Column(
                        modifier = Modifier.width(64.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        SectionLabel(text = play.period.displayValue.ifBlank { "${play.period.type} ${play.period.number}" })
                        play.resultCount?.let {
                            Text(
                                text = "${it.balls}-${it.strikes}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                    Text(
                        text = play.text,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 16.sp,
                    )
                    if (play.scoringPlay) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                        ) {
                            Text(
                                text = "+${play.scoreValue}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                    }
                }
            }
        }
    }
}

// ----- Meta footer (last updated) ------------------------------------------

/**
 * Small chip displaying when ESPN last updated this game's data. Uses
 * [GameMetaModel.lastUpdatedAt] (ISO-8601). Refreshes its relative-time text
 * every 15 seconds while in composition.
 */
@Composable
fun MetaFooter(
    meta: GameMetaModel?,
    modifier: Modifier = Modifier,
) {
    if (meta == null || meta.lastUpdatedAt.isBlank()) return
    val relative by produceState(initialValue = relativeTime(meta.lastUpdatedAt), meta.lastUpdatedAt) {
        while (true) {
            delay(15_000)
            value = relativeTime(meta.lastUpdatedAt)
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "UPDATED $relative",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.5.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

private fun relativeTime(isoTimestamp: String): String {
    return runCatching {
        val now = Instant.now()
        val then = Instant.parse(isoTimestamp)
        val secs = Duration.between(then, now).seconds
        when {
            secs < 60 -> "JUST NOW"
            secs < 3600 -> "${secs / 60}m AGO"
            secs < 86_400 -> "${secs / 3600}h AGO"
            else -> "${secs / 86_400}d AGO"
        }
    }.getOrDefault("RECENTLY")
}