private enum class Game(val stringRepresentation: String, val shapeScore: Int) {
    Rock("AX", 1),
    Paper("BY", 2),
    Scissors("CZ", 3);

    infix fun fight(rhs: Game) = when {
        this == rhs -> 3
        this == Paper && rhs == Rock
            || this == Rock && rhs == Scissors
            || this == Scissors && rhs == Paper -> 6
        else -> 0
    }

    companion object {
        fun fromString(string: String) = Game.values().find { string in it.stringRepresentation }
    }
}

fun main() {
    val matches = mapLines { line -> line.split(' ').map { Game.fromString(it).unwrap() }.toPair() }

    val first = matches.sumOf { (l, r) -> r.shapeScore + (r fight l) }

    val second = matches.sumOf { (l, r) ->
        val winningScore = when (r) {
            Game.Rock -> 0
            Game.Paper -> 3
            Game.Scissors -> 6
        }

        val shapeScore = Game.values().find { it fight l == winningScore }!!.shapeScore

        shapeScore + winningScore
    }

    println("$first $second")
}
