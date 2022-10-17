package com.spbu.homework6.game

import Figure

/**
 * Интерфейс AI для игры в крестики нолики, имеет описание одной функции: сделать ход.
 */
interface AI {
    fun makeTurnByAI(figure: Figure)
}
