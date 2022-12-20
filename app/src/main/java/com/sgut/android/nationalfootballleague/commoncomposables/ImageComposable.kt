package com.sgut.android.nationalfootballleague.commoncomposables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.sgut.android.nationalfootballleague.*
import com.sgut.android.nationalfootballleague.data.domainmodels.ArticleModel
import com.sgut.android.nationalfootballleague.data.domainmodels.TeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.data.domainmodels.TeamDomainModel


@Composable
fun TeamLogoImageLoader(team: TeamDomainModel) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(team.logos?.getOrNull(0)?.href)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = team.displayName,

    )
}

@Composable
fun TeamLogoDetailImageLoader(team: TeamDetailWithRosterModel) {
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
fun TeamLogoScoreboardImageLoader(team: TeamScoreboard) {
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
fun TeamLogoImageloadiner2(team: TeamScoreboard) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(team.logo)
            .crossfade(true)
            .build(),
        contentDescription = team.displayName,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(140.dp)
    )
}

@Composable
fun ArticleCardImageLoader(articleModel: ArticleModel) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(articleModel.images.getOrNull(0)?.url).size(Size.ORIGINAL)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = articleModel.headline,

        )
}

@Composable
fun HeadshotImageLoader(athlete: GameDetailsAthlete, modifier: Modifier) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(athlete.headshot?.href)
            .build(),
        modifier = modifier.size(100.dp),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = athlete.displayName,

        )
}

@Composable
fun VenueCardImageLoader(venue3: Venue3) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(venue3.images3.getOrNull(1)?.href ?: venue3.images3.getOrNull(0)?.href)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = venue3.fullName,
        modifier=Modifier.fillMaxWidth()

        )
}

@Composable
fun GameDetailLogoImageLoader(team: InjTeam, modifier: Modifier) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(team.logo)
            .build(),
        modifier = modifier.size(100.dp),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = team.displayName,

        )
}


@Composable
fun DetailVenueCardImageLoader(venue: GameDetailsVenue) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(venue.images.getOrNull(1)?.href ?: venue.images.getOrNull(0)?.href)
            .build(),
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = venue.fullName,
        modifier=Modifier.fillMaxWidth()

    )
}