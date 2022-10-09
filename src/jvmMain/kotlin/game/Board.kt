package game

import Figure

class Board {
    companion object {
        const val SIZE = 3
    }

    private val gameField = Array(SIZE) { Array(SIZE) { Figure.Empty } }
    val isGameOverAlready
        get() = isBoardFull || doBoardHasThreesInARow()

    private var freeCellsAmount = SIZE * SIZE
    private val isBoardFull
        get() = freeCellsAmount == 0

    fun getFigureInCell(row: Int, column: Int): Figure {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Выход за пределы поля!!!" }
        return gameField[row][column]
    }

    fun makeMove(row: Int, column: Int, figure: Figure): Boolean {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Выход за пределы поля!!!" }
        if (gameField[row][column] != Figure.Empty) {
            return false
        }
        gameField[row][column] = figure
        freeCellsAmount--
        return true
    }
    @Suppress("NestedBlockDepth")
    fun makeMoveByEasyAI(figure: Figure) {
        if (freeCellsAmount < 1) {
            return
        }
        val cellNumber = (1..freeCellsAmount).random()
        println("$cellNumber")
        var counter = 0
        for (row in gameField.indices) {
            for (column in gameField[row].indices) {
                if (gameField[row][column] == Figure.Empty) {
                    counter++
                    if (counter == cellNumber) {
                        gameField[row][column] = figure
                        freeCellsAmount--
                        return
                    }
                }
            }
        }
    }

    private fun hasThreeInRows(): Boolean {
        for (row in gameField) {
            if (row[0] == row[1] && row[1] == row[2] && row[0] != Figure.Empty) {
                return true
            }
        }
        return false
    }

    private fun hasThreeInColumns(): Boolean {
        for (column in 0 until SIZE) {
            if (gameField[0][column] == gameField[1][column] &&
                gameField[0][column] == gameField[2][column] &&
                gameField[0][column] != Figure.Empty
            ) {
                return true
            }
        }
        return false
    }

    private fun hasThreeInDiagonals(): Boolean {
        return (
            gameField[0][0] == gameField[1][1] &&
                gameField[0][0] == gameField[2][2] &&
                gameField[0][0] != Figure.Empty
            ) ||
            (
                gameField[0][2] == gameField[1][1] &&
                    gameField[0][2] == gameField[2][0] &&
                    gameField[0][2] != Figure.Empty
                )
    }

    @Suppress("NestedBlockDepth", "ComplexCondition", "ComplexMethod")
    private fun doBoardHasThreesInARow(): Boolean {
        return (hasThreeInRows() || hasThreeInColumns() || hasThreeInDiagonals())
    }
}
