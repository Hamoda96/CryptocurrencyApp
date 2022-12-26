package com.shipblu.corekt.domain.httpclient.models

import kotlinx.serialization.Serializable

@Serializable
enum class HeaderType {
    AUTHORIZATION {
        override fun toString(): String = authorization
    },
    CONTENT_TYPE {
        override fun toString(): String = "Content-Type"
    };

    protected val authorization = "Authorization"
}