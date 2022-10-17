package com.spbu.homework6.game

import Figure
import GameResult

/**
 * Класс отвечает за игровую логику.
 * В нём хранится игровое поле, метод для совершения ходов, методы для проверки состояния игрового поля.
 */
class Board {
    companion object {
        const val SIZE = 3
    }

    /**
     * Дата класс описывает ход, в том числе оценка, она нужна в для режима hardAI
     */
    data class Turn(val row: Int = 0, val column: Int = 0, var score: Int = 0)

    val board = Array(SIZE) { Array(SIZE) { Figure.Empty } }

    val isGameOverAlready
        get() = isBoardFull || didWon(board, Figure.Cross) || didWon(board, Figure.Noght)

    val gameResult
        get() = when {
            didWon(board, Figure.Cross) -> GameResult.CrossWon
            didWon(board, Figure.Noght) -> GameResult.NoghtWon
            isBoardFull -> GameResult.Tie
            else -> GameResult.NotFinishedYet
        }

    var freeCellsAmount = SIZE * SIZE

    private val isBoardFull
        get() = freeCellsAmount == 0

    fun getFigureInCell(row: Int, column: Int): Figure {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Выход за пределы поля!!!" }
        return board[row][column]
    }

    /**
     * Простая функция, чтобы сделать ход по данным строке, столбцу и фигуре.
     */
    fun makeMove(row: Int, column: Int, figure: Figure): Boolean {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Выход за пределы поля!!!" }
        if (board[row][column] != Figure.Empty) {
            return false
        }
        board[row][column] = figure
        freeCellsAmount--
        return true
    }

    fun didWon(newBoard: Array<Array<Figure>>, figure: Figure): Boolean {
        return doHaveThreeInRows(newBoard, figure) || doHaveThreeInInColumns(
            newBoard,
            figure
        ) || doHaveThreeInDiagonals(newBoard, figure)
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
