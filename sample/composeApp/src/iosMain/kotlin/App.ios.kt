import androidx.compose.runtime.Composable
import co.touchlab.kermit.Logger
import dev.theolm.txtlogwriter.TxtLogWriter
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

@Composable
actual fun AddLogWriter() {
    val paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
    val documentsDirectory = paths[0] as String
    val path = "$documentsDirectory/$TestFile"

    val file = readFile(path)
    println("File contents: $file")

    Logger.addLogWriter(TxtLogWriter(path))
}