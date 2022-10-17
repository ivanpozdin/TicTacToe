package com.spbu.homework6.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.spbu.homework6.ViewModel

/**
 * Отображает экран игрового меню.
 */
@Composable
fun MenuScreen(viewModel: ViewModel) {
    Column {
        Text(text = "Choose game options:")
        Row {
            Button(onClick = { viewModel.onClickDoInPlayer1Button() }) { Text(viewModel.state.player1Name.name) }
            Button(onClick = { viewModel.onClickDoInPlayerSwitchButton() }) { Text("↔️") }
            Button(onClick = { viewModel.onClickDoInPlayer2Button() }) { Text(viewModel.state.player2Name.name) }
        }
        Button(onClick = { viewModel.onClickStartGame() }) { Text("Start game") }
        Text(text = "P.S. You can't choose two AI options at the same time.")
    }
}
