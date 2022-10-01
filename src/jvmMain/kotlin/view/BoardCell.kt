package view

import ViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Suppress("FunctionNaming")
@Composable
fun BoardCell(viewModel: ViewModel, row: Int, column: Int, modifier: Modifier) = Box(modifier) {
    Button(
        onClick = { viewModel.onClickDoInCell(row, column) },
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Image(painterResource(viewModel.state.board.getFigureInCell(row, column).path), "")
    }
}
