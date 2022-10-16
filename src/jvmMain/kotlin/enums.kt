enum class Screen {
    MenuScreen, GameScreen, GameOverScreen
}

enum class Figure(val path: String) {
    Cross("cross.png"), Noght("noght.png"), Empty("empty.png");

    fun getNext(): Figure {
        return when (this) {
            Cross -> Noght
            Noght -> Cross
            Empty -> Cross
        }
    }
}

enum class Player {
    Human,
    EasyAI,
    HardAI
}

enum class GameResult {
    Tie, CrossWon, NoghtWon, NotFinishedYet
}
