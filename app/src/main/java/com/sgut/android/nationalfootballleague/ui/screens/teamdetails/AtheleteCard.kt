package com.sgut.android.nationalfootballleague.ui.screens.teamdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.sgut.android.nationalfootballleague.Athletes
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.AthletesRosterModel
import com.sgut.android.nationalfootballleague.domain.domainmodels.new_models_team_detail_roster.FullTeamDetailWithRosterModel
import com.sgut.android.nationalfootballleague.ui.commoncomps.commoncomposables.SportSurface



@Composable
fun AltheleteCard2(
    athelete: AthletesRosterModel,
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
    Card(modifier = modifier.fillMaxWidth(2f),
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

@Composable
fun VerticalAthleteCard(
    athelete: AthletesRosterModel,
    modifier: Modifier = Modifier,
    team: FullTeamDetailWithRosterModel
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

