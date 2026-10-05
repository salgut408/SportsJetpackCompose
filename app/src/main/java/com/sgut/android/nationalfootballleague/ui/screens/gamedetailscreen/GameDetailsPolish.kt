package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgut.android.nationalfootballleague.data.remote.network_responses.scoreboard_network_responses.StatusState
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsCompetitorModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.HeaderModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.WinprobabilityModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.BasicImage
import com.sgut.android.nationalfootballleague.ui.screens.teamdetails.HexToJetpackColor2

/**
 * UI polish helpers for the Game Details screen: team-color hero header, live
 * pulse indicator, animated score, win probability bar, and base-advance pulse.
 *
 * Everything here is presentation-only — no VM, no side effects beyond local
 * animations. Components are designed to no-op gracefully when team colors are
 * unparseable or when the data they need is missing.
 */

// ----- Team color extraction ----------------------------------------------

/**
 * Container for the two teams' primary/alternate colors, with a safe default
 * when ESPN doesn't return parseable hex.
 */
data class GameTeamColors(
    val awayPrimary: Color,
    val awayAlternate: Color,
    val homePrimary: Color,
    val homeAlternate: Color,
) {
    companion object {
        val Fallback = GameTeamColors(
            awayPrimary = Color(0xFF1976D2),
            awayAlternate = Color(0xFF0D47A1),
            homePrimary = Color(0xFFD32F2F),
            homeAlternate = Color(0xFFB71C1C),
        )
    }
}

/**
 * Pull team colors from a [GameDetailsModel]. Falls back to the default palette
 * if competitors are missing or hex strings can't be parsed.
 *
 * Note ordering: the first competitor in ESPN's header is the home team; the
 * last is the away team. Header2 elsewhere in the file uses `.first()` for
 * the left slot and `.last()` for the right slot inside a `RightToLeftLayout`
 * so the away team ends up visually on the left.
 */
fun gameTeamColors(game: GameDetailsModel): GameTeamColors {
    val competitors = game.header?.competitions?.firstOrNull()?.competitors
        ?: return GameTeamColors.Fallback

    val home = competitors.firstOrNull()
    val away = competitors.lastOrNull()

    fun parse(hex: String, fallback: Color): Color =
        runCatching { HexToJetpackColor2.getColor(hex) }.getOrDefault(fallback)

    return GameTeamColors(
        awayPrimary = parse(away?.team?.color.orEmpty(), GameTeamColors.Fallback.awayPrimary),
        awayAlternate = parse(away?.team?.alternateColor.orEmpty(), GameTeamColors.Fallback.awayAlternate),
        homePrimary = parse(home?.team?.color.orEmpty(), GameTeamColors.Fallback.homePrimary),
        homeAlternate = parse(home?.team?.alternateColor.orEmpty(), GameTeamColors.Fallback.homeAlternate),
    )
}

/**
 * Pick a readable foreground (white vs near-black) for text drawn on top of
 * a team-colored background. Uses the WCAG-style luminance threshold.
 */
private fun onColorFor(background: Color): Color =
    if (background.luminance() > 0.5f) Color(0xFF111111) else Color.White

// ----- Animated score ------------------------------------------------------

/**
 * Score number that smoothly counts up to its new value when [targetScore]
 * changes — gives live-score updates a satisfying broadcast feel rather than
 * snapping.
 */
@Composable
fun AnimatedScore(
    targetScore: Int,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val animatedScore by animateIntAsState(
        targetValue = targetScore,
        animationSpec = tween(durationMillis = 600),
        label = "score-count-up",
    )
    Text(
        text = animatedScore.toString(),
        style = MaterialTheme.typography.displaySmall,
        fontWeight = FontWeight.Black,
        color = color,
        modifier = modifier,
    )
}

// ----- Live pulse dot ------------------------------------------------------

/**
 * Small pulsing dot used next to "LIVE" / inning status text. The infinite
 * transition runs only while the composable is in composition, so it stops
 * naturally when the screen leaves.
 */
@Composable
fun LivePulseDot(
    color: Color = Color(0xFFE53935),
    modifier: Modifier = Modifier,
) {
    val transition = rememberInfiniteTransition(label = "live-pulse")
    val alpha by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "live-pulse-alpha",
    )
    Box(
        modifier = modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(color.copy(alpha = alpha)),
    )
}

// ----- Win probability bar -------------------------------------------------

/**
 * Horizontal bar split between the two teams' colors, sized to reflect the
 * current win probability split. Animates whenever [winProbability] changes
 * so live shifts feel motion-driven instead of jumpy.
 *
 * ESPN's [WinprobabilityModel] exposes `homeWinPercentage` (0..1) and
 * `tiePercentage`. Away % is whatever's left. We clamp + lerp to keep the bar
 * stable even if ESPN returns garbage values for partial states.
 */
@Composable
fun WinProbabilityBar(
    winProbability: WinprobabilityModel?,
    colors: GameTeamColors,
    modifier: Modifier = Modifier,
) {
    if (winProbability == null || winProbability.homeWinPercentage <= 0.0) return

    val homePct = winProbability.homeWinPercentage.coerceIn(0.0, 1.0).toFloat()
    val animatedHomePct by animateFloatAsState(
        targetValue = homePct,
        animationSpec = tween(durationMillis = 800),
        label = "win-probability",
    )

    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "AWAY ${formatPct(1f - animatedHomePct)}",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.awayPrimary,
            )
            Text(
                text = "WIN PROBABILITY",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.sp,
            )
            Text(
                text = "${formatPct(animatedHomePct)} HOME",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.homePrimary,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
        ) {
            Box(
                modifier = Modifier
                    .weight((1f - animatedHomePct).coerceAtLeast(0.001f))
                    .fillMaxWidth()
                    .background(colors.awayPrimary),
            )
            Box(
                modifier = Modifier
                    .weight(animatedHomePct.coerceAtLeast(0.001f))
                    .fillMaxWidth()
                    .background(colors.homePrimary),
            )
        }
    }
}

private fun formatPct(value: Float): String =
    "${(value * 100f).toInt().coerceIn(0, 100)}%"

// ----- Hero header ---------------------------------------------------------

/**
 * Replaces the flat-white Header2 in the GameDetails top bar. Renders the
 * matchup against a diagonal team-color gradient with a darkening scrim, the
 * animated score, and a [LivePulseDot] when the game is in progress.
 *
 * `middle` is the same slot the old Header2 used for status text/time — kept
 * compatible so the call site only needs a wrapper swap.
 */
@Composable
fun GameDetailsHeroHeader(
    header: HeaderModel,
    colors: GameTeamColors,
    isLive: Boolean,
    middle: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    val competition = header.competitions.firstOrNull() ?: return
    val home = competition.competitors.firstOrNull()
    val away = competition.competitors.lastOrNull()
    if (home == null || away == null) return

    val gradient = Brush.linearGradient(
        colors = listOf(
            colors.awayPrimary.copy(alpha = 0.92f),
            colors.awayAlternate.copy(alpha = 0.85f),
            colors.homeAlternate.copy(alpha = 0.85f),
            colors.homePrimary.copy(alpha = 0.92f),
        ),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
    )
    // Scrim keeps text legible even when a team's primary is a near-white.
    val scrim = Brush.verticalGradient(
        colors = listOf(Color.Black.copy(alpha = 0.10f), Color.Black.copy(alpha = 0.35f)),
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(gradient),
    ) {
        Box(modifier = Modifier.fillMaxWidth().background(scrim)) {
            Column(modifier = Modifier.padding(vertical = 12.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    HeroCompetitor(competitor = away, scoreColor = Color.White)
                    HeroMiddleSlot(isLive = isLive, content = middle)
                    HeroCompetitor(competitor = home, scoreColor = Color.White)
                }
            }
        }
    }
}

@Composable
private fun HeroCompetitor(
    competitor: GameDetailsCompetitorModel,
    scoreColor: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BasicImage(
                imgUrl = competitor.team?.logos?.firstOrNull()?.href.orEmpty(),
                contentDescription = competitor.team?.name,
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                borderWidth = 0.dp,
                borderColor = Color.Transparent,
                modifier = Modifier.size(44.dp),
            )
            Text(
                text = competitor.team?.abbreviation.orEmpty(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Text(
                text = competitor.record.firstOrNull()?.summary.orEmpty(),
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.75f),
            )
        }
        AnimatedScore(targetScore = competitor.score, color = scoreColor)
    }
}

@Composable
private fun HeroMiddleSlot(
    isLive: Boolean,
    content: @Composable () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (isLive) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                LivePulseDot()
                Text(
                    text = "LIVE",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    letterSpacing = 2.sp,
                )
            }
        }
        // Wrap nested middle content so existing Text() calls inherit white-on-color styling.
        androidx.compose.runtime.CompositionLocalProvider(
            androidx.compose.material3.LocalContentColor provides Color.White,
        ) {
            Box(
                modifier = Modifier.padding(top = 2.dp),
                contentAlignment = Alignment.Center,
            ) { content() }
        }
    }
}

// ----- Section system (Swiss typography) -----------------------------------

/**
 * Vertical rhythm constants. Game Details is a long, varied scroll, so a
 * predictable baseline grid is the cheapest way to make it feel calmer.
 *
 *   - [SectionGap]        — empty space between two consecutive sections.
 *   - [HeaderToBody]      — small breathing room between a section's header
 *                            rule and its first content card.
 *   - [SectionGutter]     — horizontal page margin, applied to headers; the
 *                            content cards manage their own padding.
 */
object GameDetailsSpacing {
    val SectionGap = 28.dp
    val HeaderToBody = 12.dp
    val SectionGutter = 16.dp
}

/**
 * Swiss-style section header: small emoji, tracked-uppercase title, optional
 * sub-line in onSurfaceVariant, and a hairline rule underneath. A vertical
 * team-color accent bar sits to the left of the title block; pass null to
 * omit it for sections that don't benefit from the color tie-in.
 *
 * Typography choices:
 *   - Title in `labelMedium`-sized text, FontWeight.Black, 2.sp letter-spacing
 *     — reads as a tag/label, not a heading. Keeps the hierarchy quiet but firm.
 *   - Subtitle in `labelSmall`, regular weight, onSurfaceVariant for contrast
 *     restraint.
 *   - Hairline divider at 0.5dp avoids the "section box" look — implied
 *     boundary, not enclosed.
 */
@Composable
fun SectionHeader(
    emoji: String,
    title: String,
    subtitle: String? = null,
    accentColors: GameTeamColors? = null,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = GameDetailsSpacing.SectionGutter),
            verticalAlignment = Alignment.Top,
        ) {
            if (accentColors != null) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(if (subtitle != null) 36.dp else 22.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(accentColors.awayPrimary, accentColors.homePrimary),
                            ),
                        ),
                )
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(10.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(text = emoji, fontSize = 14.sp)
                    Text(
                        text = title.uppercase(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 2.dp),
                    )
                }
            }
        }
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(8.dp))
        androidx.compose.material3.HorizontalDivider(
            modifier = Modifier.padding(horizontal = GameDetailsSpacing.SectionGutter),
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 0.5.dp,
        )
    }
}

/**
 * Vertical rhythm wrapper between sections. The actual emoji/title header
 * now lives *inside* each section's card (see [CardSectionHeader]) so the
 * header reads as part of the card's identity rather than as an orphan label
 * above it. This wrapper only provides the gap.
 */
@Composable
fun SectionSpacer() {
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier.height(GameDetailsSpacing.SectionGap),
    )
}

/**
 * In-card section header. Designed to live as the **first child** of a
 * DefaultCard (or any container whose horizontal padding is already managed).
 *
 * Differences vs the now-deprecated outer `SectionHeader`:
 *   - No outer 16dp gutter — inherits the card's padding.
 *   - Top padding (12dp) for breathing room from the card's edge.
 *   - Divider sits flush across the full card width to read as a structural
 *     baseline grid, not a decorative line.
 *
 * Typography stays Swiss: tracked uppercase title at 12sp/Black, optional
 * sub-line at 11sp/onSurfaceVariant, hairline 0.5dp divider.
 */
@Composable
fun CardSectionHeader(
    emoji: String,
    title: String,
    subtitle: String? = null,
    accentColors: GameTeamColors? = null,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().padding(top = 14.dp, bottom = 10.dp)) {
        Row(verticalAlignment = Alignment.Top) {
            if (accentColors != null) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(if (subtitle != null) 34.dp else 20.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(accentColors.awayPrimary, accentColors.homePrimary),
                            ),
                        ),
                )
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(10.dp))
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(text = emoji, fontSize = 14.sp)
                    Text(
                        text = title.uppercase(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 2.dp),
                    )
                }
            }
        }
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(10.dp))
        androidx.compose.material3.HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 0.5.dp,
        )
    }
}

/**
 * Common Swiss-style label text: small, tracked, uppercase, muted color.
 * Use for stat row labels ("AB", "OPS", "ERA"), column headers, and any
 * non-numeric chrome that should sit below the primary text in the hierarchy.
 */
@Composable
fun SectionLabel(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.5.sp,
        color = color,
    )
}

/**
 * Primary readout value. Bold, no letter spacing, full onSurface contrast.
 * Pair with [SectionLabel] for the standard "LABEL / value" pattern.
 */
@Composable
fun SectionValue(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface,
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = color,
    )
}

// ----- Team accent strip ---------------------------------------------------

/**
 * Thin horizontal gradient bar used as a top accent on section cards. Visual
 * "this game's colors" marker for sections that don't otherwise express
 * identity (boxscore, leaders, etc.).
 */
@Composable
fun TeamAccentStrip(
    colors: GameTeamColors,
    modifier: Modifier = Modifier,
) {
    val brush = Brush.horizontalGradient(
        colors = listOf(colors.awayPrimary, colors.homePrimary),
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp)
            .clip(RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
            .background(brush),
    )
}