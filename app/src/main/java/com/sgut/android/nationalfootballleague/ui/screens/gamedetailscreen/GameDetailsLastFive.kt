package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

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

@Composable
fun TabsLastFiveGames(
    modifier: Modifier,
    lastFiveGames: List<LastFiveGamesModel>,
    teamColors: GameTeamColors? = null,
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabTitles = listOf(
        lastFiveGames.getOrNull(0)?.team,
        lastFiveGames.getOrNull(1)?.team,
    )

    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "📅",
            title = "Last 5 Games",
            subtitle = "Recent form for both teams",
            accentColors = teamColors,
        )
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            tabTitles.forEachIndexed { index, team ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            GenericImageLoader(
                                obj = team?.logo.toString(),
                                modifier = Modifier.size(24.dp),
                            )
                            Text(
                                text = team?.abbreviation.orEmpty(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp,
                            )
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
