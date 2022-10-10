package view

import Screen
import ViewModel
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Suppress("FunctionNaming")
@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state
    MaterialTheme {
        when (state.screen) {
//            Screen.MenuScreen -> MenuScreen(viewModel)
            Screen.MenuScreen -> CoolMenu(viewModel)
            Screen.GameScreen -> GameScreen((viewModel))
            Screen.GameOverScreen -> GameOverScreen(viewModel)
        }
    }
}
