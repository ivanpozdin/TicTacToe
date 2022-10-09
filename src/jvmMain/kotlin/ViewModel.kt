import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import game.Board

@Suppress("TooManyFunctions")
class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val screen: Screen = Screen.MenuScreen,
        var board: Board = Board(),
        var whoGoFigure: Figure = Figure.Cross,
        val game: Game = Game.PersonVSPerson,
        val player1: Player = Player.Player,
        val player2: Player = Player.Player
    )
    private fun initialState(): State = State()
    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onClickDoMenuPvP() = updateState { copy(screen = Screen.GameScreen, game = Game.PersonVSPerson) }
    fun onClickDoMenuPvAI() = updateState { copy(screen = Screen.GameScreen, game = Game.PersonVSEasyAI) }

    private fun makeMovePvP(row: Int, column: Int) = updateState {
        board.makeMove(row, column, state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen)
        } else {
            copy(board = board, whoGoFigure = whoGoFigure.getNext())
        }
    }

    private fun makeMovePvEasyAI() = updateState {
        board.makeMoveByEasyAI(state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen)
        } else {
            copy(board = board, whoGoFigure = whoGoFigure.getNext())
        }
    }

    fun onClickDoInCell(row: Int, column: Int) {
        makeMovePvP(row, column)
        if (state.game == Game.PersonVSEasyAI) {
            makeMovePvEasyAI()
        }
    }

    fun onClickDoInGameOverScreen() = updateState {
        initialState()
    }
    fun onClickDoInPlayer1Button() = updateState {
        copy()
    }
    fun onClickDoInPlayer2Button() = updateState {
        copy()
    }
    fun onClickDoInPlayerSwitchButton() = updateState {
        copy()
    }
}
