package com.mlb.ballpark.projectix.common.presentation.accountPicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mlb.ballpark.projectix.common.presentation.models.Account

@Composable
internal fun AccountPicker(
    onAddAccount: (Account) -> Unit,
    onExitAccountPicker: () -> Unit,
) {
    Column {
        IconButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            onClick = onExitAccountPicker,
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Exit Account Linking",
            )
        }
        AccountSelectionMenu(
            onOptionSelect = {
                onAddAccount(it)
            },
        )
    }
}