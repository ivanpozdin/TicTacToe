package view

import ViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Suppress("FunctionNaming")
@Composable
fun GameOverScreen(viewModel: ViewModel) = Button(
    onClick = { viewModel.onClickDoInGameOverScreen() },
    modifier = Modifier.fillMaxWidth().fillMaxHeight()
) {
    Text(text = "Game over!\nTap to enter menu screen.")
    Image(painterResource(viewModel.state.whoGoFigure.path), "")
}
