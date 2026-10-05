package com.sgut.android.nationalfootballleague.ui.screens.gamedetailscreen

import com.sgut.android.nationalfootballleague.data.remote.network_responses.game_details.End
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
fun NewVidList(
    modifier: Modifier,
    vidList: List<VideoModel>,
    teamColors: GameTeamColors? = null,
) {
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🎥",
            title = "Videos",
            subtitle = if (vidList.isEmpty()) "No clips yet" else "Highlights and recaps",
            accentColors = teamColors,
        )
        LazyRow(
            contentPadding = PaddingValues(vertical = 4.dp),
            modifier = Modifier.padding(bottom = 12.dp),
        ) {
            items(items = vidList) { vid ->
                VideoPreview(video = vid, modifier = Modifier.padding(end = 12.dp))
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
fun PickCenterList(
    modifier: Modifier,
    list: List<PickcenterModel>,
    teamColors: GameTeamColors? = null,
) {
    DefaultCard(modifier = modifier) {
        CardSectionHeader(
            emoji = "🎲",
            title = "Picks",
            subtitle = "Consensus and expert spreads",
            accentColors = teamColors,
        )
        Column(modifier = Modifier.padding(bottom = 10.dp)) {
            list.forEachIndexed { index, pickCenter ->
                if (index > 0) {
                    androidx.compose.material3.HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                        thickness = 0.5.dp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = pickCenter.provider.name,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = pickCenter.details,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 2.dp),
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            SectionLabel(text = "O/U")
                            SectionValue(text = pickCenter.overUnder.toString())
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            SectionLabel(text = "Spread")
                            SectionValue(text = pickCenter.spread.toString())
                        }
                    }
                }
            }
        }
    }
}
