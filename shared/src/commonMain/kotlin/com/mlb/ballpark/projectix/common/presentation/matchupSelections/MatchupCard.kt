package com.mlb.ballpark.projectix.common.presentation.matchupSelections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mlb.ballpark.projectix.common.presentation.models.ProjecTixMatchup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchupCard(
    matchup: ProjecTixMatchup,
    selectable: Boolean,
) {
    var selected by remember { mutableStateOf(false) }
    OutlinedCard(
        enabled = selectable,
        onClick = {
            selected = !selected
            matchup.selected = selected
        },
        border = BorderStroke(
            width = if (selected) 5.dp else 2.dp,
            color = if (selected) Color.Blue else Color.Gray,
        ),
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                if (selected) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        tint = Color.Blue,
                        contentDescription = null,
                    )
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(text = "${matchup.mAwayNameFull}", fontWeight = FontWeight.Bold)
                    Text(text = "@")
                    Text(text = "${matchup.mHomeNameFull}", fontWeight = FontWeight.Bold)
                    Text(text = "Date: ${matchup.mGameDate}")
                    Text(text = "Time: ${matchup.mGameTimeLocalStr}")
                    Text(text = "Venue: ${matchup.mVenueName}")
                }
            }
        },
    )
}
