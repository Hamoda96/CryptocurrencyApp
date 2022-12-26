package com.shipblu.corekt.domain.httpclient.models

import kotlinx.serialization.Serializable

@Serializable
data class HttpResponse<T>(
    val request: HttpRequest,
    val requestHeaders: Map<String, List<String>>,
    val requestMethod: String,
    val responseBodyBytes: ByteArray,
    val responseCode: Int,
    val body: T
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HttpResponse<*>

        if (request != other.request) return false
        if (requestHeaders != other.requestHeaders) return false
        if (requestMethod != other.requestMethod) return false
        if (!responseBodyBytes.contentEquals(other.responseBodyBytes)) {
            return false
        }
        if (responseCode != other.responseCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = request.hashCode()
        result = 31 * result + requestHeaders.hashCode()
        result = 31 * result + requestMethod.hashCode()
        result = 31 * result + responseBodyBytes.contentHashCode()
        result = 31 * result + responseCode
        return result
    }
}