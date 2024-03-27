package dev.theolm.txtlogwriter

import co.touchlab.kermit.Severity

public data class LogWriterConfig(
    val filePath: String? = null,
    val fileName: String? = null,
    val isLoggable: (tag: String, severity: Severity) -> Boolean = { _, _ -> true },
    val messageBuilder: MessageBuilder = MessageBuilderDefault(),
)
