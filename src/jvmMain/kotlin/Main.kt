import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import view.View

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "TicTacToe",
        state = rememberWindowState(width = 500.dp, height = 500.dp)
    ) {
        val viewModel = remember { ViewModel() }
        View(viewModel)
    }
}
