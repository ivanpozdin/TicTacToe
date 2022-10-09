package view

import ViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("FunctionNaming")
@Composable
fun MenuScreen(viewModel: ViewModel) = Column{
    Text("Choose game type")
    Button(
        onClick = viewModel::onClickDoMenuPvP,
        modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1f)
    ) {
        Text(text = "Person vs Person")
    }
    Button(
        onClick = viewModel::onClickDoMenuPvAI,
        modifier = Modifier.fillMaxWidth().fillMaxHeight().weight(1f)
    ) {
        Text(text = "Person vs AI")
    }
}
