import kotlin.math.min

// asymptotic: O(V^2)
private fun dijkstra(matrix: List<List<Int>>): Int {
    val distance = MutableList(matrix.size) { MutableList(matrix.first().size) { Int.MAX_VALUE } }
    val used = MutableList(matrix.size) { MutableList(matrix.first().size) { false } }
    distance[0][0] = 0

    for (dummy in matrix.innerIndices) {
        val vertex = matrix.innerIndices.filter { !used[it] }.minByOrNull { distance[it] }.expect()

        if (distance[vertex] == Int.MAX_VALUE) break
        used[vertex] = true

        val (i, j) = vertex
        sequenceOf(i - 1 to j, i + 1 to j, i to j - 1, i to j + 1)
            .filter { (i, j) -> i in matrix.indices && j in matrix.first().indices }
            .forEach { distance[it] = min(distance[it], distance[vertex] + matrix[it]) }
    }

    return distance.last().last()
}

private fun widen(matrix: List<List<Int>>) =
    List(matrix.size * 5) { i ->
        List(matrix.first().size * 5) { j ->
            val increment = (i / matrix.size) + (j / matrix.first().size)
            val value = matrix[i % matrix.size][j % matrix.first().size] + increment
            if (value >= 10) value - 9 else value
        }
    }

fun main() {
    val matrix = mapLines { line -> line.map { it.toString().toInt() } }

    // runs for 3s
    println(dijkstra(matrix))

    // runs for 30min
    println(dijkstra(widen(matrix)))
}
