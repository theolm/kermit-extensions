package dev.theolm.txtlogwriter

import java.io.File
import java.io.FileOutputStream
import java.io.IOException


internal actual fun getInternalDir(): String? =
    runCatching { applicationContext.filesDir.absolutePath }.getOrNull()

internal actual class FileWriter actual constructor(
    private val fileDir: String,
    private val fileName: String
) {
    private val file = initFile()

    internal actual fun writeToFile(text: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file, true)
            fileOutputStream.write(text.toByteArray())
        } catch (e: IOException) {
            println("Error appending to file: ${e.message}")
        } finally {
            fileOutputStream?.close()
        }
    }

    private fun initFile(): File {
        val dir = File(fileDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        return File(fileDir + fileName)
    }
}

public actual fun listLogFiles(dir: String): List<String> {
    return File(dir).listFiles()?.filter { it.path.endsWith(FileExt) }?.map { it.name }
        ?: emptyList()
}