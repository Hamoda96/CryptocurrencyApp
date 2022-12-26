package com.shipblu.corekt.domain.httpclient.models

import kotlinx.serialization.Serializable

@Serializable
data class HttpHeader(val key: HeaderType, val value: String) {

    fun toStringPair(): Pair<String, String> {
        return key.toString() to value
    }

    fun toMapItem(): Pair<String, List<String>> {
        return key.toString() to listOf(value)
    }
}