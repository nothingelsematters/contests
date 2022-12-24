private val moves = listOf(
    Direction.Left to (-1..1).map { Point(-1, it) },
    Direction.Right to (-1..1).map { Point(1, it) },
    Direction.Down to (-1..1).map { Point(it, -1) },
    Direction.Up to (-1..1).map { Point(it, 1) },
)

@OptIn(ExperimentalStdlibApi::class)
private fun wander(tiles: MutableSet<Point>, moveIndex: Int) {
    tiles.asSequence()
        .filter { point ->
            moves.any { neighbours -> neighbours.second.any { point + it in tiles } }
        }
        .mapNotNull { point ->
            moves[moveIndex..<moves.size].asSequence()
                .plus(moves[0..<moveIndex].asSequence())
                .find { (_, scope) -> scope.none { point + it in tiles } }
                ?.let { point + it.first.point to point }
        }
        .groupBy({ it.first }) { it.second }
        .asSequence()
        .filter { it.value.size == 1 }
        .forEach { (to, from) ->
            tiles -= from.single()
            tiles += to
        }
}

private fun part1(originalTiles: Set<Point>): Int {
    val tiles = originalTiles.toMutableSet()
    repeat(10) { wander(tiles, it % moves.size) }

    return (tiles.minOf { it.x }..tiles.maxOf { it.x }).sumOf { i ->
        (tiles.minOf { it.y }..tiles.maxOf { it.y }).count { Point(i, it) !in tiles }
    }
}

private fun part2(originalTiles: Set<Point>): Int {
    val tiles = originalTiles.toMutableSet()

    repeat(Int.MAX_VALUE) { i ->
        val copy = tiles.toSet()
        wander(tiles, i % moves.size)
        if (tiles == copy) return i + 1
    }
    error("Int.MAX_VALUE iterations, huh?")
}

fun main() {
    val tiles = buildSet {
        getFullInput().lineSequence().forEachIndexed { i, row ->
            row.asSequence().withIndex().filter { it.value == '#' }.forEach { add(Point(i, it.index)) }
        }
    }

    println(part1(tiles))
    println(part2(tiles))
}
