import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import co.touchlab.kermit.Logger
import dev.theolm.filelogwriter.FileLogWriter



@Composable
actual fun AddLogWriter() {
    val added = remember { mutableStateOf(false) }
    if(!added.value) {
        val currentWorkingDir = System.getProperty("user.dir")
        val filePath = "$currentWorkingDir/$TestFile"
        println(readFile(filePath))
        Logger.addLogWriter(FileLogWriter(filePath))
    }
}