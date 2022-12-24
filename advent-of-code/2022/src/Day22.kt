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

private enum class Rotation(override val stringRepresentation: String) : StringRepresentable, PathEntity {
    Clockwise("R"),
    Counterclockwise("L"),
}

private data class Distance(val distance: Int) : PathEntity

private fun slalom(
    slope: List<List<Cell>>,
    pathEntities: List<PathEntity>,
    wrappingRule: (Point, Direction) -> Pair<Point, Direction>,
): Int {
    var currentPoint = Point(0, slope[0].indexOf(Cell.Open))
    val facings = listOf(Direction.Up, Direction.Right, Direction.Down, Direction.Left)
    var currentFacing = facings.first()

    pathEntities.forEach { entity ->
        when (entity) {
            is Rotation -> {
                val ordinalDifference = when (entity) {
                    Rotation.Clockwise -> 1
                    Rotation.Counterclockwise -> -1
                }

                currentFacing = facings.getModulo(currentFacing.ordinal + ordinalDifference)
            }

            is Distance -> repeat(entity.distance) {
                val newPoint = currentPoint + currentFacing.point

                when (slope.getOrNull(newPoint)) {
                    Cell.Wall -> {}
                    Cell.Open -> currentPoint = newPoint
                    null, Cell.Close -> wrappingRule(currentPoint, currentFacing).let {
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
        val localFacing = currentFacing.point * -1
        val localPoint = currentPoint.copy()

        while (slope.getOrNull(localPoint).let { it != null && it != Cell.Close }) {
            localPoint += localFacing
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
            1 to Direction.Down -> Point(SIDE - 1 - x, 0).quadrantize(6) to Direction.Up
            1 to Direction.Left -> Point(y, 0).quadrantize(9) to Direction.Up
            2 to Direction.Up -> Point(SIDE - 1 - x, SIDE - 1).quadrantize(7) to Direction.Down
            2 to Direction.Right -> Point(y, SIDE - 1).quadrantize(4) to Direction.Down
            2 to Direction.Left -> Point(SIDE - 1, y).quadrantize(9) to Direction.Left
            4 to Direction.Down -> Point(0, x).quadrantize(6) to Direction.Right
            4 to Direction.Up -> Point(SIDE - 1, x).quadrantize(2) to Direction.Left
            6 to Direction.Left -> Point(y, 0).quadrantize(4) to Direction.Up
            6 to Direction.Down -> Point(SIDE - 1 - x, 0).quadrantize(1) to Direction.Up
            7 to Direction.Up -> Point(SIDE - 1 - x, SIDE - 1).quadrantize(2) to Direction.Down
            7 to Direction.Right -> Point(y, SIDE - 1).quadrantize(9) to Direction.Down
            9 to Direction.Down -> Point(0, x).quadrantize(1) to Direction.Right
            9 to Direction.Right -> Point(0, y).quadrantize(2) to Direction.Right
            9 to Direction.Up -> Point(SIDE - 1, x).quadrantize(7) to Direction.Left
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
                    add(current.toIntOrNull()?.let(::Distance) ?: current.intoEnum<Rotation>()!!)
                }
            }
        }

        slope to path
    }

    println(part1(slope, path))
    println(part2(slope, path))
}
