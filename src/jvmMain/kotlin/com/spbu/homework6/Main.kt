import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.spbu.homework6.ViewModel
import com.spbu.homework6.view.View

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "TicTacToe", resizable = false,
        state = rememberWindowState(width = 600.dp, height = 600.dp)
    ) {
        val viewModel = remember { ViewModel() }
        View(viewModel)
    }
}
