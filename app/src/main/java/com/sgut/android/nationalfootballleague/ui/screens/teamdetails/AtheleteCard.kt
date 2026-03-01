package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Scale
import com.sgut.android.nationalfootballleague.Athletes
import com.sgut.android.nationalfootballleague.R
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.SportSurface
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.TeamSurface
import com.sgut.android.nationalfootballleague.domain.domainmodels.TeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.TeamDomainModel

@Composable
fun AtheleteCard(
    athelete: TeamDetailWithRosterModel,
    modifier: Modifier,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
           ) {
//             image
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(athelete.athletes[0].headshot?.href)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = athelete.displayName,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp)

            )
        }
    }
}

@Composable
fun AltheleteCard2(
    athelete: Athletes,
    modifier: Modifier,
) {
    var inj = athelete.injuries
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { } // navigate.to.athletedetailsScreen

    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

        ) {

            Box() {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(athelete.headshot.href ?: athelete.flag?.href)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .crossfade(100)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = athelete.displayName,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)

                )


            }


            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = athelete.displayName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = athelete.position.displayName)
                Text("# ${athelete.jersey}")


                val injuries = athelete.injuries


                injuries?.map {
                    Text(text = it.injuryStatus ?: "")
                    Text(text = it.detail?.side ?: "")

                }





            }
        }

    }

}

@Composable
fun AltheleteCard3(
    athelete: Athletes,
    modifier: Modifier,
) {
    Card(modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp)) {
        Box(modifier = Modifier.height(200.dp)) {
            // image ()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(athelete.headshot.href ?: athelete.flag?.href)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .crossfade(100)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = athelete.displayName,
                modifier = Modifier
                    .size(350.dp)
                    .background(Color.Transparent)
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart,
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.width(180.dp)
                ) {
                        Text(athelete.jersey, style = TextStyle(color = Color.Black, fontSize = 80.sp))
                        Text(athelete.position.name, style = TextStyle(color = Color.Black, fontSize = 16.sp))
                        Text(athelete.shortName, style = TextStyle(color = Color.Black, fontSize = 16.sp))
                }
            }
        }
    }
}





@Composable
fun AthleteImage2(
    athletes: Athletes,
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

@Composable
fun VerticalAthleteCard(
    athelete: Athletes,
    modifier: Modifier = Modifier,
    team: TeamDetailWithRosterModel
) {

    val color = HexToJetpackColor2.getColor(team.color)

    SportSurface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(
            start = 4.dp,
            end= 4.dp,
            bottom = 8.dp
        ),

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { }
                .padding(8.dp)
        ) {

            Box(
                modifier = Modifier.wrapContentSize()
                    .background(color = color)

            ){
                AthleteImage2(
                    athletes = athelete,
                    contentDescription = "" ,
                    elevation = 4.dp,
                    modifier = Modifier.size(120.dp)

                )
            }


            Row(){
                Text(
                    text = athelete.displayName,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = " ${ athelete.jersey }",
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Text(
                text = athelete.position.displayName,
                modifier = Modifier.padding(top = 4.dp)
            )
            Row{
                Text(
                    text = athelete.displayHeight,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = "/",
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = athelete.displayWeight,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

        }
    }
}

