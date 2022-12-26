package com.hamoda.coins.module.ktor

import com.shipblu.corekt.domain.httpclient.models.HeaderType
import com.shipblu.corekt.domain.httpclient.models.HttpHeader
import com.shipblu.corekt.domain.httpclient.models.HttpRequest

internal fun generateRequest(
    url: String,
    token: String? =null,
    body: Any? = null
): HttpRequest {
    return HttpRequest(
        url = url,
        headers = listOf(
            HttpHeader(
                key = HeaderType.AUTHORIZATION,
                value = "Bearer $token"
            ),
            HttpHeader(
                key = HeaderType.CONTENT_TYPE,
                value = "application/json"
            )
        ),
        body = body
    )
}