import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import game.Board

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val screen: Screen,
        var board: Board,
        var whoGoFigure: FigureType
    )

    private fun initialState(): State = State(Screen.MenuScreen, Board(), FigureType.Cross)
    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onClickDoInMenu() = updateState { copy(screen = Screen.GameScreen) }

    fun onClickDoInCell(raw: Int, column: Int) = updateState {
        board.makeMove(raw, column, state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen)
        } else {
            copy(board = board, whoGoFigure = whoGoFigure.getNext())
        }
    }

    fun onClickDoInGameOverScreen() = updateState {
        initialState()
    }
}
