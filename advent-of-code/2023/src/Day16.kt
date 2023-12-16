private enum class Tile {
    Empty,
    RightMirror,
    LeftMirror,
    HorizontalSplitter,
    VerticalSplitter;

    companion object {
        fun parse(input: Char): Tile? = when (input) {
            '.' -> Empty
            '/' -> RightMirror
            '\\' -> LeftMirror
            '-' -> HorizontalSplitter
            '|' -> VerticalSplitter
            else -> null
        }
    }
}

private fun energize(
    tiles: List<List<Tile>>,
    current: Index2,
    direction: Direction,
    energized: MutableSet<Pair<Index2, Direction>>,
) {
    if (current to direction in energized) return
    if (tiles.getOrNull(current) == null) return
    energized += current to direction

    val nextDirections = when (tiles[current]) {
        Tile.Empty -> sequenceOf(direction)
        Tile.RightMirror -> sequenceOf(
            when (direction) {
                Direction.Up -> Direction.Right
                Direction.Down -> Direction.Left
                Direction.Left -> Direction.Down
                Direction.Right -> Direction.Up
            }
        )

        Tile.LeftMirror -> sequenceOf(
            when (direction) {
                Direction.Up -> Direction.Left
                Direction.Down -> Direction.Right
                Direction.Left -> Direction.Up
                Direction.Right -> Direction.Down
            }
        )

        Tile.HorizontalSplitter -> when (direction) {
            Direction.Up, Direction.Down -> sequenceOf(Direction.Left, Direction.Right)
            Direction.Left, Direction.Right -> sequenceOf(direction)
        }

        Tile.VerticalSplitter -> when (direction) {
            Direction.Up, Direction.Down -> sequenceOf(direction)
            Direction.Left, Direction.Right -> sequenceOf(Direction.Up, Direction.Down)
        }
    }

    nextDirections.forEach {
        energize(tiles, current + it.index2, it, energized)
    }
}

private fun countEnergized(tiles: List<List<Tile>>, start: Index2, direction: Direction): Int {
    val energized = mutableSetOf<Pair<Index2, Direction>>()
    energize(tiles, start, direction, energized)
    return energized.asSequence().map { it.first }.distinct().count()
}

fun main() {
    val tiles = mapLines { line -> line.map { Tile.parse(it).unwrap() } }

    val first = countEnergized(tiles, Index2(0, 0), Direction.Right)

    val second = tiles.indices.asSequence()
        .flatMap {
            sequenceOf(
                Index2(it, 0) to Direction.Right,
                Index2(it, tiles[it].lastIndex) to Direction.Left,
            )
        }
        .plus(
            tiles.first().indices.asSequence()
                .flatMap {
                    sequenceOf(
                        Index2(0, it) to Direction.Down,
                        Index2(it, tiles[it].lastIndex) to Direction.Up,
                    )
                }
        )
        .maxOf { (index, direction) -> countEnergized(tiles, index, direction) }

    println("$first $second")
}
