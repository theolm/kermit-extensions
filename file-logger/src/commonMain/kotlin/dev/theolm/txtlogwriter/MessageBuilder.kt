package dev.theolm.txtlogwriter

import co.touchlab.kermit.Severity

public interface MessageBuilder {
    public fun buildLogMessage(severity: Severity, message: String, tag: String): String
    public fun buildThrowableMessage(throwable: Throwable): String
}

internal class MessageBuilderDefault : MessageBuilder {
    override fun buildLogMessage(severity: Severity, message: String, tag: String): String =
        "[${severity.name}] $tag: $message\n"

    override fun buildThrowableMessage(throwable: Throwable) =
        "\n${throwable.stackTraceToString()}\n"
}
