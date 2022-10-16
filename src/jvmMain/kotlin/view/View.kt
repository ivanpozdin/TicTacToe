package view

import Screen
import ViewModel
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Функция, которая запускает разные экраны(меню, игра, game over) в зависимости от состояния.
 */
@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state
    MaterialTheme {
        when (state.screen) {
            Screen.MenuScreen -> MenuScreen(viewModel)
            Screen.GameScreen -> GameScreen((viewModel))
            Screen.GameOverScreen -> GameOverScreen(viewModel)
        }
    }
}
