package view

import Figure
import ViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun GameOverScreen(viewModel: ViewModel) = Button(
    onClick = { viewModel.onClickDoInGameOverScreen() },
    modifier = Modifier.fillMaxWidth().fillMaxHeight()
) {
    Text(text = "Game over!\nTap to enter menu screen.")

    if (viewModel.state.gameResult == GameResult.Tie) {
        Column {
            Text(text = "It is a tie!", modifier = Modifier.weight(1f))
            Image(painterResource(Figure.Cross.path), "", modifier = Modifier.weight(3f))
            Image(painterResource(Figure.Noght.path), "", modifier = Modifier.weight(3f))

        }
    } else {

        val image = when (viewModel.state.gameResult) {
            GameResult.CrossWon -> Figure.Cross.path
            GameResult.NoghtWon -> Figure.Noght.path
            else -> Figure.Empty.path
        }
        Column {
            Image(painterResource(image), "")
            Text("won.")
        }
    }
}
