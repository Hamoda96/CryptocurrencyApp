package com.hamoda.coins.module.ktor.data.repository

import com.shipblu.corekt.domain.httpclient.errors.HttpThrow
import com.shipblu.corekt.domain.httpclient.models.HttpRequest
import com.shipblu.corekt.domain.httpclient.repositories.BaseHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class KtorHttpClient(engine: HttpClientEngine) :
    BaseHttpClient<HttpResponse> {

    @ExperimentalSerializationApi
    private val client: HttpClient = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                json = kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }
        // install(HttpTimeout) {
        //     requestTimeoutMillis = HTTP_REQUEST_TIMEOUT
        //     connectTimeoutMillis = HTTP_REQUEST_TIMEOUT
        // }
    }

    override suspend fun get(request: HttpRequest): HttpResponse {
        return executeRequest {
            client.get(block = buildHttpRequest(request))
        }
    }

    override suspend fun post(request: HttpRequest): HttpResponse {
        return executeRequest {
            client.post(block = buildHttpRequest(request))
        }
    }

    override suspend fun patch(request: HttpRequest): HttpResponse {
        return executeRequest {
            client.patch(block = buildHttpRequest(request))
        }
    }

    override suspend fun delete(request: HttpRequest): HttpResponse {
        return executeRequest {
            client.delete(block = buildHttpRequest(request))
        }
    }

    private fun buildHttpRequest(
        httpRequest: HttpRequest
    ): HttpRequestBuilder.() -> Unit =
        {
            url(httpRequest.url)

            if (httpRequest.body != null) {
                body = httpRequest.body!!
            }
            if (httpRequest.headers != null) {
                for (httpHeader in httpRequest.headers) {
                    header(httpHeader.key.toString(), httpHeader.value)
                }
            }
        }

    private suspend fun
            executeRequest(request: suspend () -> HttpResponse): HttpResponse {
        return try {
            request()
        } catch (e: RedirectResponseException) {
            val message = getErrorMessage(e.response)
            throw HttpThrow.Redirect(
                exception = e,
                message = message,
                details = "params -> ",
                callSite = "KtorHttpClint -> execute request"
            )
        } catch (e: ClientRequestException) {
            val message = getErrorMessage(e.response)
            throw HttpThrow.Client(
                exception = e,
                message = message,
                details = "params -> ",
                callSite = "KtorHttpClint -> execute request"
            )
        } catch (e: ServerResponseException) {
            val message = getErrorMessage(e.response)
            throw HttpThrow.Server(
                exception = e,
                message = message,
                details = "params -> ",
                callSite = "KtorHttpClint -> execute request"
            )
        } catch (e: Exception) {
            throw HttpThrow.Other(
                exception = e,
                message = e.message ?: "",
                details = "params -> ",
                callSite = "KtorHttpClint -> execute request"
            )
        }
    }

    private suspend fun getErrorMessage(res: HttpResponse): String {
        val responseObject = Json.parseToJsonElement(res.readText()).jsonObject
        return responseObject["message"]?.jsonPrimitive?.content
            ?: responseObject["detail"]?.jsonPrimitive?.content
            ?: "Error code ${res.status}"
    }
}