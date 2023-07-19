package com.mlb.ballpark.projectix.common.presentation.matchupSelections

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mlb.ballpark.projectix.common.presentation.models.ProjecTixMatchup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchupCard(
    matchup: ProjecTixMatchup,
) {
    ElevatedCard(
        onClick = {},
        content = {
            Column {
                Text(text = "${matchup.mAwayNameFull} at ${matchup.mHomeNameFull}")
                Text(text = "Date: ${matchup.mGameDate}")
                Text(text = "Time: ${matchup.mGameTimeLocalStr}")
                Text(text = "Location: ${matchup.mVenueName}, ${matchup.mVenueCity}")
            }
        },
    )
}
