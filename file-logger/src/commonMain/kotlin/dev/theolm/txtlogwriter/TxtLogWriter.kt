package dev.theolm.txtlogwriter

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity


public class TxtLogWriter(
    private val config: LogWriterConfig = LogWriterConfig()
) : LogWriter() {
    private val messageBuilder = config.messageBuilder

    private val fileWriter = initFileWriter()

    override fun isLoggable(tag: String, severity: Severity): Boolean {
        return config.isLoggable(tag, severity)
    }

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        if (!isLoggable(tag, severity)) return

        fileWriter?.let { writer ->
            val logMessage = messageBuilder.buildLogMessage(severity, message, tag)
            writer.writeToFile(logMessage)

            throwable?.let {
                writer.writeToFile(messageBuilder.buildThrowableMessage(it))
            }
        }
    }

    private fun initFileWriter(): FileWriter? {
        val fileDir =
            config.filePath ?: getDefaultFileDir() ?: return null
        val fileName = config.fileName ?: getFileName()

        return FileWriter(fileDir, fileName)
    }
}
