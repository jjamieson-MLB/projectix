package com.mlb.ballpark.projectix.common.presentation.accountPicker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlb.ballpark.projectix.common.presentation.models.Account
import com.mlb.ballpark.projectix.common.presentation.models.AccountType
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
internal fun AccountCard(
    accountType: AccountType,
    accountName: String,
    accountIcon: String,
    onOptionSelect: (Account) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        onClick = {
            onOptionSelect(
                Account(
                    accountType = accountType,
                    accountIcon = accountIcon,
                ),
            )
        },
        content = {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.width(80.dp).height(80.dp),
                    painter = painterResource(accountIcon),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Add $accountName Account",
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = accountName,
                    fontSize = 24.sp,
                )
            }
        },
    )
}
