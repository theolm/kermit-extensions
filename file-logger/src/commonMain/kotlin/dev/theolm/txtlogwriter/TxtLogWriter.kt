package dev.theolm.txtlogwriter

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.Severity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


/**
 * Kermit Log writer that writes log messages to a text file.
 * @param config: Configuration for the log writer. Default is [TxtLogWriter.Config].
 *
 * Example:
 * Logger.addLogWriter(TxtLogWriter())
 */
public class TxtLogWriter(
    private val config: Config = Config()
) : LogWriter() {
    private val logWriterScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val messageBuilder = config.customMessageBuilder ?: MessageBuilderDefault(config.linePrefix)
    private val fileWriter by lazy { initFileWriter() }

    override fun isLoggable(tag: String, severity: Severity): Boolean {
        return config.canWrite(tag, severity)
    }

    override fun log(severity: Severity, message: String, tag: String, throwable: Throwable?) {
        if (!isLoggable(tag, severity)) return

        logWriterScope.launch {
            fileWriter?.let { writer ->
                val logMessage = messageBuilder.buildLogMessage(severity, message, tag)
                writer.writeToFile(logMessage)

                throwable?.let {
                    writer.writeToFile(messageBuilder.buildThrowableMessage(it))
                }
            }
        }
    }

    private fun initFileWriter(): FileWriter? {
        val fileDir =
            config.filePath ?: getDefaultFileDir() ?: return null
        val fileName = config.fileName ?: getFileName()

        return FileWriter(fileDir, fileName)
    }

    /**
     * Configuration for [TxtLogWriter]
     * @param filePath: Path to the directory where the log file will be saved, if null the default
     * directory will be used.
     * @param fileName: Name of the log file, if null the default name will be used (current date).
     * @param canWrite: Function that determines if a log message should be written to the file.
     * Useful for filtering logs and delay the creation of the log file.
     * @param customMessageBuilder: Builder for the log message. Default is [MessageBuilderDefault].
     */
    public data class Config(
        val filePath: String? = null,
        val fileName: String? = null,
        val linePrefix: LinePrefix = LinePrefix.DateTime,
        val canWrite: (tag: String, severity: Severity) -> Boolean = { _, _ -> true },
        val customMessageBuilder: MessageBuilder? = null,
    )
}
