package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import com.hamoda.coins.core.domain.model.coins.Coins

data class CoinListState(
    val coins: List<Coins> = listOf()
)
