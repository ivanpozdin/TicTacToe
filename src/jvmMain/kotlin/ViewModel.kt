import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import game.Board
import java.util.*
import kotlin.concurrent.schedule

const val DELAY_TIME = 400L
class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val screen: Screen = Screen.MenuScreen,
        var board: Board = Board(),
        var whoGoFigure: Figure = Figure.Cross,
        val player1: Player = Player.Human,
        val player2: Player = Player.Human,
        var mayPlayerGo: Boolean = false,
        var gameResult: GameResult = GameResult.Tie
    )

    private fun initialState(): State = State()
    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onClickDoMenuPvP() = updateState { copy(screen = Screen.GameScreen) }
    fun onClickDoMenuPvAI() = updateState { copy(screen = Screen.GameScreen) }

    private fun makeMovePvP(row: Int, column: Int) = updateState {
        board.makeMove(row, column, state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen, gameResult = board.gameResult)
        } else {
            copy(board = board, whoGoFigure = whoGoFigure.getNext())
        }
    }

    private fun makeMovePvEasyAI() = updateState {
        board.makeMoveByEasyAI(state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen,gameResult = board.gameResult)
        } else {
            copy(board = board, whoGoFigure = whoGoFigure.getNext())
        }
    }

    private fun makeMovePvHardAI() = updateState {
        board.makeMoveByHardAI(state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen, gameResult = board.gameResult)
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
        } else if (state.player1 == Player.HardAI || state.player2 == Player.HardAI) {
            state.mayPlayerGo = false
            Timer("SettingUp", false).schedule(DELAY_TIME) {
                makeMovePvHardAI()
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
        if (state.player1 in listOf(Player.EasyAI, Player.HardAI) && state.player2 in listOf(Player.EasyAI, Player.HardAI)) {
            return
        }
        updateState { copy(screen = Screen.GameScreen) }
        when (state.player1) {
            Player.EasyAI -> {
                Timer("SettingUp", false).schedule(DELAY_TIME) {
                    makeMovePvEasyAI()
                    state.mayPlayerGo = true
                }
            }
            Player.HardAI -> {
                Timer("SettingUp", false).schedule(DELAY_TIME) {
                    makeMovePvHardAI()
                    state.mayPlayerGo = true
                }
            }
            else -> {
                state.mayPlayerGo = true
            }
        }
    }
}