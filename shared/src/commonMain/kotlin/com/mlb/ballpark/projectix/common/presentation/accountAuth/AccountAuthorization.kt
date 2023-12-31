package com.mlb.ballpark.projectix.common.presentation.accountAuth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mlb.ballpark.projectix.common.presentation.models.Account
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun AccountAuthorization(
    account: Account,
    onExitAuthorization: () -> Unit,
    onSuccessfulAccountLink: (Account) -> Unit,
) {
    var showLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        IconButton(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            onClick = onExitAuthorization,
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Exit Account Linking",
            )
        }
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            painter = painterResource("projectix.png"),
            contentDescription = "ProjecTix Logo",
        )
        Icon(
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .offset(y = (-32).dp)
                .align(Alignment.CenterHorizontally),
            imageVector = Icons.Rounded.Link,
            contentDescription = null,
        )
        Image(
            modifier = Modifier
                .height(256.dp)
                .width(256.dp)
                .offset(x = (-8).dp, y = (-80).dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(account.accountIcon),
            contentDescription = "${account.accountType.value} Logo",
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .align(Alignment.CenterHorizontally)
                .offset(y = (-80).dp),
            text = "Link your ${account.accountType.value} account to ProjecTix",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )

        Button(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
                .offset(y = (-80).dp),
            onClick = {
                showLoading = true
                onSuccessfulAccountLink(account)
                // todo: set account to linked (or something?)
                // todo: sign in/add account/go back and have it added
            },
            contentPadding = PaddingValues(horizontal = 56.dp, vertical = 12.dp),
            enabled = !showLoading,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (showLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                }
                Text(
                    text = "Authorize Account Linking",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
