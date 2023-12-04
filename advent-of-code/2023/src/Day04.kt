private fun sumCardsBy(
    games: List<Pair<List<Int>, List<Int>>>,
    winProcessing: (Int) -> Int,
): Int =
    games.sumOf { (winning, bought) ->
        winProcessing(bought.count { it in winning })
    }

fun main() {
    val games = mapLines { line ->
        line.substringAfter(":")
            .splitToSequence("|")
            .map { it.toInts() }
            .toList()
            .toPair()
    }

    val first = sumCardsBy(games) { 1 shl (it - 1) }

    val copies = ArrayDeque(List(games.first().first.size) { 1 })
    val second = sumCardsBy(games) { won ->
        copies.addLast(1)
        copies.removeFirst().also { cards ->
            repeat(won) { copies[it] += cards }
        }
    }

    println("$first $second")
}
