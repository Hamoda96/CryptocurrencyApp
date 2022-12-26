package com.shipblu.corekt.domain.httpclient.errors

import com.hamoda.coins.core.domain.model.error.Throw
import com.hamoda.coins.core.domain.model.message.Message

sealed class HttpThrow(
    info: Message,
    exception: Exception,
    details: String,
    message: String = info.en,
    callSite: String
) : Throw.Error.Http(
    info = info,
    exception = exception,
    callSite = callSite,
    details = details,
    message = message
) {
    class Redirect(
        exception: Exception,
        message: String,
        details: String,
        callSite: String
    ) : HttpThrow(
        info = Message(en = message, ar = message),
        exception = exception,
        details = details,
        callSite = callSite,
    )

    class Client(
        exception: Exception,
        message: String,
        details: String,
        callSite: String
    ) : HttpThrow(
        info = Message(en = message, ar = message),
        exception = exception,
        details = details,
        callSite = callSite,
    )

    class Server(
        exception: Exception,
        message: String,
        details: String,
        callSite: String
    ) : HttpThrow(
        info = Message(en = message, ar = message),
        exception = exception,
        callSite = callSite,
        details = details,
    )

    class Other(
        exception: Exception,
        message: String,
        details: String,
        callSite: String
    ) : HttpThrow(
        info = Message(en = message, ar = message),
        exception = exception,
        callSite = callSite,
        details = details,
    )
}