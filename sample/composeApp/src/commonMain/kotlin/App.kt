import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import co.touchlab.kermit.Logger
import dev.theolm.txtlogwriter.TxtLogWriter
import dev.theolm.txtlogwriter.listLogFiles
import home.HomeScreen

@Composable
fun App() {
    val added = remember { mutableStateOf(false) }
    if (!added.value) {
        Logger.addLogWriter(TxtLogWriter())
        println(listLogFiles())
        added.value = true
    }

    MaterialTheme {
        Navigator(HomeScreen())
    }
}
