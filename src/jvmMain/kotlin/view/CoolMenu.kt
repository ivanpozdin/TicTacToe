package view

import ViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.runtime.Composable

@Suppress("FunctionNaming")
@Composable
fun CoolMenu(viewModel: ViewModel) {
    Row {
        Button(onClick = {viewModel.onClickDoInPlayer1Button()}) {}
        Button(onClick = {viewModel.onClickDoInPlayerSwitchButton()} ) {}
        Button(onClick = {viewModel.onClickDoInPlayer2Button()}) {}
    }
}
