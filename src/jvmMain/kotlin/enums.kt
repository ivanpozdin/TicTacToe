enum class Screen {
    MenuScreen, GameScreen, GameOverScreen
}

enum class FigureType(val path: String) {
    Cross("cross.png"), Noght("noght.png"), Empty("empty.png");
    fun getNext(): FigureType {
        return when (this) {
            Cross -> Noght
            Noght -> Cross
            Empty -> Cross
        }
    }
}
