package com.hamoda.coins.core.domain.repository

import com.hamoda.coins.core.domain.model.coins.Coins
import com.shipblu.corekt.domain.httpclient.models.HttpResponse

interface BaseCoinsRemote {
    suspend fun getCoins() : HttpResponse<List<Coins>>
}