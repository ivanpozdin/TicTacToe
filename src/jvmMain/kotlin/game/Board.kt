package game

import FigureType

class Board {
    companion object {
        const val SIZE = 3
    }

    val isGameOverAlready
        get() = isBoardFull() || doBoardHasThreesInARow()
    private val gameField = Array<Array<FigureType>>(SIZE) { Array(SIZE) { FigureType.Empty } }

    fun getFigureInCell(row: Int, column: Int): FigureType {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Выход за пределы поля!!!" }
        return gameField[row][column]
    }

    fun makeMove(row: Int, column: Int, figure: FigureType): Boolean {
        require(row in 0..SIZE && column in 0..SIZE) { "Выход за пределы поля!!!" }
        if (gameField[row][column] != FigureType.Empty) {
            return false
        }
        gameField[row][column] = figure
        return true
    }

    private fun isBoardFull(): Boolean {
        for (raw in gameField) {
            for (cell in raw) {
                if (cell == FigureType.Empty) {
                    return false
                }
            }
        }
        return true
    }

    private fun hasThreeInRows(): Boolean {
        for (row in gameField) {
            if (row[0] == row[1] && row[1] == row[2] && row[0] != FigureType.Empty) {
                return true
            }
        }
        return false
    }

    private fun hasThreeInColumns(): Boolean {
        for (column in 0 until SIZE) {
            if (gameField[0][column] == gameField[1][column] &&
                gameField[0][column] == gameField[2][column] &&
                gameField[0][column] != FigureType.Empty
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
                gameField[0][0] != FigureType.Empty
            ) ||
            (
                gameField[0][2] == gameField[1][1] &&
                    gameField[0][2] == gameField[2][0] &&
                    gameField[0][2] != FigureType.Empty
                )
    }

    @Suppress("NestedBlockDepth", "ComplexCondition", "ComplexMethod")
    private fun doBoardHasThreesInARow(): Boolean {
        return (hasThreeInRows() || hasThreeInColumns() || hasThreeInDiagonals())
    }
}
