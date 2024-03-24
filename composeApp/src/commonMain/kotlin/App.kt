import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import home.HomeScreen


const val TestFile = "testFile.txt"

@Composable
fun App() {
    val added = remember { mutableStateOf(false) }
    if (!added.value) {
        AddLogWriter()
        added.value = true
    }

    MaterialTheme {
        Navigator(HomeScreen())
    }
}

@Composable
expect fun AddLogWriter()
