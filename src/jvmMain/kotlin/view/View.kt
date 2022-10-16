package view

import Screen
import ViewModel
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state
    MaterialTheme {
        when (state.screen) {
            Screen.MenuScreen -> Menu(viewModel)
            Screen.GameScreen -> GameScreen((viewModel))
            Screen.GameOverScreen -> GameOverScreen(viewModel)
        }
    }
}
