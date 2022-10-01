package view

import ViewModel
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("FunctionNaming")
@Composable
fun MenuScreen(viewModel: ViewModel) = Button(
    onClick = viewModel::onClickDoInMenu,
    modifier = Modifier.fillMaxWidth().fillMaxHeight()
) {
    Text(text = "Start game")
}
