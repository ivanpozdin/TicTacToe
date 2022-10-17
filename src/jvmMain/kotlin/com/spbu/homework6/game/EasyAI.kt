package com.spbu.homework6.game

import Figure

/**
 * Класс отвечает за режим easyAI, в котором AI делает случайные ходы.
 */
class EasyAI(private val gameBoard: Board) : AI {
    /**
     * Функция совершает случайный ход.
     */
    override fun makeTurnByAI(figure: Figure) {
        if (gameBoard.freeCellsAmount < 1) {
            return
        }
        val cellNumber = (1..gameBoard.freeCellsAmount).random()
        var counter = 0
        for (row in gameBoard.board.indices) {
            for (column in gameBoard.board[row].indices) {
                if (gameBoard.board[row][column] == Figure.Empty) {
                    counter++
                    if (counter == cellNumber) {
                        gameBoard.makeMove(row, column, figure)
                        return
                    }
                }
            }
        }
    }
}
