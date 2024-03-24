package dev.theolm.filelogwriter

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity


class FileLogWriter(
    private val filePath: String,
    private val config: LogWriterConfig = LogWriterConfig()
) : LogWriter() {
    private val messageBuilder = config.messageBuilder
    override fun isLoggable(tag: String, severity: Severity): Boolean {
        return config.isLoggable(tag, severity)
    }

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        if (!isLoggable(tag, severity)) return

        val logMessage = messageBuilder.buildLogMessage(severity, message, tag)
        logMessage.writeToFile(filePath)

        throwable?.let {
            messageBuilder.buildThrowableMessage(it).writeToFile(filePath)
        }
    }
}
