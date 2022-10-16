package game

import Figure
import GameResult

class Board {
    companion object {
        const val SIZE = 3
    }

    data class Turn(val row: Int, val column: Int, var score: Int = 0)

    private val board = Array(SIZE) { Array(SIZE) { Figure.Empty } }
    val isGameOverAlready
        get() = isBoardFull || didCrossWin || didNoghtWin

    val gameResult
        get() = when{
            didCrossWin -> GameResult.CrossWon
            didNoghtWin -> GameResult.NoghtWon
            isBoardFull -> GameResult.Tie
            else -> GameResult.NotFinishedYet
        }
    private val didCrossWin
        get() = didWon(board, Figure.Cross)
    private val didNoghtWin
        get() = didWon(board, Figure.Noght)

    private var freeCellsAmount = SIZE * SIZE

    private val isBoardFull
        get() = freeCellsAmount == 0

    fun getFigureInCell(row: Int, column: Int): Figure {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Выход за пределы поля!!!" }
        return board[row][column]
    }

    fun makeMove(row: Int, column: Int, figure: Figure): Boolean {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Выход за пределы поля!!!" }
        if (board[row][column] != Figure.Empty) {
            return false
        }
        board[row][column] = figure
        freeCellsAmount--
        return true
    }

    fun makeMoveByEasyAI(figure: Figure) {
        if (freeCellsAmount < 1) {
            return
        }
        val cellNumber = (1..freeCellsAmount).random()
        var counter = 0
        for (row in board.indices) {
            for (column in board[row].indices) {
                if (board[row][column] == Figure.Empty) {
                    counter++
                    if (counter == cellNumber) {
                        board[row][column] = figure
                        freeCellsAmount--
                        return
                    }
                }
            }
        }
    }

    fun makeMoveByHardAI(figure: Figure) {
        val turn = getBestTurn(board, figure, figure, figure.getNext())
        makeMove(turn.row, turn.column, figure)
    }

    private fun getBestTurn(newBoard: Array<Array<Figure>>, currentFigure: Figure, ourFigure: Figure, opponentFigure: Figure): Turn{
        val availableSpots = getEmptyTurns(newBoard)
        if (didWon(newBoard, opponentFigure)){
            return Turn(0, 0, -10)
        } else if (didWon(newBoard, ourFigure)){
            return Turn(0, 0, 10)
        } else if (availableSpots.isEmpty()) {
            return Turn(0, 0, 0)
        }
        val turnsAndScores = mutableListOf<Turn>()

        for (turn in availableSpots) {
            val tmpBoard = Array(3){newBoard[it].copyOf()}
            tmpBoard[turn.row][turn.column] = currentFigure
            turnsAndScores.add(Turn(turn.row, turn.column, getBestTurn(tmpBoard, if (currentFigure == ourFigure) opponentFigure else ourFigure, ourFigure, opponentFigure).score))
        }
        var bestTurn = Turn(0, 0)
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

    private fun getEmptyTurns(newBoard: Array<Array<Figure>>): List<Turn> {
        val listOfEmptyCells = mutableListOf<Turn>()
        for (row in newBoard.indices){
            for (column in newBoard[row].indices) {
                if (newBoard[row][column] == Figure.Empty) {
                    listOfEmptyCells.add(Turn(row, column))
                }
            }
        }
        return listOfEmptyCells.toList()
    }

    private fun didWon(newBoard: Array<Array<Figure>>, figure: Figure): Boolean {
        return doHaveThreeInRows(newBoard, figure) || doHaveThreeInInColumns(newBoard, figure) || doHaveThreeInDiagonals(newBoard, figure)
    }

    private fun doHaveThreeInRows(newBoard: Array<Array<Figure>>, figure: Figure): Boolean {
        for (row in newBoard) {
            if (row[0] == row[1] && row[1] == row[2] && row[0] == figure) {
                return true
            }
        }
        return false

    }

    private fun doHaveThreeInDiagonals(newBoard: Array<Array<Figure>>, figure: Figure): Boolean {
        return (
                newBoard[0][0] == newBoard[1][1] &&
                        newBoard[0][0] == newBoard[2][2] &&
                        newBoard[0][0] == figure
                ) ||
                (
                        newBoard[0][2] == newBoard[1][1] &&
                                newBoard[0][2] == newBoard[2][0] &&
                                newBoard[0][2] == figure
                        )
    }
    private fun doHaveThreeInInColumns(newBoard: Array<Array<Figure>>, figure: Figure): Boolean {
        for (column in 0 until SIZE) {
            if (newBoard[0][column] == newBoard[1][column] &&
                newBoard[0][column] == newBoard[2][column] &&
                newBoard[0][column] == figure
            ) {
                return true
            }
        }
        return false
    }
}
