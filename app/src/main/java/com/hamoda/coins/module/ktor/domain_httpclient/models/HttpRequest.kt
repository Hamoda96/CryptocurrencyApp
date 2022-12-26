package com.shipblu.corekt.domain.httpclient.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class HttpRequest(
    val url: String,
    @Contextual val body: Any?,
    val headers: List<HttpHeader>
)