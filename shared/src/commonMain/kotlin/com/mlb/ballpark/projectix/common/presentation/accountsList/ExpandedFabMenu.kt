package com.mlb.ballpark.projectix.common.presentation.accountsList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mlb.ballpark.projectix.common.presentation.models.Account
import com.mlb.ballpark.projectix.common.presentation.models.AccountType

@Composable
internal fun ExpandedFabMenu(
    modifier: Modifier = Modifier,
    showOptions: Boolean,
    onShowOptionsChange: () -> Unit,
    onOptionSelect: (Account) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.End),
            visible = showOptions,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Column(
                modifier = Modifier.align(Alignment.End),
                horizontalAlignment = Alignment.End,
            ) {
                AccountFab(
                    accountType = AccountType.SPORTS_CONNECT,
                    accountName = "Sports Connect",
                    onOptionSelect = onOptionSelect,
                )
                AccountFab(
                    accountType = AccountType.SPORTS_ENGINE,
                    accountName = "Sports Engine",
                    onOptionSelect = onOptionSelect,
                )
                AccountFab(
                    accountType = AccountType.LEAGUE_APPS,
                    accountName = "League Apps",
                    onOptionSelect = onOptionSelect,
                )
                AccountFab(
                    accountType = AccountType.GAME_CHANGER,
                    accountName = "GameChanger",
                    onOptionSelect = onOptionSelect,
                )

                // Add more options as needed
            }
        }

        ExtendedFloatingActionButton(
            modifier = Modifier.align(Alignment.End),
            text = {
                val text = if (showOptions) {
                    "Choose Account"
                } else {
                    "Link ProjecTix Account"
                }
                Text(text = text)
            },
            icon = {
                if (showOptions) {
                    Icon(
                        imageVector = Icons.Default.Expand,
                        contentDescription = "Collapse Menu",
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add An Account",
                    )
                }
            },
            onClick = onShowOptionsChange,
            containerColor = MaterialTheme.colorScheme.primary,
        )
    }
}
