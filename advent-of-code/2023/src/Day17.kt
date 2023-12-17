import java.util.PriorityQueue

private fun findMinimumPath(cityBlocks: List<List<Int>>, minimumStraight: Int, maximumStraight: Int): Int {
    data class TraverseState(val index: Index2, val direction: Direction, val straight: Int)

    val q = PriorityQueue(compareBy { it: Pair<Int, TraverseState> -> it.first })
    q += 0 to TraverseState(Index2(0, 0), Direction.Right, 0)
    val visited = mutableSetOf<TraverseState>()

    while (q.isNotEmpty()) {
        val (distance, state) = q.poll()
        if (state in visited) continue
        if (state.index == cityBlocks.lastIndex2) return distance

        visited += state

        val sequence = sequence {
            if (state.straight >= minimumStraight) {
                val turnSubsequence = Direction.entries.asSequence()
                    .filter { it != state.direction && it != state.direction.reversed() }
                    .map { it to 1 }
                yieldAll(turnSubsequence)
            }
            if (state.straight < maximumStraight) {
                yield(state.direction to state.straight + 1)
            }
        }

        sequence.filter { (d, _) -> cityBlocks.getOrNull(state.index + d.index2) != null }
            .forEach { (d, s) ->
                val index = state.index + d.index2
                q += distance + cityBlocks[index] to TraverseState(index, d, s)
            }
    }

    expect()
}

fun main() {
    val cityBlocks = mapLines { line -> line.map { it.toString().toInt() } }

    val first = findMinimumPath(cityBlocks, 0, 3)
    val second = findMinimumPath(cityBlocks, 4, 10)

    println("$first $second")
}
