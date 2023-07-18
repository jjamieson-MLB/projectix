package com.mlb.ballpark.projectix.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mlb.ballpark.projectix.common.presentation.accountAuth.AccountAuthorization
import com.mlb.ballpark.projectix.common.presentation.accountsList.AccountsList
import com.mlb.ballpark.projectix.common.presentation.models.Account
import com.mlb.ballpark.projectix.common.presentation.models.Screen

@Composable
fun RootContent(
    modifier: Modifier = Modifier,
    chosenDates: List<Pair<String, String>>,
    onShowDatePicker: (String) -> Unit,
    onExitProjecTix: () -> Unit,
    onRemoveDate: (Pair<String, String>) -> Unit,
    teams: List<String>,
) {
    val model = remember { RootStore() }
    val state = model.state
    val screenState: MutableState<Pair<Screen, Account?>> =
        remember { mutableStateOf(Pair(Screen.LINKED_ACCOUNTS, null)) }
    when (screenState.value.first) {
        Screen.LINKED_ACCOUNTS -> {
            AccountsList(
                modifier = modifier,
                accounts = model.state.accounts,
                onAddAccount = {
                    screenState.goToAccountAuthorization(it)
                },
                onRemoveAccount = { model.removeAccount(it) },
                onExitProjecTix = onExitProjecTix,
                onShowDatePicker = onShowDatePicker,
                chosenDates = chosenDates,
                onRemoveDate = onRemoveDate,
                teams = teams,
            )
        }

        Screen.AUTHENTICATION -> {
            screenState.value.second?.let {
                AccountAuthorization(
                    account = it,
                    onExitAuthorization = {
                        screenState.goToAccountsList()
                    },
                    onSuccessfulAccountLink = {
                        model.addAccount(it)
                        screenState.goToAccountsList()
                    },
                )
            } ?: screenState.goToAccountsList()
        }
    }
}

internal fun MutableState<Pair<Screen, Account?>>.goToAccountAuthorization(account: Account) {
    this.value = Pair(Screen.AUTHENTICATION, account)
}

internal fun MutableState<Pair<Screen, Account?>>.goToAccountsList() {
    this.value = Pair(Screen.LINKED_ACCOUNTS, null)
}
