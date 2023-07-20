package com.mlb.ballpark.projectix.common.presentation.matchupSelections

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mlb.ballpark.projectix.common.presentation.models.ProjecTixMatchup
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun MatchupCard(
    matchup: ProjecTixMatchup,
    onSelected: (Boolean) -> Unit,
    selectable: Boolean,
) {
    var selected by rememberSaveable { mutableStateOf(matchup.selected) }
    OutlinedCard(
        enabled = selectable,
        onClick = {
            selected = !selected
            onSelected(selected)
        },
        border = BorderStroke(
            width = if (selected) 2.dp else 2.dp,
            color = if (selected) Color.Blue else Color.Gray,
        ),
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                if (selected) {
                    Icon(
                        modifier = Modifier.align(Alignment.TopEnd),
                        imageVector = Icons.Filled.TaskAlt,
                        tint = Color.Blue,
                        contentDescription = null,
                    )
                }
                Column(
                    modifier = Modifier.padding(8.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            modifier = Modifier.width(24.dp).height(24.dp),
                            painter = painterResource(matchup.mAwayTeamIconRes ?: "projectix.png"),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "@",
                        )
                        Image(
                            modifier = Modifier.width(24.dp).height(24.dp),
                            painter = painterResource(matchup.mHomeTeamIconRes ?: "projectix.png"),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                        )
                    }
//                    Text(text = "${matchup.mAwayNameFull}", fontWeight = FontWeight.Bold)
//                    Text(text = "@")
//                    Text(text = "${matchup.mHomeNameFull}", fontWeight = FontWeight.Bold)
                    Text(text = "${matchup.mGameDate}")
                    Text(text = "${matchup.mGameTimeLocalStr}")
                    Text(text = "${matchup.mVenueName}")
                }
            }
        },
    )
}
