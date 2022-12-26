package com.shipblu.corekt.domain.httpclient.repositories

import com.shipblu.corekt.domain.httpclient.models.HttpRequest

interface BaseHttpClient<RESPONSE> {

    suspend fun get(request: HttpRequest): RESPONSE

    suspend fun post(request: HttpRequest): RESPONSE

    suspend fun patch(request: HttpRequest): RESPONSE

    suspend fun delete(request: HttpRequest): RESPONSE
}