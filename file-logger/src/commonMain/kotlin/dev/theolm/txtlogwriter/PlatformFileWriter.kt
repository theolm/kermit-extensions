package dev.theolm.txtlogwriter

import kotlinx.datetime.Clock

internal const val LogDir = "/logs/"
internal const val FileExt = ".txt"

public fun getDefaultFileDir(): String = getInternalDir() + LogDir

public expect fun listLogFiles(dir: String = getDefaultFileDir()): List<String>

internal expect class FileWriter(
    fileDir: String,
    fileName: String
) {
    internal fun writeToFile(text: String)
}

internal expect fun getInternalDir(): String

internal fun getFileName(): String {
    val date = Clock.System.now()
    return date.toString() + FileExt
}

