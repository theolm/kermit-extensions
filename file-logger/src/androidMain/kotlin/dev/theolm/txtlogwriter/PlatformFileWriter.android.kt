package dev.theolm.txtlogwriter

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

internal actual fun String.writeToFile(filePath: String) {
    var fileOutputStream: FileOutputStream? = null
    try {
        fileOutputStream = FileOutputStream(File(filePath), true)
        fileOutputStream.write(this.toByteArray())
    } catch (e: IOException) {
        println("Error appending to file: ${e.message}")
    } finally {
        fileOutputStream?.close()
    }
}