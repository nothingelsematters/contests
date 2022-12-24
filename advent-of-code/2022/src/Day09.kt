import kotlin.math.absoluteValue
import kotlin.math.sign

private data class State(var rope: List<Point>, val visited: MutableSet<Point> = mutableSetOf(Point(0, 0))) {
    constructor(ropeLength: Int) : this(List(ropeLength) { Point(0, 0) })
}

private fun ropeSimulation(headRoute: List<Pair<String, Int>>, ropeLength: Int) =
    headRoute.asSequence()
        .flatMap { (direction, steps) -> List(steps) { direction } }
        .fold(State(ropeLength)) { state, directionString ->
            val (rope, visited) = state
            rope.first().run {
                val direction = when (directionString) {
                    "U" -> Direction.Up
                    "D" -> Direction.Down
                    "L" -> Direction.Left
                    "R" -> Direction.Right
                    else -> expect()
                }
                this += direction.point
            }

            rope.asSequence().windowed(2).forEach { (currentHead, currentTail) ->
                val distance = currentHead - currentTail
                if (distance.x.absoluteValue >= 2 || distance.y.absoluteValue >= 2) {
                    currentTail.x += distance.x.sign
                    currentTail.y += distance.y.sign
                }
            }

            visited += rope.last().copy()
            state
        }
        .visited
        .size

fun main() {
    val headRoute = mapLines { line ->
        val (direction, steps) = line.split(' ')
        direction to steps.toInt()
    }

    println(ropeSimulation(headRoute, 2))
    println(ropeSimulation(headRoute, 10))
}
