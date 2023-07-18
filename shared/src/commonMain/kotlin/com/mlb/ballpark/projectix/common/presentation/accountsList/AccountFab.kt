package com.mlb.ballpark.projectix.common.presentation.accountsList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlb.ballpark.projectix.common.presentation.models.Account
import com.mlb.ballpark.projectix.common.presentation.models.AccountType

@Composable
internal fun AccountFab(
    accountType: AccountType,
    accountName: String,
    onOptionSelect: (Account) -> Unit,
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 4.dp)
                .background(MaterialTheme.colorScheme.surface),
        ) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = accountName,
                fontSize = 14.sp,
            )
        }
        SmallFloatingActionButton(
            onClick = {
                onOptionSelect(
                    Account(
                        accountType = accountType,
                    ),
                )
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add $accountName Account",
                )
            },
            containerColor = MaterialTheme.colorScheme.primary,
        )
    }
}
