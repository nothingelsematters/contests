private fun getTrenchSize(instructions: List<Pair<Direction, Int>>): Long {
    val verticalIntervals = buildList {
        instructions.fold(Index2(0, 0)) { currentIndex, (dir, dist) ->
            val next = currentIndex + dir.index2 * dist

            when (dir) {
                Direction.Up -> add(currentIndex.j to next.i..currentIndex.i)
                Direction.Down -> add(currentIndex.j to currentIndex.i..next.i)
                else -> {}
            }

            next
        }
    }

    return verticalIntervals.asSequence()
        .flatMap { (_, range) -> sequenceOf(range.first, range.last) }
        .sorted()
        .distinct()
        .zipWithNext()
        .fold(0L to listOf<IntRange>()) { (sum, previousHorizontalIntervals), (a, b) ->
            val currentHorizontalIntervals = verticalIntervals.asSequence()
                .filter { a in it.second && b in it.second }
                .map { it.first }
                .sorted()
                .chunked(2) { (c, d) -> c..d }
                .toList()

            val intervalLength = currentHorizontalIntervals.sumOf { it.last.toLong() - it.first + 1 }
            val overlappingLength = currentHorizontalIntervals.sumOf { range ->
                previousHorizontalIntervals.asSequence()
                    .filter { it.first in range || it.last in range || range.first in it || range.last in it }
                    .sumOf { minOf(it.last, range.last) - maxOf(it.first, range.first) + 1 }
            }

            sum + intervalLength * (b - a + 1) - overlappingLength to currentHorizontalIntervals
        }
        .first
}

fun main() {
    data class Input(val direction: Direction, val distance: Int, val color: String)

    val instructions = mapLines { line ->
        val (directionString, distanceString, colorString) = line.split(' ')
        Input(
            when (directionString) {
                "R" -> Direction.Right
                "L" -> Direction.Left
                "U" -> Direction.Up
                "D" -> Direction.Down
                else -> expect()
            },
            distanceString.toInt(),
            colorString.substring(2, 8),
        )

    }

    val first = getTrenchSize(instructions.map { it.direction to it.distance })

    val decodedInstructions = instructions.map {
        val direction = when (it.color.last()) {
            '0' -> Direction.Right
            '1' -> Direction.Down
            '2' -> Direction.Left
            '3' -> Direction.Up
            else -> expect()
        }
        val distance = it.color.substring(0, 5).toInt(16)
        direction to distance
    }
    val second = getTrenchSize(decodedInstructions)

    println("$first $second")
}
