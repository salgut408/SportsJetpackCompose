package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.AthletesRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.SportSurface


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalAthleteCard(
    athelete: AthletesRosterModel,
    modifier: Modifier = Modifier,
    team: FullTeamDetailWithRosterModel,
) {
    val teamColor = HexToJetpackColor2.getColor(team.color)
    val isInjured = athelete.injuries?.isNotEmpty() == true
    var showEnlargedImage by remember { mutableStateOf(false) }

    if (showEnlargedImage) {
        AthleteImageDialog(
            athlete = athelete,
            teamColor = teamColor,
            onDismiss = { showEnlargedImage = false }
        )
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier.padding(4.dp)
    ) {
        Column {
            // Hero image with overlays
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = { showEnlargedImage = true }
                    )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(athelete.headshot.href)
                        .crossfade(true)
                        .build(),
                    contentDescription = athelete.displayName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Bottom gradient for jersey number
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.80f))
                            )
                        )
                )

                // Jersey number
                if (athelete.jersey.isNotBlank()) {
                    Text(
                        text = "#${athelete.jersey}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 8.dp, bottom = 8.dp)
                    )
                }

                // Injury badge
                if (isInjured) {
                    Surface(
                        color = Color(0xFFD32F2F),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                    ) {
                        Text(
                            text = "INJ",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            // Info strip
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(teamColor.copy(alpha = 0.10f))
                    .padding(horizontal = 8.dp, vertical = 7.dp)
            ) {
                Text(
                    text = athelete.shortName,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = athelete.position.displayName,
                    style = MaterialTheme.typography.labelSmall,
                    color = teamColor,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                if (athelete.displayHeight.isNotBlank() || athelete.displayWeight.isNotBlank()) {
                    Text(
                        text = "${athelete.displayHeight}  ·  ${athelete.displayWeight}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
            }
        }
    }
}


@Composable
fun AthleteImageDialog(
    athlete: AthletesRosterModel,
    teamColor: Color,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(440.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(athlete.headshot.href)
                        .crossfade(true)
                        .build(),
                    contentDescription = athlete.displayName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.90f))
                            )
                        )
                )

                // Details overlay
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = athlete.displayName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = athlete.position.displayName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = teamColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        if (athlete.jersey.isNotBlank()) {
                            AthleteDetailChip(label = "#${athlete.jersey}")
                        }
                        if (athlete.displayHeight.isNotBlank()) {
                            AthleteDetailChip(label = athlete.displayHeight)
                        }
                        if (athlete.displayWeight.isNotBlank()) {
                            AthleteDetailChip(label = athlete.displayWeight)
                        }
                        if (athlete.age > 0) {
                            AthleteDetailChip(label = "Age ${athlete.age}")
                        }
                    }
                    if (athlete.experience?.years != null && (athlete.experience.years ?: 0) > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Yr ${athlete.experience.years} · ${athlete.debutYear}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.65f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun AthleteDetailChip(label: String) {
    Surface(
        color = Color.White.copy(alpha = 0.18f),
        shape = RoundedCornerShape(6.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
        )
    }
}


// Horizontal card variant — used in standalone detail flows
@Composable
fun AltheleteCard2(
    athelete: AthletesRosterModel,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(athelete.headshot.href)
                    .crossfade(true)
                    .build(),
                contentDescription = athelete.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = athelete.displayName,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = athelete.position.displayName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "#${athelete.jersey}  ·  ${athelete.displayHeight}  ·  ${athelete.displayWeight}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
                athelete.injuries?.firstOrNull()?.let { injury ->
                    Text(
                        text = injury.injuryStatus ?: "",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFD32F2F)
                    )
                }
            }
        }
    }
}


@Composable
fun AthleteImage2(
    athletes: AthletesRosterModel,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
) {
    SportSurface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(athletes.headshot.href)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}