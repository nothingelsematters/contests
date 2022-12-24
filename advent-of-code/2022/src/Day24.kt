import java.util.PriorityQueue
import kotlin.math.absoluteValue

@OptIn(ExperimentalStdlibApi::class)
private fun sprint(
    originalBlizzards: Set<Pair<Point, Direction>>,
    fieldSize: Point,
    from: Point,
    to: Point,
    initialTime: Int = 0,
): Int {
    val positions = PriorityQueue(
        compareBy<Pair<Point, Int>> { (point, iteration) ->
            (to - point).let { it.x.absoluteValue + it.y.absoluteValue } + iteration * 10
        }
    )
    positions += from to initialTime + 1

    val blizzards = mutableMapOf<Int, Set<Point>>()
    val used = mutableSetOf<Pair<Point, Int>>()

    while (true) {
        val currentPoint = positions.remove()
        val currentBlizzards = blizzards.getOrPut(currentPoint.second) {
            originalBlizzards
                .asSequence()
                .map { (point, direction) ->
                    val newPoint = point + direction.point * currentPoint.second
                    Point(newPoint.x within fieldSize.x, newPoint.y within fieldSize.y)
                }
                .toSet()
        }

        Direction.values()
            .asSequence()
            .map { it.point }
            .plus(Point(0, 0))
            .map { it + currentPoint.first }
            .filter { it.x in 0..<fieldSize.x && (it.y in 0..<fieldSize.y || it == from || it == to) }
            .filter { it !in currentBlizzards }
            .map { it to currentPoint.second + 1 }
            .filter { it !in used }
            .forEach {
                if (it.first == to) return currentPoint.second
                positions += it
                used += it
            }
    }
}

private fun part1(blizzards: Set<Pair<Point, Direction>>, fieldSize: Point, from: Point, to: Point): Int =
    sprint(blizzards, fieldSize, from, to)

private fun part2(blizzards: Set<Pair<Point, Direction>>, fieldSize: Point, from: Point, to: Point): Int {
    val first = sprint(blizzards, fieldSize, from, to)
    val second = sprint(blizzards, fieldSize, to, from, first)
    return sprint(blizzards, fieldSize, from, to, second)
}

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val lines = getFullInput().lines()

    val fieldSize = Point(lines.first().length - 2, lines.size - 2)
    val from = Point(lines.first().indexOf('.') - 1, -1)
    val to = Point(lines.last().indexOf('.') - 1, fieldSize.y)

    val blizzards = buildSet {
        lines[1..<lines.size - 1].asSequence().forEachIndexed { i, row ->
            row.substring(1..<row.length - 1)
                .asSequence()
                .withIndex()
                .filter { it.value != '.' }
                .forEach { (j, value) ->
                    val direction = when (value) {
                        '>' -> Direction.Right
                        '<' -> Direction.Left
                        '^' -> Direction.Down
                        'v' -> Direction.Up
                        else -> expect()
                    }
                    add(Point(j, i) to direction)
                }
        }
    }

    println(part1(blizzards, fieldSize, from, to))
    println(part2(blizzards, fieldSize, from, to))
}
