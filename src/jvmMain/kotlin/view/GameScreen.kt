package view

import ViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("FunctionNaming")
@Composable
fun GameScreen(viewModel: ViewModel) {
    Column(Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            BoardCell(viewModel, 0, 0, Modifier.weight(1f))
            BoardCell(viewModel, 0, 1, Modifier.weight(1f))
            BoardCell(viewModel, 0, 2, Modifier.weight(1f))
        }

        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            BoardCell(viewModel, 1, 0, Modifier.weight(1f))
            BoardCell(viewModel, 1, 1, Modifier.weight(1f))
            BoardCell(viewModel, 1, 2, Modifier.weight(1f))
        }

        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            BoardCell(viewModel, 2, 0, Modifier.weight(1f))
            BoardCell(viewModel, 2, 1, Modifier.weight(1f))
            BoardCell(viewModel, 2, 2, Modifier.weight(1f))
        }
    }
}
