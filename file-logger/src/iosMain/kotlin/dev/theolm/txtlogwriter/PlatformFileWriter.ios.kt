package dev.theolm.txtlogwriter

import platform.Foundation.NSFileHandle
import platform.Foundation.NSFileManager
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.dataUsingEncoding
import platform.Foundation.fileHandleForWritingAtPath
import platform.Foundation.seekToEndOfFile
import platform.Foundation.writeData
import platform.Foundation.writeToFile

@Suppress("CAST_NEVER_SUCCEEDS")
internal actual fun String.writeToFile(filePath: String) {
    val fileManager = NSFileManager.defaultManager()
    if (!fileManager.fileExistsAtPath(filePath)) {
        val text = this as NSString
        text.writeToFile(filePath, true)
    } else {
        NSFileHandle.fileHandleForWritingAtPath(filePath)?.let {
            it.seekToEndOfFile()
            val data = (this as NSString).dataUsingEncoding(NSUTF8StringEncoding)
            if (data != null) {
                it.writeData(data)
            }
        }
    }
}