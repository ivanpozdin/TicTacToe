import androidx.compose.runtime.*
import game.AI
import game.Board
import game.EasyAI
import game.HardAI
import java.util.*
import kotlin.concurrent.schedule

const val DELAY_TIME = 400L

/**
 * Класс, отвечающий за связь между ui и бизнес логикой программы (Board)
 */
class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    /**
     * Дата-класс, отвечающий за состояние приложения. Если какая-то функция изменила state,
     * то вследствии этого может изменится view, т.е. ui.
     */
    data class State(
        val screen: Screen = Screen.MenuScreen,
        var board: Board = Board(),
        var whoGoFigure: Figure = Figure.Cross,
        val player1AI: AI? = null,
        val player2AI: AI? = null,
        val player1Name: Player = Player.Human,
        val player2Name: Player = Player.Human,
        var mayPlayerGo: Boolean = false,
        var gameResult: GameResult = GameResult.Tie,
        var ai: AI = EasyAI(board)
    )

    private fun initialState(): State = State()

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    private fun makeMoveByHuman(row: Int, column: Int) = updateState {
        board.makeMove(row, column, state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen, gameResult = board.gameResult)
        } else {
            copy(board = board, whoGoFigure = whoGoFigure.getNext())
        }
    }

    private fun makeMoveByAI() = updateState {
        ai.makeTurnByAI(state.whoGoFigure)
        if (board.isGameOverAlready) {
            copy(screen = Screen.GameOverScreen, gameResult = board.gameResult)
        } else {
            copy(board = board, whoGoFigure = whoGoFigure.getNext())
        }
    }

    /**
     * Если нажали на клетку игрового поля, запускается эта функция.
     * Она меняет пустую клетку на крестик или нолик, и если включен режим с ботом запускает ход бота.
     */
    fun onClickDoInCell(row: Int, column: Int) {
        if (!state.mayPlayerGo) {
            return
        }
        makeMoveByHuman(row, column)

        if (state.player1AI != null || state.player2AI != null) {
            state.mayPlayerGo = false
            Timer("SettingUp", false).schedule(DELAY_TIME) {
                makeMoveByAI()
                state.mayPlayerGo = true
            }
        }
    }

    /**
     * Очищает данные прошлой игры.
     */
    fun onClickDoInGameOverScreen() = updateState {
        initialState()
    }

    /**
     * Функция меняет режим того, кто первым сделает ход.
     */
    fun onClickDoInPlayer1Button() = updateState {
        copy(
            player1AI = when (player1AI) {
                EasyAI(board) -> HardAI(board)
                HardAI(board) -> null
                else -> EasyAI(board)
            },
            player1Name = when (player1Name) {
                Player.Human -> Player.EasyAI
                Player.EasyAI -> Player.HardAI
                Player.HardAI -> Player.Human
            }


        )
    }

    /**
     * Функция меняет режим того, кто вторрым сделает ход.
     */
    fun onClickDoInPlayer2Button() = updateState {
        copy(
            player2AI = when (player2AI) {
                EasyAI(board) -> HardAI(board)
                HardAI(board) -> null
                else -> EasyAI(board)
            },
            player2Name = when (player2Name) {
                Player.Human -> Player.EasyAI
                Player.EasyAI -> Player.HardAI
                Player.HardAI -> Player.Human
            }
        )
    }

    /**
     * Функция меняет местами режимы игры.
     */
    fun onClickDoInPlayerSwitchButton() = updateState {
        copy(player1AI = player2AI, player2AI = player1AI, player1Name = player2Name, player2Name = player1Name)
    }

    /**
     * Функция начинает игру, если пользователь нажал на кнопу start game.
     * Причём не начинает, если выбраны сразу 2 режима с AI.
     */
    fun onClickStartGame() {
        if (state.player1AI != null && state.player2AI != null) {
            return
        }
        updateState { copy(screen = Screen.GameScreen) }
        if (state.player1AI != null) {
            Timer("SettingUp", false).schedule(DELAY_TIME) {
                makeMoveByAI()
                state.mayPlayerGo = true
            }
        } else {
            state.mayPlayerGo = true
        }
    }
}
