package game

import Figure

/**
 * Класс отвечает за режим hardAI, в котором AI делает продуманные ходы.
 */
class HardAI(private val gameBoard: Board) : AI {
    /**
     * Функция совершает продуманный ход.
     */
    override fun makeTurnByAI(figure: Figure) {
        val turn = getBestTurn(gameBoard.board, figure, figure, figure.getNext())
        gameBoard.makeMove(turn.row, turn.column, figure)
    }

    private fun getBestTurn(
        newBoard: Array<Array<Figure>>,
        currentFigure: Figure,
        ourFigure: Figure,
        opponentFigure: Figure
    ): Board.Turn {
        val availableSpots = getEmptyTurns(newBoard)
        if (gameBoard.didWon(newBoard, opponentFigure)) {
            return Board.Turn(0, 0, -10)
        } else if (gameBoard.didWon(newBoard, ourFigure)) {
            return Board.Turn(0, 0, 10)
        } else if (availableSpots.isEmpty()) {
            return Board.Turn(0, 0, 0)
        }
        val turnsAndScores = mutableListOf<Board.Turn>()

        for (turn in availableSpots) {
            val tmpBoard = Array(3) { newBoard[it].copyOf() }
            tmpBoard[turn.row][turn.column] = currentFigure
            turnsAndScores.add(
                Board.Turn(
                    turn.row,
                    turn.column,
                    getBestTurn(
                        tmpBoard,
                        if (currentFigure == ourFigure) opponentFigure else ourFigure,
                        ourFigure,
                        opponentFigure
                    ).score
                )
            )
        }
        var bestTurn = Board.Turn(0, 0)
        if (currentFigure == ourFigure) {
            var bestScore = -10000
            for (turn in turnsAndScores) {
                if (turn.score > bestScore) {
                    bestScore = turn.score
                    bestTurn = turn
                }
            }
        } else {
            var bestScore = 10000
            for (turn in turnsAndScores) {
                if (turn.score < bestScore) {
                    bestScore = turn.score
                    bestTurn = turn
                }
            }
        }
        return bestTurn
    }

    private fun getEmptyTurns(newBoard: Array<Array<Figure>>): List<Board.Turn> {
        val listOfEmptyCells = mutableListOf<Board.Turn>()
        for (row in newBoard.indices) {
            for (column in newBoard[row].indices) {
                if (newBoard[row][column] == Figure.Empty) {
                    listOfEmptyCells.add(Board.Turn(row, column))
                }
            }
        }
        return listOfEmptyCells.toList()
    }
}
