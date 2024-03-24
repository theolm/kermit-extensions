package dev.theolm.txtlogwriter

import co.touchlab.kermit.Severity

data class LogWriterConfig(
    val isLoggable: (tag: String, severity: Severity) -> Boolean = { _, _ -> true },
    val messageBuilder: MessageBuilder = MessageBuilderImpl(),
)