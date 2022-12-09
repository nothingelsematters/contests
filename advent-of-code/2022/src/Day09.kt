import kotlin.math.absoluteValue
import kotlin.math.sign

private data class State(var rope: List<Point>, val visited: MutableSet<Point> = mutableSetOf(Point(0, 0))) {
    constructor(ropeLength: Int) : this(List(ropeLength) { Point(0, 0) })
}

private fun ropeSimulation(headRoute: List<Pair<String, Int>>, ropeLength: Int) =
    headRoute.asSequence()
        .flatMap { (direction, steps) -> List(steps) { direction } }
        .fold(State(ropeLength)) { state, direction ->
            val (rope, visited) = state
            rope.first().run {
                when (direction) {
                    "U" -> y += 1
                    "D" -> y -= 1
                    "L" -> x -= 1
                    "R" -> x += 1
                    else -> expect()
                }
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
