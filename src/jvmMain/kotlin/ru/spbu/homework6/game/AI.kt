package ru.spbu.homework6.game

import ru.spbu.homework6.Figure

/**
 * Интерфейс AI для игры в крестики нолики, имеет описание одной функции: сделать ход.
 */
interface AI {
    /**
     * Функция совержает ход данной фигурой.
     */
    fun makeTurnByAI(figure: Figure)
}
