fun main() {
    val lines = mapLines { it }
    val size = lines.asSequence().map { it.length }.maxOrNull().expect()
    val iterations = 100
    val sideSize = (size + iterations) * 2 + 1
    var grid = MutableList(sideSize) { MutableList(sideSize) { false } }
    val start = sideSize / 2 to sideSize / 2
    val transitions = mapOf(
        "e" to (0 to 1),
        "se" to (1 to 0),
        "sw" to (1 to -1),
        "w" to (0 to -1),
        "nw" to (-1 to 0),
        "ne" to (-1 to 1)
    )

    for (line in lines) {
        var current = start
        var currentLine = line

        while (currentLine.isNotEmpty()) {
            val (key, diff) = transitions.asSequence().find { currentLine.startsWith(it.key) }.expect()
            current = current.first + diff.first to current.second + diff.second
            currentLine = currentLine.drop(key.length)
        }

        grid[current.first][current.second] = !grid[current.first][current.second]
    }

    val first = grid.sumBy { it.count { it } }

    repeat(iterations) {
        val copy = grid.map { it.toMutableList() }.toMutableList()

        for (i in 1 until grid.lastIndex) {
            for (j in 1 until grid[i].lastIndex) {
                val amount = transitions.values.asSequence().map { grid[i + it.first][j + it.second] }.count { it }
                when {
                    grid[i][j] && amount !in 1..2 -> copy[i][j] = false
                    !grid[i][j] && amount == 2 -> copy[i][j] = true
                }
            }
        }

        grid = copy
    }

    val second = grid.sumBy { it.count { it } }

    println("$first $second")
}
