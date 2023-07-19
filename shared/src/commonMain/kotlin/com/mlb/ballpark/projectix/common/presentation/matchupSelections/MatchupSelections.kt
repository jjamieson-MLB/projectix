package com.mlb.ballpark.projectix.common.presentation.matchupSelections

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mlb.ballpark.projectix.common.presentation.models.ProjecTixMatchup
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun MatchupSelections(
    selectedTeam: String?,
    projecTixMatchups: List<ProjecTixMatchup>,
    onGoToTeamPicker: () -> Unit,
    onGoToAccountsList: () -> Unit,
) {
    var checkedState by remember { mutableStateOf(false) }

    Column {
        IconButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            onClick = onGoToAccountsList,
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Exit Account Linking",
            )
        }
        Image(
            painter = painterResource("projectix.png"),
            contentDescription = null,
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(
                onClick = onGoToTeamPicker,
                content = {
                    Text(text = selectedTeam ?: "Select A Team")
                },
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(text = "Home")
            Switch(
                checked = checkedState,
                onCheckedChange = { checkedState = !checkedState },
            )
            Text(text = "Away")
        }
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = StaggeredGridCells.Fixed(2),
            state = rememberLazyStaggeredGridState(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            content = {
                items(
                    projecTixMatchups.filter {
                        if (checkedState) {
                            selectedTeam == it.mAwayNameFull
                        } else {
                            selectedTeam == it.mHomeNameFull
                        }
                    },
                ) { matchup ->
                    MatchupCard(matchup = matchup)
                }
            },
        )
    }
}
