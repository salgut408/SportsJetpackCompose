package com.sgut.android.nationalfootballleague.ui.screens.scoreboardscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.SituationScoreboard
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardCompetitorsModel

/**
 * Full live-baseball game situation: linescore grid, bases diamond, outs,
 * count, current pitcher + batter, last play text, and due-up batters.
 *
 * Caller is responsible for only showing this during actually-live baseball
 * (not during delays — see PillKind.LIVE check in NewEventMatchup).
 */
@Composable
fun BaseballLiveSituation(
    situation: SituationScoreboard?,
    homeCompetitor: ScoreboardCompetitorsModel? = null,
    awayCompetitor: ScoreboardCompetitorsModel? = null,
    modifier: Modifier = Modifier,
) {
    if (situation == null) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        // Inning-by-inning linescore grid (R/H/E summary at the right edge)
        if (homeCompetitor != null && awayCompetitor != null) {
            LinescoreGrid(
                home = homeCompetitor,
                away = awayCompetitor,
            )
        }

        // Row: bases diamond + outs dots + count
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            BasesDiamond(
                onFirst = situation.onFirst,
                onSecond = situation.onSecond == true,
                onThird = situation.onThird == true,
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                OutsDots(outs = situation.outs ?: 0)
                Text(
                    text = "B ${situation.balls ?: 0}-${situation.strikes ?: 0} S",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        // Pitcher row
        val pitcherName = situation.pitcher?.athlete?.shortName.orEmpty()
        if (pitcherName.isNotBlank()) {
            AtBatRow(
                roleLabel = "P",
                name = pitcherName,
                summary = situation.pitcher?.summary.orEmpty(),
            )
        }

        // Batter row
        val batterName = situation.batter?.athlete?.shortName.orEmpty()
        if (batterName.isNotBlank()) {
            AtBatRow(
                roleLabel = "AB",
                name = batterName,
                summary = situation.batter?.summary.orEmpty(),
            )
        }

        // Last play
        val lastPlayText = situation.lastPlay?.text.orEmpty()
        if (lastPlayText.isNotBlank()) {
            Text(
                text = "\"$lastPlayText\"",
                fontSize = 11.sp,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        // Due up — only show names that have athlete info (skip raw playerIds)
        val dueUpNames = situation.dueUp
            .mapNotNull { it.athlete?.shortName?.takeIf { name -> name.isNotBlank() } }
        if (dueUpNames.isNotEmpty()) {
            Text(
                text = "Due up: ${dueUpNames.joinToString(", ")}",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun AtBatRow(
    roleLabel: String,
    name: String,
    summary: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = roleLabel,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    RoundedCornerShape(4.dp),
                )
                .padding(horizontal = 6.dp, vertical = 1.dp),
        )
        Text(
            text = name,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        if (summary.isNotBlank()) {
            Text(
                text = "($summary)",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

/**
 * Inning-by-inning linescore grid with R/H/E totals on the right.
 *
 *      1  2  3  4  5  6  7  8  9   R  H  E
 * AWAY 0  0  0  0  0  1  -  -  -   1  4  0
 * HOME 0  0  0  1  0  0  -  -  -   1  4  0
 *
 * Renders at least 9 periods (standard baseball), more if extra innings.
 * Missing periods (not yet played) display "-".
 */
@Composable
fun LinescoreGrid(
    home: ScoreboardCompetitorsModel,
    away: ScoreboardCompetitorsModel,
    modifier: Modifier = Modifier,
) {
    val homeLines = home.linescores.orEmpty()
    val awayLines = away.linescores.orEmpty()
    if (homeLines.isEmpty() && awayLines.isEmpty()) return

    // We render at least 9 innings, but expand for extra innings.
    // Use the highest period number we've seen so far.
    val maxPeriod = maxOf(
        homeLines.maxOfOrNull { it.period ?: 0 } ?: 0,
        awayLines.maxOfOrNull { it.period ?: 0 } ?: 0,
        9,
    )

    val cellWidth = 22.dp
    val totalCellWidth = 26.dp
    val labelWidth = 36.dp
    val scrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxWidth()) {
        // Header row
        LinescoreRow(
            label = "",
            cells = (1..maxPeriod).map { it.toString() },
            rTotal = "R",
            hTotal = "H",
            eTotal = "E",
            isHeader = true,
            cellWidth = cellWidth,
            totalCellWidth = totalCellWidth,
            labelWidth = labelWidth,
            scrollState = scrollState,
        )
        // Away row
        LinescoreRow(
            label = away.team.abbreviation,
            cells = (1..maxPeriod).map { period ->
                awayLines.firstOrNull { it.period == period }?.displayValue ?: "-"
            },
            rTotal = away.score,
            hTotal = statByAbbrev(away, "H") ?: "-",
            eTotal = statByAbbrev(away, "E") ?: "-",
            isHeader = false,
            cellWidth = cellWidth,
            totalCellWidth = totalCellWidth,
            labelWidth = labelWidth,
            scrollState = scrollState,
        )
        // Home row
        LinescoreRow(
            label = home.team.abbreviation,
            cells = (1..maxPeriod).map { period ->
                homeLines.firstOrNull { it.period == period }?.displayValue ?: "-"
            },
            rTotal = home.score,
            hTotal = statByAbbrev(home, "H") ?: "-",
            eTotal = statByAbbrev(home, "E") ?: "-",
            isHeader = false,
            cellWidth = cellWidth,
            totalCellWidth = totalCellWidth,
            labelWidth = labelWidth,
            scrollState = scrollState,
        )
    }
}

@Composable
private fun LinescoreRow(
    label: String,
    cells: List<String>,
    rTotal: String,
    hTotal: String,
    eTotal: String,
    isHeader: Boolean,
    cellWidth: Dp,
    totalCellWidth: Dp,
    labelWidth: Dp,
    scrollState: ScrollState,
) {
    val textColor = if (isHeader) MaterialTheme.colorScheme.onSurfaceVariant
        else MaterialTheme.colorScheme.onSurface
    val weight = if (isHeader) FontWeight.Bold else FontWeight.Normal
    val fontSize = 11.sp

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            modifier = Modifier.width(labelWidth),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = textColor,
        )
        // Innings — horizontally scrollable when extra innings push the row wide.
        Row(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(scrollState),
        ) {
            cells.forEach { cell ->
                Text(
                    text = cell,
                    modifier = Modifier.width(cellWidth),
                    fontSize = fontSize,
                    fontWeight = weight,
                    color = textColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
        // Vertical separator before totals
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(14.dp)
                .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
        )
        Spacer(Modifier.width(4.dp))
        // Totals
        listOf(rTotal, hTotal, eTotal).forEach { total ->
            Text(
                text = total,
                modifier = Modifier.width(totalCellWidth),
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}

private fun statByAbbrev(competitor: ScoreboardCompetitorsModel, abbr: String): String? =
    competitor.statistics
        .firstOrNull { it.abbreviation == abbr }
        ?.displayValue
        ?.takeIf { it.isNotBlank() }

/**
 * Classic baseball bases diamond. Renders four rotated squares around a central
 * point: 2nd at top, 3rd left, 1st right, home at bottom (rendered as outline
 * since no runner ever stands on home base in the situation feed).
 */
@Composable
fun BasesDiamond(
    onFirst: Boolean,
    onSecond: Boolean,
    onThird: Boolean,
    modifier: Modifier = Modifier,
    containerSize: Dp = 56.dp,
    baseSize: Dp = 12.dp,
) {
    Box(
        modifier = modifier.size(containerSize),
        contentAlignment = Alignment.Center,
    ) {
        Base(occupied = onSecond, modifier = Modifier.align(Alignment.TopCenter).size(baseSize))
        Base(occupied = onThird, modifier = Modifier.align(Alignment.CenterStart).size(baseSize))
        Base(occupied = onFirst, modifier = Modifier.align(Alignment.CenterEnd).size(baseSize))
    }
}

@Composable
private fun Base(occupied: Boolean, modifier: Modifier = Modifier) {
    val activeColor = MaterialTheme.colorScheme.primary
    val inactiveColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
    val color = if (occupied) activeColor else inactiveColor
    Box(
        modifier = modifier
            .rotate(45f)
            .background(
                color = if (occupied) color else androidx.compose.ui.graphics.Color.Transparent,
                shape = RectangleShape,
            )
            .border(width = 1.dp, color = color, shape = RectangleShape),
    )
}

/**
 * Three small dots: filled per [outs] (0..3), unfilled for the rest.
 */
@Composable
fun OutsDots(outs: Int, modifier: Modifier = Modifier) {
    val active = MaterialTheme.colorScheme.error
    val inactive = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = if (index < outs) active else inactive,
                        shape = CircleShape,
                    ),
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = if (outs == 1) "1 out" else "$outs outs",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
    Spacer(modifier = Modifier.height(0.dp)) // no-op layout hint; lets caller compose vertically
}