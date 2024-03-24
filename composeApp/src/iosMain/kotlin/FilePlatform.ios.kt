import platform.Foundation.NSString
import platform.Foundation.create


actual fun readFile(filePath: String): String {
    return try {
        val text = NSString.create(contentsOfFile = filePath)
        text.toString()
    } catch (e: Exception) {
        "Error reading file: $e"
    }
}



