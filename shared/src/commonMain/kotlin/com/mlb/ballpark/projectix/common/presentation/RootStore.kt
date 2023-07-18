package com.mlb.ballpark.projectix.common.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mlb.ballpark.projectix.common.presentation.models.Account

internal class RootStore() {

    var state: RootState by mutableStateOf(initialState())
        private set

    fun addAccount(account: Account) {
        state = state.copy(
            accounts = state.accounts.plus(account),
        )
    }

    fun removeAccount(account: Account) {
        state = state.copy(
            accounts = state.accounts.minus(account),
        )
    }

    private fun initialState(): RootState =
        RootState(listOf())

    data class RootState(
        val accounts: List<Account> = emptyList(),
    )
}
