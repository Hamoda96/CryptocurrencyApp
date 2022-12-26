package com.hamoda.coins.core.domain.model.error

import com.hamoda.coins.core.domain.model.message.Message
import java.util.UUID

sealed class Throw(
    val info: Message,
    override val cause: Throwable,
    override val message: String? = cause.message
) : Exception(cause) {

    sealed class Info(info: Message) : Throw(info, Throwable()) {
        open class Connectivity(info: Message) : Throw.Info(info)

        open class Authentication(info: Message) : Throw.Info(info)

        open class Api(info: Message) : Throw.Info(info)

        open class Location(info: Message) : Throw.Info(info)

        open class UnExpected(info: Message) : Throw.Info(info)
    }

    sealed class Error(
        val exception: Throwable,
        val callSite: String,
        val tag: String = callSite,
        val uid: String = UUID.randomUUID().toString(),
        val details: String,
        info: Message,
    ) : Throw(info, exception) {

        open class Connectivity(
            info: Message,
            callSite: String,
            exception: Exception,
            details: String
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = info
        )

        open class Authentication(
            info: Message,
            callSite: String,
            exception: Exception,
            details: String
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = info
        )

        open class Api(
            info: Message,
            callSite: String,
            exception: Exception,
            details: String
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = info
        )

        open class Cached(
            info: Message,
            callSite: String,
            exception: Exception,
            details: String
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = info
        )

        open class Credentials(
            info: Message,
            callSite: String,
            exception: Exception,
            details: String
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = info
        )

        open class Http(
            info: Message,
            callSite: String,
            exception: Exception,
            details: String,
            override val message: String
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = info
        )

        open class Location(
            info: Message,
            callSite: String,
            exception: Exception,
            details: String
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = info
        )

        class FromException(
            exception: Exception,
            callSite: String,
            details: String = "",
        ) : Throw.Error(
            exception = exception,
            callSite = callSite,
            details = details,
            info = Message(
                en = "Unexpected error happened",
                ar = "لقد حدث خطأ غير متوقع"
            )
        )

        class UnExpected(
            message: String,
            callSite: String,
            details: String
        ) : Throw.Error(
            exception = IllegalStateException(message),
            callSite = callSite,
            details = details,
            info = Message(
                en = "Unexpected error happened",
                ar = "لقد حدث خطأ غير متوقع"
            )
        )

        private var isReported = false

        fun reported() {
            isReported = true
        }

        fun isReported(): Boolean {
            return isReported
        }
    }

    override fun toString(): String {
        return info.en
    }
}