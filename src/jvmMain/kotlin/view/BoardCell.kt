package view

import Figure
import ViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@ExperimentalAnimationApi
@Suppress("FunctionNaming")
@Composable
fun BoardCell(viewModel: ViewModel, row: Int, column: Int, modifier: Modifier) = Box(modifier) {
    Button(
        onClick = { viewModel.onClickDoInCell(row, column) },
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        AnimatedVisibility(
            visible = viewModel.state.board.getFigureInCell(row, column) != Figure.Empty,
            enter = slideInHorizontally() + expandHorizontally(expandFrom = Alignment.End) +
                fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }) +
                shrinkHorizontally() + fadeOut(),
        ) {
            Image(painterResource(viewModel.state.board.getFigureInCell(row, column).path), "")
        }
    }
}
