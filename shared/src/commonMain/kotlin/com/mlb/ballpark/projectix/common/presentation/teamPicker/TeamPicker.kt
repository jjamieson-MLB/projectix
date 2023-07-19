package com.mlb.ballpark.projectix.common.presentation.teamPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TeamPicker(
    modifier: Modifier = Modifier,
    onExitProjecTix: () -> Unit,
    onGoToMatchupSelection: (String) -> Unit,
    teams: List<String>,
) {
    Box(
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
    ) {
        val listState = rememberLazyListState()

        IconButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            onClick = onExitProjecTix,
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Exit Account Linking",
            )
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 24.dp),
                text = "Select a team.",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(teams.sorted()) { team ->
                    ElevatedCard(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        onClick = {
                            onGoToMatchupSelection(team)
                        },
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = team,
                            fontSize = 24.sp,
                            color = Color.Black,
                        )
                    }
                }
            }
        }
    }
}
