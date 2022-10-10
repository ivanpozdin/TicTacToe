import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import game.Board
import java.util.*
import kotlin.concurrent.schedule

const val DELAY_TIME = 400L
@Suppress("TooManyFunctions")
class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val screen: Screen = Screen.MenuScreen,
        var board: Board = Board(),
        var whoGoFigure: Figure = Figure.Cross,
        val game: Game = Game.PersonVSPerson,
        val player1: Player = Player.Human,
        val player2: Player = Player.Human,
        var mayPlayerGo: Boolean = false
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
        if (!state.mayPlayerGo) {
            return
        }
        makeMovePvP(row, column)

        if (state.player1 == Player.EasyAI || state.player2 == Player.EasyAI) {
            state.mayPlayerGo = false
            Timer("SettingUp", false).schedule(DELAY_TIME) {
                makeMovePvEasyAI()
                state.mayPlayerGo = true
            }
        }
    }

    fun onClickDoInGameOverScreen() = updateState {
        initialState()
    }

    fun onClickDoInPlayer1Button() = updateState {
        copy(
            player1 = when (player1) {
                Player.Human -> Player.EasyAI
                Player.EasyAI -> Player.HardAI
                Player.HardAI -> Player.Human
            }
        )
    }

    fun onClickDoInPlayer2Button() = updateState {
        copy(
            player2 = when (player2) {
                Player.Human -> Player.EasyAI
                Player.EasyAI -> Player.HardAI
                Player.HardAI -> Player.Human
            }
        )
    }

    fun onClickDoInPlayerSwitchButton() = updateState {
        copy(player1 = player2, player2 = player1)
    }

    fun onClickStartGame() {
        updateState { copy(screen = Screen.GameScreen) }
        if (state.player1 == Player.EasyAI) {
            Timer("SettingUp", false).schedule(DELAY_TIME) {
                makeMovePvEasyAI()
                state.mayPlayerGo = true
            }
        } else {
            state.mayPlayerGo = true
        }
    }
}
