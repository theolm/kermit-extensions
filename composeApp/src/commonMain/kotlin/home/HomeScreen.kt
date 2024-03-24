package home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        Screen()
    }

    @Composable
    private fun Screen() {
        var counter by remember { mutableStateOf(0) }
        Scaffold {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Test log writer")
                Button(
                    onClick = {
                        Logger.d("Test log writer: $counter")
                        counter++
                    }
                ) {
                    Text("Write Log")
                }
            }
        }
    }
}