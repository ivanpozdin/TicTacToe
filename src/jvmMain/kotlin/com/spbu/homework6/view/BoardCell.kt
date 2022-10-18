package com.spbu.homework6.view

import Figure
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spbu.homework6.ViewModel

@ExperimentalAnimationApi
@Composable
fun BoardCell(viewModel: ViewModel, row: Int, column: Int, modifier: Modifier) = Box(modifier) {
    Button(
        onClick = { viewModel.onClickDoInCell(row, column) },
        modifier = Modifier.fillMaxSize(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = CutCornerShape(0.dp)
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
