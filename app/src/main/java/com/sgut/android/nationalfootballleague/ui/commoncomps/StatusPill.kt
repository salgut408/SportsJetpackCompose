package com.sgut.android.nationalfootballleague.ui.commoncomps

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Display intent for [StatusPill]. The pill's color, animation, and chrome are
 * derived from the kind; the caller chooses which kind matches the underlying
 * domain status. Keeping this an enum (instead of mirroring raw ESPN strings)
 * keeps the pill's visual rules in one place.
 */
enum class PillKind {
    LIVE,        // red, pulsing dot — game actively in progress
    DELAYED,     // yellow, no animation — paused (rain, lightning, fog, suspended)
    SCHEDULED,   // primary, no animation — game not yet started
    FINAL,       // muted, no animation — game complete
    POSTPONED,   // yellow, no animation — rescheduled
    CANCELED,    // red (muted), no animation — won't be played
    NEUTRAL,     // muted, no animation — fallback for unknown states
}

/**
 * Small status indicator pill shown on event cards.
 *
 * [kind] drives color and animation; [label] is the text and [emoji] (if any)
 * renders as a small leading glyph. LIVE gets a pulsing dot instead of an emoji.
 */
@Composable
fun StatusPill(
    kind: PillKind,
    label: String,
    modifier: Modifier = Modifier,
    emoji: String? = null,
) {
    val color = pillColor(kind)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color.copy(alpha = 0.15f))
            .border(
                width = 1.dp,
                color = color.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp),
            )
            .padding(horizontal = 8.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        when (kind) {
            PillKind.LIVE -> PulsingDot(color = color)
            else -> if (!emoji.isNullOrBlank()) {
                Text(text = emoji, fontSize = 11.sp)
            }
        }
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = color,
        )
    }
}

@Composable
private fun pillColor(kind: PillKind): Color = when (kind) {
    PillKind.LIVE -> MaterialTheme.colorScheme.error
    PillKind.DELAYED -> MaterialTheme.colorScheme.tertiary
    PillKind.SCHEDULED -> MaterialTheme.colorScheme.primary
    PillKind.FINAL -> MaterialTheme.colorScheme.onSurfaceVariant
    PillKind.POSTPONED -> MaterialTheme.colorScheme.tertiary
    PillKind.CANCELED -> MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
    PillKind.NEUTRAL -> MaterialTheme.colorScheme.onSurfaceVariant
}

@Composable
private fun PulsingDot(color: Color, size: Dp = 7.dp) {
    val transition = rememberInfiniteTransition(label = "live")
    val alpha by transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "live-alpha",
    )
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color.copy(alpha = alpha)),
    )
}