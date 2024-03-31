package dev.theolm.txtlogwriter

import co.touchlab.kermit.Severity

public interface MessageBuilder {
    public fun buildLogMessage(severity: Severity, message: String, tag: String): String
    public fun buildThrowableMessage(throwable: Throwable): String
}

internal class MessageBuilderDefault(
    private val linePrefix: LinePrefix
) : MessageBuilder {
    override fun buildLogMessage(severity: Severity, message: String, tag: String): String {
        return "${getTrimPrefix()}[${severity.name}] ${getTrimTag(tag)}$message\n"
    }

    override fun buildThrowableMessage(throwable: Throwable) =
        "\n${linePrefix.getPrefix()}${throwable.stackTraceToString()}\n"

    private fun getTrimPrefix() =
        if (linePrefix.getPrefix().isEmpty()) "" else "${linePrefix.getPrefix()} "

    private fun getTrimTag(tag: String) = if (tag.isEmpty()) "" else "[$tag] "
}
