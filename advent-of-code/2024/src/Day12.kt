private fun getAreaAndFences(
    grid: List<List<Char>>,
    current: Index2,
    visited: MutableSet<Index2>,
    fences: MutableSet<Pair<Index2, Direction>>,
): Int {
    if (current in visited) return 0

    visited += current
    return Direction.entries
        .map { direction ->
            val next = direction.point + current
            if (grid.getOrNull(next) != grid[current]) {
                fences += current to direction
                return@map 0
            }

            getAreaAndFences(grid, next, visited, fences)
        }
        .sum() + 1
}

private fun calculateFenceCost(
    grid: List<List<Char>>,
    secondMetricFunction: (Set<Pair<Index2, Direction>>) -> Int,
): Int {
    val visited = mutableSetOf<Index2>()
    return grid.indexed2Sequence().sumOf {
        val sides = mutableSetOf<Pair<Index2, Direction>>()
        val first = getAreaAndFences(grid, it.index2, visited, sides)
        first * secondMetricFunction(sides)
    }
}

private fun calculateSides(initialFences: Set<Pair<Index2, Direction>>): Int {
    fun traverseSide(
        initialFences: Set<Pair<Index2, Direction>>,
        fences: MutableSet<Pair<Index2, Direction>>,
        vertical: Boolean,
    ): Int =
        initialFences.asSequence()
            .filter { it.second.isVertical() == vertical }
            .sortedBy { if (vertical) it.first.y else it.first.x }
            .count { (point, direction) ->
                if (point to direction !in fences) return@count false

                var current = point
                while (current to direction in fences) {
                    fences -= current to direction
                    current += (if (vertical) Direction.Right else Direction.Down).point
                }
                true
            }

    val fences = initialFences.toMutableSet()
    return traverseSide(initialFences, fences, true) + traverseSide(initialFences, fences, false)
}

fun main() {
    val grid = mapLines { it.toList() }

    val first = calculateFenceCost(grid) { it.size }
    val second = calculateFenceCost(grid, ::calculateSides)

    println("$first $second")
}
