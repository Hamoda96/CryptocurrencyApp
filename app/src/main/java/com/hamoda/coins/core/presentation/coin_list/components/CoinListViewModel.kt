package com.hamoda.coins.core.presentation.coin_list.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hamoda.coins.core.domain.use_case.get_coins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        MainScope().launch {
            getCoins()
        }
    }

    private suspend fun getCoins() {
        val result = getCoinsUseCase.invoke()
        _state.value = _state.value.copy(coins = result)
    }
}