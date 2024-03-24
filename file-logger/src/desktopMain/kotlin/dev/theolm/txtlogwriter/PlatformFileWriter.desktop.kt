package dev.theolm.txtlogwriter

import java.io.File
import java.io.IOException

internal actual fun String.writeToFile(filePath: String) {
    try {
        val file = File(filePath)
        file.appendText(this)
    } catch (e: IOException) {
        println("Error writing to file: ${e.message}")
    }
}