package com.mlb.ballpark.projectix.common.presentation.accountsList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlb.ballpark.projectix.common.presentation.models.Account

@Composable
internal fun AccountsList(
    modifier: Modifier = Modifier,
    accounts: List<Account>,
    onAddAccount: (Account) -> Unit,
    onRemoveAccount: (Account) -> Unit,
    onExitProjecTix: () -> Unit,
    onShowDatePicker: (String) -> Unit,
    chosenDates: List<Pair<String, String>>, // todo: hacked in
    onRemoveDate: (Pair<String, String>) -> Unit, // todo: hacked in
    teams: List<String>, // todo: move this internal
) {
    var showOptions by remember { mutableStateOf(false) }
    var showTeamPicker by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize(),
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
            if (accounts.isEmpty()) {
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = "No accounts currently linked to ProjecTix.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Select \"+ Link ProjecTix Account\" to get started!",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                )
            } else {
                Text(
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 24.dp),
                    text = "You've linked verified accounts to your ProjecTix.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    state = listState,
                ) {
                    items(accounts) { connectedAccount ->
                        ElevatedCard(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                        ) {
                            Row(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    contentDescription = null,
                                )
                                Text(
                                    modifier = Modifier.padding(start = 16.dp),
                                    text = connectedAccount.accountType.value,
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                )
                                Spacer(modifier = modifier.weight(1F))
                                IconButton(
                                    onClick = {
                                        if (accounts.size == 1) { // todo: super hacked together
                                            chosenDates.forEach {
                                                onRemoveDate(it)
                                            }
                                        }
                                        onRemoveAccount(connectedAccount)
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Delete,
                                        contentDescription = "Remove Account from ProjecTix",
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 16.dp),
                        ) {
                            Button(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                onClick = { showTeamPicker = true },
                            ) {
                                Text(
                                    text = "Submit a Team & Date",
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }

                    item {
                        val text = if (chosenDates.isNotEmpty()) {
                            "You are eligible for the shown teams and dates."
                        } else {
                            "Add the teams and dates you want to be eligible for."
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .align(Alignment.CenterHorizontally),
                            text = text,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                        )
                    }

                    items(chosenDates.sortedBy { it.first }) { chosenDate ->
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column {
                                Text(
                                    text = chosenDate.first,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(text = chosenDate.second)
                            }
                            Spacer(modifier = Modifier.weight(1F))
                            IconButton(
                                onClick = { onRemoveDate(chosenDate) },
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Delete,
                                    contentDescription = "Remove Account from ProjecTix",
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showTeamPicker) {
            TeamPicker(
                teams = teams,
                onShowDatePicker = {
                    showTeamPicker = false
                    onShowDatePicker(it)
                },
                onExitProjecTix = onExitProjecTix,
            )
        } else {
            ExpandedFabMenu(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp),
                showOptions = showOptions,
                onShowOptionsChange = { showOptions = !showOptions },
                onOptionSelect = {
                    // todo go to account link page
                    onAddAccount(it)
                    showOptions = false
                },
            )
        }
    }
}
