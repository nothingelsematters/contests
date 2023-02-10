fun maxDistance(grid: Array<IntArray>): Int {
    val visited = grid.asSequence()
        .map { it.asSequence().map { false }.toMutableList() }
        .toMutableList()

    var q = grid.indices
        .asSequence()
        .flatMap { i -> grid.indices.asSequence().map { i to it } }
        .filter { (i, j) -> grid[i][j] == 1 }
        .toList()

    var lastZero: Int? = null
    var distance = 1

    while (q.isNotEmpty()) {
        q = q.asSequence()
            .flatMap { (i, j) -> sequenceOf(i + 1 to j, i - 1 to j, i to j + 1, i to j - 1) }
            .filter { (i, j) -> i in grid.indices && j in grid[0].indices && !visited[i][j] }
            .onEach { (i, j) ->
                visited[i][j] = true
                if (grid[i][j] == 0) lastZero = distance
            }
            .toList()

        distance++
    }

    return lastZero ?: -1
}
