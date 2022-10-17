package com.spbu.homework6.view

import Screen
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.spbu.homework6.ViewModel

/**
 * Функция, которая запускает разные экраны(меню, игра, com.spbu.homework6.game over) в зависимости от состояния.
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
