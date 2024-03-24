import java.io.File
import java.io.IOException

actual fun readFile(filePath: String): String {
    return try {
        File(filePath).readText()
    } catch (e: IOException) {
        e.printStackTrace()
        "Error reading file"
    }
}