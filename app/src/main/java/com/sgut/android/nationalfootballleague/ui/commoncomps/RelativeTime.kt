package com.sgut.android.nationalfootballleague.ui.commoncomps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

/**
 * Returns a human-readable string like "Updated 2m ago" for the given timestamp.
 * Recomposes every 60 seconds so the displayed value stays current while the
 * screen is in the foreground.
 *
 * Returns `null` if [timestampMs] is null (no data ever loaded).
 */
@Composable
fun rememberRelativeTime(timestampMs: Long?): String? {
    if (timestampMs == null) return null

    val now by produceState(initialValue = System.currentTimeMillis(), key1 = timestampMs) {
        while (true) {
            value = System.currentTimeMillis()
            delay(60_000L)
        }
    }

    val deltaMs = (now - timestampMs).coerceAtLeast(0L)
    val deltaSec = deltaMs / 1_000
    val deltaMin = deltaSec / 60
    val deltaHr = deltaMin / 60
    val deltaDay = deltaHr / 24

    return when {
        deltaSec < 45 -> "Updated just now"
        deltaMin < 60 -> "Updated ${deltaMin}m ago"
        deltaHr < 24 -> "Updated ${deltaHr}h ago"
        else -> "Updated ${deltaDay}d ago"
    }
}