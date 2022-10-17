package com.spbu.homework6.view

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spbu.homework6.ViewModel

/**
 * Функция выводит на экран информацию об окончании игры, т.е. кто выиграл.
 */
@Composable
fun GameOverScreen(viewModel: ViewModel) = Button(
    onClick = { viewModel.onClickDoInGameOverScreen() },
    modifier = Modifier.fillMaxWidth().fillMaxHeight()
) {
    val text = when (viewModel.state.gameResult) {
        GameResult.NoghtWon -> "⭕ won!"
        GameResult.CrossWon -> "❌️ won!"
        else -> "It is a tie!"
    }
    Text(text = "$text\nTap to enter menu screen.")
    GameScreen(viewModel, Modifier.aspectRatio(1f))
}

