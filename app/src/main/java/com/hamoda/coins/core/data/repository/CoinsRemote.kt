package com.hamoda.coins.core.data.repository

import com.hamoda.coins.common.Constants
import com.hamoda.coins.core.data.remote.dto.CoinsDto
import com.hamoda.coins.core.domain.model.coins.Coins
import com.hamoda.coins.core.domain.repository.BaseCoinsRemote
import com.hamoda.coins.module.ktor.generateRequest
import com.shipblu.corekt.domain.httpclient.models.HttpResponse
import com.shipblu.corekt.domain.httpclient.repositories.BaseHttpClient
import com.shipblu.ktorkt.utils.KtorHttpResponse
import com.shipblu.ktorkt.utils.toHttpResponse
import io.ktor.client.call.receive

class CoinsRemote(
    private val httpClient: BaseHttpClient<KtorHttpResponse>
) : BaseCoinsRemote {
    override suspend fun getCoins(): HttpResponse<List<Coins>> {
        val url = Constants.coinsUrl
        val request = generateRequest(url)
        val response = httpClient.get(request)

        return response.toHttpResponse(
            request = request,
            response = response.receive<List<CoinsDto>>().map {
                it.toModel()
            }
        )
    }
}