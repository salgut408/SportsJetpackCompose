package com.sgut.android.nationalfootballleague.teamdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sgut.android.nationalfootballleague.NextEvent3

@Composable
fun NextEvent(
    nextEvent3: NextEvent3,
    modifier: Modifier
) {
    Card (
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = modifier

            ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {



            Text(text = "Next Event:", style = MaterialTheme.typography.headlineSmall)

            nextEvent3.name?.let { Text(text = it, style = MaterialTheme.typography.titleLarge) }
            nextEvent3.competitions[0].venue?.fullName.let { Text(text = it!!, style = MaterialTheme.typography.titleSmall) }
            nextEvent3.date?.let { Text(text = it, style = MaterialTheme.typography.titleSmall) }
        }


    }
}

