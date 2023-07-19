package com.mlb.ballpark.projectix.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mlb.ballpark.projectix.common.presentation.accountAuth.AccountAuthorization
import com.mlb.ballpark.projectix.common.presentation.accountPicker.AccountPicker
import com.mlb.ballpark.projectix.common.presentation.accountsList.AccountsList
import com.mlb.ballpark.projectix.common.presentation.matchupSelections.MatchupSelections
import com.mlb.ballpark.projectix.common.presentation.models.Account
import com.mlb.ballpark.projectix.common.presentation.models.ProjecTixMatchup
import com.mlb.ballpark.projectix.common.presentation.models.Screen
import com.mlb.ballpark.projectix.common.presentation.teamPicker.TeamPicker

@Composable
fun RootContent(
    modifier: Modifier = Modifier,
    projecTixMatchups: List<ProjecTixMatchup>,
    chosenDates: List<Pair<String, String>>,
    onTeamSelected: (String) -> Unit,
    onExitProjecTix: () -> Unit,
    onRemoveDate: (Pair<String, String>) -> Unit,
    teams: List<String>,
) {
    val model = remember { RootStore() }
    val state = model.state
    val screenState: MutableState<Pair<Screen, Any?>> =
        remember { mutableStateOf(Pair(Screen.LINKED_ACCOUNTS, null)) }
    when (screenState.value.first) {
        Screen.LINKED_ACCOUNTS -> {
            AccountsList(
                modifier = modifier,
                accounts = model.state.accounts,
                onGoToAccountPicker = {
                    screenState.goToAccountPicker()
                },
                onGoToMatchupSelection = {
                    screenState.goToMatchupSelection(null)
                },
                onRemoveAccount = { model.removeAccount(it) },
                onExitProjecTix = onExitProjecTix,
                onShowDatePicker = onTeamSelected,
                chosenDates = chosenDates,
                onRemoveDate = onRemoveDate,
                teams = teams,
            )
        }

        Screen.ACCOUNT_PICKER -> {
            AccountPicker(
                onAddAccount = {
                    screenState.goToAccountAuthorization(it)
                },
                onExitAccountPicker = {
                    screenState.goToAccountsList()
                },
            )
        }

        Screen.AUTHENTICATION -> {
            screenState.value.second?.let {
                AccountAuthorization(
                    account = it as Account,
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

        Screen.MATCHUP_SELECTION -> {
            MatchupSelections(
                selectedTeam = screenState.value.second as? String,
                projecTixMatchups = projecTixMatchups,
                onGoToTeamPicker = {
                    screenState.goToTeamPicker()
                },
                onGoToAccountsList = {
                    screenState.goToAccountsList()
                },
            )
        }

        Screen.TEAM_PICKER -> {
            TeamPicker(
                onExitProjecTix = {},
                onGoToMatchupSelection = {
                    onTeamSelected(it)
                    screenState.goToMatchupSelection(it as? String)
                },
                teams = teams,
            )
        }
    }
}

internal fun MutableState<Pair<Screen, Any?>>.goToAccountPicker() {
    this.value = Pair(Screen.ACCOUNT_PICKER, null)
}

internal fun MutableState<Pair<Screen, Any?>>.goToAccountAuthorization(account: Account) {
    this.value = Pair(Screen.AUTHENTICATION, account)
}

internal fun MutableState<Pair<Screen, Any?>>.goToAccountsList() {
    this.value = Pair(Screen.LINKED_ACCOUNTS, null)
}

internal fun MutableState<Pair<Screen, Any?>>.goToMatchupSelection(selectedTeam: String?) {
    this.value = Pair(Screen.MATCHUP_SELECTION, selectedTeam)
}

internal fun MutableState<Pair<Screen, Any?>>.goToTeamPicker() {
    this.value = Pair(Screen.TEAM_PICKER, null)
}
