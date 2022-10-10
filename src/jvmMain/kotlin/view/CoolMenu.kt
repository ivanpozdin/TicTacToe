package view

import ViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Suppress("FunctionNaming")
@Composable
fun CoolMenu(viewModel: ViewModel) {
    Column {
        Row {
            Button(onClick = { viewModel.onClickDoInPlayer1Button() }) { Text(viewModel.state.player1.name) }
            Button(onClick = { viewModel.onClickDoInPlayerSwitchButton() }) { Text("↔️") }
            Button(onClick = { viewModel.onClickDoInPlayer2Button() }) { Text(viewModel.state.player2.name) }
        }
        Button(onClick = { viewModel.onClickStartGame() }) { Text("Start game") }
    }
}
