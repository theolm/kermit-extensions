package dev.theolm.txtlogwriter

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileHandle
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.NSUserDomainMask
import platform.Foundation.closeFile
import platform.Foundation.dataUsingEncoding
import platform.Foundation.fileHandleForWritingAtPath
import platform.Foundation.seekToEndOfFile
import platform.Foundation.writeData
import platform.Foundation.writeToFile

internal actual fun getInternalDir(): String? = runCatching {
    val paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
    (paths.first() as String)
}.getOrNull()

@OptIn(ExperimentalForeignApi::class)
@Suppress("CAST_NEVER_SUCCEEDS", "TooGenericExceptionCaught")
internal actual class FileWriter actual constructor(
    fileDir: String,
    fileName: String
) {
    private val file = fileDir + fileName
    private val fileManager = NSFileManager.defaultManager()

    init {
        if (!fileManager.fileExistsAtPath(fileDir)) {
            try {
                fileManager.createDirectoryAtURL(
                    NSURL.fileURLWithPath(fileDir),
                    withIntermediateDirectories = true,
                    attributes = null,
                    error = null
                )
            } catch (e: Exception) {
                println("Could not create directory: ${e.message}")
            }
        }
    }

    internal actual fun writeToFile(text: String) {
        val textToWrite = text as NSString
        if (!fileManager.fileExistsAtPath(file)) {
            textToWrite.writeToFile(
                file,
                atomically = true,
                encoding = NSUTF8StringEncoding,
                error = null
            )
        } else {
            NSFileHandle.fileHandleForWritingAtPath(file)?.let {
                it.seekToEndOfFile()
                val data = textToWrite.dataUsingEncoding(NSUTF8StringEncoding)
                if (data != null) {
                    it.writeData(data)
                }
                it.closeFile()
            }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
public actual fun listLogFiles(dir: String): List<String> {
    val fileManager = NSFileManager.defaultManager()
    return fileManager.contentsOfDirectoryAtPath(dir, null)?.map {
        it as String
    }?.filter {
        it.endsWith(FileExt)
    }.orEmpty()
}