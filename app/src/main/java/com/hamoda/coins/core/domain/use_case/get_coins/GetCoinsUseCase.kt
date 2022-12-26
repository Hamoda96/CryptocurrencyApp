package com.hamoda.coins.core.domain.use_case.get_coins

import com.hamoda.coins.common.Resource
import com.hamoda.coins.core.domain.model.coins.Coins
import com.hamoda.coins.core.domain.repository.BaseCoinsRemote

class GetCoinsUseCase(
    private val suite: BaseCoinsRemote
) {
    suspend operator fun invoke(): List<Coins> {
        try {
            val response = suite.getCoins()

            return response.body
        } catch (ex: Exception) {
            throw ex
        }
    }
}