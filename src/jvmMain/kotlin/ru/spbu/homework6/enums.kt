package ru.spbu.homework6
enum class Screen {
    MenuScreen, GameScreen, GameOverScreen
}

/**
 * Enum содержит виды фигур, пути к их изображениям.
 */
enum class Figure(val path: String) {
    Cross("cross.png"), Noght("noght.png"), Empty("empty.png");

    /**
     * Функция получает следующию по ходу игры фигуру.
     */
    fun getNext(): Figure {
        return when (this) {
            Cross -> Noght
            Noght -> Cross
            Empty -> Cross
        }
    }
}

/**
 * Enum используется для текстового поля в кнопке выбора режима игры в MenuScreen.
 */
enum class Player {
    Human,
    EasyAI,
    HardAI
}

/**
 * Enum используется для получения информации о состоянии игрового процесса, завершена ли игра, кто выиграл.
 */
enum class GameResult {
    Tie, CrossWon, NoghtWon, NotFinishedYet
}
