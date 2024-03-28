import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.Navigator
import co.touchlab.kermit.Logger
import dev.theolm.txtlogwriter.TxtLogWriter
import dev.theolm.txtlogwriter.listLogFiles
import home.HomeScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun App() {
    val canWrite = remember { mutableStateOf(false) }
    val added = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (!added.value) {
        Logger.addLogWriter(
            TxtLogWriter(
                config = TxtLogWriter.Config(
                    canWrite = { _, _ -> canWrite.value }
                )
            )
        )
        println(listLogFiles())
        added.value = true

        scope.launch {
            delay(5000)
            canWrite.value = true
        }
    }

    LaunchedEffect(Unit) {
        scope.launch {
            repeat(100) {
                Logger.d("Test log writer: $it")
                delay(500)
            }
        }
    }

    MaterialTheme {
        Navigator(HomeScreen())
    }
}
