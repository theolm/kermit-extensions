package dev.theolm.txtlogwriter

import co.touchlab.kermit.Severity

interface MessageBuilder {
    fun buildLogMessage(severity: Severity, message: String, tag: String): String
    fun buildThrowableMessage(throwable: Throwable): String
}

internal class MessageBuilderImpl : MessageBuilder {
    override fun buildLogMessage(severity: Severity, message: String, tag: String): String =
        "[${severity.name}] $tag: $message\n"

    override fun buildThrowableMessage(throwable: Throwable) =
        "\n${throwable.stackTraceToString()}\n"
}
