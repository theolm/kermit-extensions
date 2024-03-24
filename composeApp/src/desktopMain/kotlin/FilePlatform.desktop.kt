import java.io.File

actual fun readFile(filePath: String): String {
    return try {
        File(filePath).readText(Charsets.UTF_8)
    } catch (e: Exception) {
        "Error reading file: ${e.message}"
    }
}