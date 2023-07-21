package com.mlb.ballpark.projectix.common.presentation.accountsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mlb.ballpark.projectix.common.presentation.models.Account
import com.mlb.ballpark.projectix.common.presentation.models.ProjecTixMatchup
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun AccountsList(
    modifier: Modifier = Modifier,
    accounts: List<Account>,
    onGoToAccountPicker: () -> Unit,
    onGoToMatchupSelection: () -> Unit,
    onRemoveAccount: (Account) -> Unit,
    onExitProjecTix: () -> Unit,
    teams: List<String>, // todo: move this internal
    matchupsSelected: List<ProjecTixMatchup>,
) {
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

        if (accounts.isNotEmpty()) {
            IconButton(
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .align(Alignment.TopEnd),
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "ProjecTix Profile",
                )
            }
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.wrapContentSize(),
                painter = painterResource("projectix.png"),
                contentDescription = null,
            )
            if (accounts.isEmpty()) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "No accounts currently linked to ProjecTix.",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Select \"+ Link ProjecTix Account\" to get started!",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                )
                Button(
                    modifier = Modifier.padding(32.dp),
                    onClick = onGoToAccountPicker,
                ) {
                    Text(text = "Link ProjecTix Account")
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.offset(y = (-32).dp),
                        painter = painterResource("green-check-mark-verified-circle.png"),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .offset(y = (-32).dp),
                        text = "You've linked a verified account to ProjecTix.",
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .offset(y = (-32).dp)
                        .clickable { onRemoveAccount(accounts.first()) },
                    text = "Unlink Account.",
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .offset(y = (-32).dp),
                    state = listState,
                ) {
//                    item {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 24.dp, bottom = 16.dp),
//                        ) {
//                            Button(
//                                modifier = Modifier
//                                    .align(Alignment.Center),
//                                onClick = { showTeamPicker = true },
//                            ) {
//                                Text(
//                                    text = "Submit a Team & Date",
//                                    textAlign = TextAlign.Center,
//                                )
//                            }
//                        }
//                    }

                    item {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp)
                                .align(Alignment.CenterHorizontally),
                            text = "You are eligible for your chosen matchups!",
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                        )
                    }

                    if (matchupsSelected.isNotEmpty()) {
                        item {
                            Text(
                                modifier = Modifier.padding(horizontal = 32.dp, vertical = 32.dp),
                                text = "You're all set.\nWe'll notify you no later than 14 days in advance via email. Thank you!",
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

//                    items(chosenDates.sortedBy { it.first }) { chosenDate ->
//                        Row(
//                            modifier = Modifier.padding(horizontal = 16.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                        ) {
//                            Column {
//                                Text(
//                                    text = chosenDate.first,
//                                    fontWeight = FontWeight.Bold,
//                                )
//                                Text(text = chosenDate.second)
//                            }
//                            Spacer(modifier = Modifier.weight(1F))
//                            IconButton(
//                                onClick = { onRemoveDate(chosenDate) },
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Rounded.Delete,
//                                    contentDescription = "Remove Account from ProjecTix",
//                                )
//                            }
//                        }
//                    }
                }
            }
        }

        if (accounts.isNotEmpty() && matchupsSelected.isEmpty()) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp),
                text = {
                    Text(text = "Select Matchups")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Eligible Games",
                    )
                },
                onClick = onGoToMatchupSelection,
                containerColor = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
