private interface StringRepresentable {
    val stringRepresentation: String
}

private inline fun <reified T> String.intoEnum(): T?
    where T : StringRepresentable, T : Enum<T> =
    T::class.java.enumConstants.find { it.stringRepresentation == this }

private enum class Cell(override val stringRepresentation: String) : StringRepresentable {
    Close(" "),
    Open("."),
    Wall("#"),
}

private sealed interface PathEntity

private enum class Direction(override val stringRepresentation: String) : StringRepresentable, PathEntity {
    Clockwise("R"),
    Counterclockwise("L"),
}

private enum class Facing(val point: Point) {
    Right(Point(0, 1)),
    Down(Point(1, 0)),
    Left(Point(0, -1)),
    Up(Point(-1, 0)),
}

private data class Distance(val distance: Int) : PathEntity

private fun slalom(
    slope: List<List<Cell>>,
    pathEntities: List<PathEntity>,
    wrappingRule: (Point, Facing) -> Pair<Point, Facing>,
): Int {
    var currentPoint = Point(0, slope[0].indexOf(Cell.Open))
    var currentFacing = Facing.Right

    pathEntities.forEach { entity ->
        when (entity) {
            is Direction -> {
                val ordinalDifference = when (entity) {
                    Direction.Clockwise -> 1
                    Direction.Counterclockwise -> -1
                }

                val newIndex = (currentFacing.ordinal + ordinalDifference + Facing.values().size) % Facing.values().size
                currentFacing = Facing.values()[newIndex]
            }
            is Distance -> repeat(entity.distance) {
                val newPoint = currentPoint + currentFacing.point

                when (slope.getOrNull(newPoint)) {
                    Cell.Wall -> {}
                    Cell.Open -> currentPoint = newPoint
                    null, Cell.Close ->  wrappingRule(currentPoint, currentFacing).let {
                        if (slope[it.first] != Cell.Wall) {
                            currentPoint = it.first
                            currentFacing = it.second
                        }
                    }
                }
            }
        }
    }

    return 1000 * (currentPoint.x + 1) + 4 * (currentPoint.y + 1) + currentFacing.ordinal
}

private fun part1(slope: List<List<Cell>>, pathEntities: List<PathEntity>): Int =
    slalom(slope, pathEntities) { currentPoint, currentFacing ->
        val newIndex = (currentFacing.ordinal + 2 + Facing.values().size) % Facing.values().size
        val localFacing = Facing.values()[newIndex]
        var localPoint = currentPoint

        while (slope.getOrNull(localPoint).let { it != null && it != Cell.Close }) {
            localPoint += localFacing.point
        }

        (localPoint + currentFacing.point) to currentFacing
    }

private const val SIDE = 50

private fun part2(slope: List<List<Cell>>, pathEntities: List<PathEntity>): Int {
    fun Point.getQuadrant(): Int = (x / SIDE) * 3 + y / SIDE

    fun Point.quadrantize(quadrant: Int) = Point(x + quadrant / 3 * SIDE, y + quadrant % 3 * SIDE)

    fun Point.dequadrantize() = quadrantize(-getQuadrant())

    // .12
    // .4.
    // 67.
    // 9..
    return slalom(slope, pathEntities) { currentPoint, currentFacing ->
        val (x, y) = currentPoint.dequadrantize()

        when (currentPoint.getQuadrant() to currentFacing) {
            1 to Facing.Left -> Point(SIDE - 1 - x, 0).quadrantize(6) to Facing.Right
            1 to Facing.Up -> Point(y, 0).quadrantize(9) to Facing.Right
            2 to Facing.Right -> Point(SIDE - 1 - x, SIDE - 1).quadrantize(7) to Facing.Left
            2 to Facing.Down -> Point(y, SIDE - 1).quadrantize(4) to Facing.Left
            2 to Facing.Up -> Point(SIDE - 1, y).quadrantize(9) to Facing.Up
            4 to Facing.Left -> Point(0, x).quadrantize(6) to Facing.Down
            4 to Facing.Right -> Point(SIDE - 1, x).quadrantize(2) to Facing.Up
            6 to Facing.Up -> Point(y, 0).quadrantize(4) to Facing.Right
            6 to Facing.Left -> Point(SIDE - 1 - x, 0).quadrantize(1) to Facing.Right
            7 to Facing.Right -> Point(SIDE - 1 - x, SIDE - 1).quadrantize(2) to Facing.Left
            7 to Facing.Down -> Point(y, SIDE - 1).quadrantize(9) to Facing.Left
            9 to Facing.Left -> Point(0, x).quadrantize(1) to Facing.Down
            9 to Facing.Down -> Point(0, y).quadrantize(2) to Facing.Down
            9 to Facing.Right -> Point(SIDE - 1, x).quadrantize(7) to Facing.Up
            else -> expect()
        }
    }
}

fun main() {
    val (slope, path) = mapBlocks { it }.let { (slopeBlock, pathBlock) ->
        val slope = slopeBlock.map { line -> line.map { it.toString().intoEnum<Cell>()!! } }
        val path = pathBlock.single().let {
            val q = it.toCollection(ArrayDeque())

            buildList {
                while (q.isNotEmpty()) {
                    val current = buildString {
                        val number = q.first().isDigit()
                        while (q.isNotEmpty() && q.first().isDigit() == number) {
                            append(q.removeFirst())
                        }
                    }
                    add(current.toIntOrNull()?.let(::Distance) ?: current.intoEnum<Direction>()!!)
                }
            }
        }

        slope to path
    }

    println(part1(slope, path))
    println(part2(slope, path))
}
