import kotlin.math.min

private fun dijkstra(grid: List<List<Int>>, from: Point): List<List<Int>> {
    val distances = MutableList(grid.size) { MutableList(grid.first().size) { Int.MAX_VALUE } }
    val used = MutableList(grid.size) { MutableList(grid.first().size) { false } }

    distances[from] = 0

    for (iteration in 0 until grid.size * grid.first().size) {
        val v = distances.innerIndexedSequence()
            .filter { (i, j) -> !used[i][j] }
            .minBy { it.value }
            .let { (i, j) -> Point(i, j) }

        if (distances[v] == Int.MAX_VALUE) break

        used[v] = true

        Direction.values()
            .asSequence()
            .map { it.point + v }
            .filter { it.x in grid.indices && it.y in grid.first().indices }
            .filter { grid[it] - grid[v] >= -1 }
            .forEach { distances[it] = min(distances[it], distances[v] + 1) }
    }

    return distances
}

fun main() {
    var from = Point(0, 0)
    var to = Point(0, 0)

    val grid = getFullInput()
        .lineSequence()
        .withIndex()
        .map { (i, line) ->
            line.asSequence()
                .withIndex()
                .map { (j, it) ->
                    when (it) {
                        in 'a'..'z' -> it
                        'S' -> 'a'.also { from = Point(i, j) }
                        'E' -> 'z'.also { to = Point(i, j) }
                        else -> expect()
                    } - 'a'
                }
                .toList()
        }
        .toList()

    val distances = dijkstra(grid, to)
    val first = distances[from]
    val second = distances.innerIndexedSequence().filter { (i, j) -> grid[i][j] == 0 }.minOf { it.value }

    println("$first $second")
}
