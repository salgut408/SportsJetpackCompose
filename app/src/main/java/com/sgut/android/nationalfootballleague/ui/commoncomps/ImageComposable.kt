package com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sgut.android.nationalfootballleague.R
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_game_details.GameDetailsVenueModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_scoreboard.ScoreboardTeamModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.VenueModel


@Composable
fun BasicImage(
    imgUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp,
    backgroundColor: Color,
    borderWidth: Dp,
    borderColor: Color,
    shape: Shape = CircleShape,
    padding: Dp = 0.dp
) {
    SportSurface(
        color = backgroundColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier,
        border = BorderStroke(borderWidth, borderColor),
        padding = padding
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imgUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun TeamLogoDetailImageLoader(team: FullTeamDetailWithRosterModel) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(team.logos.getOrNull(0)?.href)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = team.displayName,
    )
}

@Composable
fun TeamLogoScoreboardImageLoader(team: ScoreboardTeamModel) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(team.logo)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = team.displayName,
    )
}


@Composable
fun GeneralImageLoader(
    href: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
) {
    SportSurface(
        color = Color.Transparent,
        elevation = elevation,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(href)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun GenericImageLoader(obj: String, modifier: Modifier) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(obj)
            .build(),
        modifier = modifier,
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = obj,

        )
}


@Composable
fun VenueCardImageLoader(venue: VenueModel) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(venue.images3.getOrNull(1)?.href ?: venue.images3.getOrNull(0)?.href)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = venue.fullName,
        modifier = Modifier.fillMaxWidth()

    )
}


@Composable
fun DetailVenueCardImageLoader(venue: GameDetailsVenueModel) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(venue.images.getOrNull(1)?.href ?: venue.images.getOrNull(0)?.href)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = venue.fullName,
        modifier = Modifier.fillMaxWidth()

    )
}

@Composable
fun OnlineNowImage() {
    Icon(
        modifier = Modifier.size(30.dp),
        painter = painterResource(id = R.drawable.myspace_online), contentDescription = null)

}