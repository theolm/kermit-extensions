package dev.theolm.txtlogwriter

import java.io.File


internal actual fun getInternalDir(): String? =
    runCatching { System.getProperty("user.dir") }.getOrNull()

internal actual class FileWriter actual constructor(
    private val fileDir: String,
    private val fileName: String
) {
    private val file = initFile()

    internal actual fun writeToFile(text: String) {
        file?.appendText(text)
    }

    private fun initFile(): File? = runCatching {
        val dir = File(fileDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val file = File(fileDir + fileName)
        file.createNewFile()
        file
    }.getOrNull()
}

public actual fun listLogFiles(dir: String): List<String> {
    return File(dir).listFiles { _, name ->
        name.endsWith(FileExt)
    }?.let {
        it.map { file -> file.name }
    }.orEmpty()
}