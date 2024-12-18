private fun wanderMap(map: List<List<Boolean>>, start: Index2, process: (Pair<Index2, Direction>) -> Boolean) {
    var current = start to Direction.Up

    while (map.getOrNull(current.first) != null) {
        if (!process(current)) return

        val (position, direction) = current
        val forward = position + direction.point

        current = if (map.getOrNull(forward) != false) forward to direction
            else position to direction.clockwise()
    }
}

private fun part1(map: List<List<Boolean>>, start: Index2): Int {
    val visited = mutableSetOf<Index2>()

    wanderMap(map, start) {
        visited += it.first
        true
    }

    return visited.size
}

private fun isLooped(map: List<List<Boolean>>, start: Index2): Boolean {
    val visited = mutableSetOf<Pair<Index2, Direction>>()

    var looped = false

    wanderMap(map, start) {
        looped = it in visited
        visited += it
        !looped
    }

    return looped
}

private fun part2(map: List<List<Boolean>>, start: Index2): Int =
    map.indexed2Sequence()
        .filter { it.value }
        .count { (index, _) ->
            val mapCopy = map.map { it.toMutableList() }
            mapCopy[index] = false
            isLooped(mapCopy, start)
        }

fun main() {
    var start = Index2(0, 0)

    val map = mapLinesIndexed { (i, line) ->
        line.asSequence()
            .withIndex()
            .map { (j, ch) ->
                if (ch == '^') start = Index2(i, j)
                ch != '#'
            }
            .toList()
    }

    val first = part1(map, start)
    val second = part2(map, start)

    println("$first $second")
}
