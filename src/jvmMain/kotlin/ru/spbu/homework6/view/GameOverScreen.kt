package ru.spbu.homework6.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.spbu.homework6.GameResult
import ru.spbu.homework6.ViewModel

/**
 * Функция выводит на экран информацию об окончании игры, т.е. кто выиграл.
 */
@Composable
fun GameOverScreen(viewModel: ViewModel) = Button(
    onClick = { viewModel.onClickDoInGameOverScreen() },
    modifier = Modifier.fillMaxWidth().fillMaxHeight()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val text = when (viewModel.state.gameResult) {
            GameResult.NoghtWon -> "⭕ won!"
            GameResult.CrossWon -> "❌️ won!"
            else -> "It is a tie!"
        }
        Text(text = "$text\nTap to enter menu screen.", modifier = Modifier.weight(1f))
        GameScreen(viewModel, Modifier.aspectRatio(1f).weight(2f))
    }
}

