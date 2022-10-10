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

enum class Player(val description: String) {
    Human("Player"),
    EasyAI("Stupid AI"),
    HardAI("Smart AI")
}

enum class Game {
    PersonVSPerson, PersonVSEasyAI, PersonVSHardAI
}
