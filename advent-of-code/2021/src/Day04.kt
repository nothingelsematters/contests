private fun findWinner(numbers: List<Int>, boards: List<List<List<Int>>>, position: Int): Int {
    val marked = mutableSetOf<Int>()
    val won = mutableSetOf<Int>()

    for (number in numbers) {
        marked.add(number)

        for ((i, board) in boards.withIndex()) {
            if (i in won) continue

            val isRowMarked = board.any { row -> row.all { it in marked } }
            val isColumnMarked = board.indices.any { j -> board.all { it[j] in marked } }

            if (isRowMarked || isColumnMarked) {
                won.add(i)
                if (won.size == position) {
                    return number * board.flatten().filter { it !in marked }.sum()
                }
            }
        }
    }

    throw IllegalArgumentException("Invalid input")
}

fun main() {
    val numbers = readln().toInts()
    val boards = mapBlocks { it.toInts() }

    println(findWinner(numbers, boards, 1))
    println(findWinner(numbers, boards, boards.size))
}
