package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamoda.coins.common.Resource
import com.hamoda.coins.core.domain.model.coins.Coins
import com.hamoda.coins.core.domain.use_case.get_coins.GetCoinsUseCase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeActivity

class CoinListViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(
        CoinListState(
        )
    )
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