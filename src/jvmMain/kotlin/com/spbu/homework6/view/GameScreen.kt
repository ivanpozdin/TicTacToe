package com.spbu.homework6.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spbu.homework6.ViewModel

/**
 * Функция отображает экран непосредственно игры.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen(viewModel: ViewModel, modifier: Modifier = Modifier) = Box(modifier) {
    Column(Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BoardCell(viewModel, 0, 0, Modifier.weight(1f))
            BoardCell(viewModel, 0, 1, Modifier.weight(1f))
            BoardCell(viewModel, 0, 2, Modifier.weight(1f))
        }

        Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BoardCell(viewModel, 1, 0, Modifier.weight(1f))
            BoardCell(viewModel, 1, 1, Modifier.weight(1f))
            BoardCell(viewModel, 1, 2, Modifier.weight(1f))
        }

        Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BoardCell(viewModel, 2, 0, Modifier.weight(1f))
            BoardCell(viewModel, 2, 1, Modifier.weight(1f))
            BoardCell(viewModel, 2, 2, Modifier.weight(1f))
        }
    }
}
