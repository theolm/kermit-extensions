import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import co.touchlab.kermit.Logger
import dev.theolm.filelogwriter.FileLogWriter

@Composable
actual fun AddLogWriter() {
    val added = remember { mutableStateOf(false) }
    if(!added.value) {
        val context = LocalContext.current
        val filePath = "${context.filesDir.absolutePath}/$TestFile"
        println(readFile(filePath))
        Logger.addLogWriter(FileLogWriter(filePath))
    }
}