package com.shipblu.ktorkt.utils

import com.shipblu.corekt.domain.httpclient.models.HttpRequest
import com.shipblu.corekt.domain.httpclient.models.HttpResponse
import io.ktor.client.statement.request
import io.ktor.util.toByteArray
import io.ktor.util.toMap

suspend fun <T> KtorHttpResponse.toHttpResponse(
    request: HttpRequest,
    response: T
): HttpResponse<T> {
    return HttpResponse(
        request = request,
        requestHeaders = this.request.headers.toMap(),
        requestMethod = this.request.method.value,
        responseCode = this.status.value,
        responseBodyBytes = this.content.toByteArray(),
        body = response
    )
}