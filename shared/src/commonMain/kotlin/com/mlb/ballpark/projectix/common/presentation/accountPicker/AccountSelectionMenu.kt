package com.mlb.ballpark.projectix.common.presentation.accountPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mlb.ballpark.projectix.common.presentation.models.Account
import com.mlb.ballpark.projectix.common.presentation.models.AccountType

@Composable
internal fun AccountSelectionMenu(
    modifier: Modifier = Modifier,
    onOptionSelect: (Account) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.surface),
    ) {
        LazyColumn(
            modifier = Modifier.align(Alignment.End),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                AccountCard(
                    accountType = AccountType.SPORTS_CONNECT,
                    accountName = "Sports Connect",
                    onOptionSelect = onOptionSelect,
                )
            }
            item {
                AccountCard(
                    accountType = AccountType.SPORTS_ENGINE,
                    accountName = "Sports Engine",
                    onOptionSelect = onOptionSelect,
                )
            }
            item {
                AccountCard(
                    accountType = AccountType.LEAGUE_APPS,
                    accountName = "League Apps",
                    onOptionSelect = onOptionSelect,
                )
            }
            item {
                AccountCard(
                    accountType = AccountType.GAME_CHANGER,
                    accountName = "GameChanger",
                    onOptionSelect = onOptionSelect,
                )
            }

            // Add more options as needed
        }
    }
}
