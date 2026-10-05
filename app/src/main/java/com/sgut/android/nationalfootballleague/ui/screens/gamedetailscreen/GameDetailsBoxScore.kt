package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.End
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.Start
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
 * Renders [BoxScoreModel.statistics] (the top-level boxscore-wide stats list).
 *
 * ESPN returns one of two shapes here:
 *   - **Flat** (NBA, NFL): each entry has a meaningful `displayValue` and the
 *     nested `stats` array is empty.
 *   - **Grouped** (MLB and similar): each entry is a *category*
 *     ("batting" / "pitching" / "fielding") with `displayValue` empty and the
 *     actual stats inside `stats` (abbreviation + displayValue per entry —
 *     OPS .815, ERA 3.42, WHIP 1.21, etc.). These were captured in the Tier 3
 *     model audit but never surfaced to the UI until now.
 */
@Composable
fun BoxScoreTeamStats(
    boxscore: BoxScoreModel,
    modifier: Modifier,
    teamColors: GameTeamColors? = null,
) {
    if (boxscore.statistics.isEmpty()) return
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "📋",
            title = "Box Score",
            subtitle = "Side-by-side breakdown",
            accentColors = teamColors,
        )
        Column(modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)) {
            boxscore.statistics.forEachIndexed { index, group ->
                if (index > 0) {
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                    )
                }
                if (group.stats.isNotEmpty()) {
                    // Grouped shape — render category header + nested stats.
                    Text(
                        text = group.displayName.ifBlank { group.name }.uppercase(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.5.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(top = 10.dp, bottom = 6.dp),
                    )
                    group.stats.forEach { stat ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            SectionLabel(text = stat.abbreviation.ifBlank { stat.name })
                            SectionValue(text = stat.displayValue)
                        }
                    }
                } else {
                    // Flat shape — original simple row.
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        SectionLabel(text = group.name)
                        SectionValue(text = group.displayValue)
                    }
                }
            }
        }
    }
}

@Composable
fun TeamStatCard3(
    modifier: Modifier,
    boxscore: BoxScoreModel,
    teamColors: GameTeamColors? = null,
) {
    if (boxscore.teams.isEmpty()) return
    val team0 = boxscore.teams.getOrNull(0)
    val team1 = boxscore.teams.getOrNull(1)
    val stats0 = team0?.statistics ?: emptyList()
    val stats1 = team1?.statistics ?: emptyList()

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🧮",
            title = "Advanced Stats",
            subtitle = "Side-by-side numerical breakdown",
            accentColors = teamColors,
        )

        // Team header row — sets up a 1 / 1.5 / 1 column grid that the body
        // rows below mirror for clean vertical alignment of stat values.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GenericImageLoader(
                    obj = team0?.team?.logos?.firstOrNull()?.href.orEmpty(),
                    modifier = Modifier.size(28.dp),
                )
                Text(
                    text = team0?.team?.abbreviation.orEmpty(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            SectionLabel(
                text = "Stat",
                modifier = Modifier.weight(1.5f),
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = team1?.team?.abbreviation.orEmpty(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                )
                GenericImageLoader(
                    obj = team1?.team?.logos?.firstOrNull()?.href.orEmpty(),
                    modifier = Modifier.size(28.dp),
                )
            }
        }
        androidx.compose.material3.HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 0.5.dp,
        )

        // Stat comparison rows. ESPN returns one of two shapes per group:
        //   • Grouped (MLB): group has stats[] with abbreviation+displayValue
        //     (OPS, ERA, OBP, SLG, WHIP, FIP, etc.). Render group header then
        //     compare each nested stat row by row. Match by abbreviation so
        //     ordering differences between teams don't misalign.
        //   • Flat (NBA/NFL): outer displayValue populated, stats[] empty.
        //     Render the original single-row comparison.
        //
        // Both shapes are tabular: away on the left (right-justified into the
        // center), abbreviation centered, home on the right (left-justified).
        stats0.forEachIndexed { groupIdx, group0 ->
            val group1 = stats1.getOrNull(groupIdx)

            if (group0.stats.isNotEmpty()) {
                // ----- Grouped shape -----
                Text(
                    text = group0.displayName.ifBlank { group0.name }.uppercase(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.5.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 12.dp, bottom = 6.dp),
                )
                group0.stats.forEach { stat0 ->
                    val stat1 = group1?.stats?.find { it.abbreviation == stat0.abbreviation }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stat0.displayValue,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f),
                        )
                        SectionLabel(
                            text = stat0.abbreviation.ifBlank { stat0.name },
                            modifier = Modifier.weight(1.5f),
                        )
                        Text(
                            text = stat1?.displayValue.orEmpty(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f),
                        )
                    }
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f),
                        thickness = 0.5.dp,
                    )
                }
            } else {
                // ----- Flat shape -----
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 9.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = group0.displayValue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1f),
                    )
                    SectionLabel(
                        text = group0.name,
                        modifier = Modifier.weight(1.5f),
                    )
                    Text(
                        text = group1?.displayValue.orEmpty(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f),
                    )
                }
                androidx.compose.material3.HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                    thickness = 0.5.dp,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
