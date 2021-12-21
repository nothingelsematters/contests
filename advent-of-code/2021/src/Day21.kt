private operator fun Pair<Long, Long>.plus(other: Pair<Long, Long>) = first + other.first to second + other.second

private fun part1(firstStart: Int, secondStart: Int): Int {
    var (firstPosition, secondPosition) = firstStart to secondStart
    var firstScore = 0
    var secondScore = 0

    repeat(2000) {
        when {
            firstScore >= 1000 -> return secondScore * it * 3
            secondScore >= 1000 -> return firstScore * it * 3
            it and 1 == 0 -> {
                val acc = (1..3).sumOf { i -> (it * 3 + i) }
                firstPosition = (firstPosition + acc - 1) % 10 + 1
                firstScore += firstPosition
            }
            else -> {
                val acc = (1..3).sumOf { i -> (it * 3 + i) }
                secondPosition = (secondPosition + acc - 1) % 10 + 1
                secondScore += secondPosition
            }
        }
    }
    expect()
}

data class GameState(val firstPosition: Int, val secondPosition: Int, val firstScore: Int = 0, val secondScore: Int = 0)

private fun multiversePlay(
    gameState: GameState,
    visited: MutableMap<GameState, Pair<Long, Long>> = mutableMapOf()
): Pair<Long, Long> = when {
    gameState.firstScore >= 21 -> 1L to 0L
    gameState.secondScore >= 21 -> 0L to 1L
    else -> visited.getOrPut(gameState) {
        cartesian(1..3, 1..3, 1..3)
            .fold(0L to 0L) { acc, i ->
                val newPosition = (gameState.firstPosition + i.sum() - 1) % 10 + 1
                acc + multiversePlay(
                    GameState(
                        gameState.secondPosition,
                        newPosition,
                        gameState.secondScore,
                        gameState.firstScore + newPosition
                    ),
                    visited,
                )
            }
            .let { (first, second) -> second to first }
    }
}

private fun part2(firstStart: Int, secondStart: Int): Long =
    multiversePlay(GameState(firstStart, secondStart)).toList().maxOrNull().expect()

fun main() {
    val (firstStart, secondStart) = (0..1).map { readln().substringAfter(": ").toInt() }

    println(part1(firstStart, secondStart))
    println(part2(firstStart, secondStart))
}
