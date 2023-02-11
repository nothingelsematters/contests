fun shortestAlternatingPaths(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
    val graph = redEdges.asSequence()
        .map { (a, b) -> a to (b to true) }
        .groupingBy { it.first }
        .fold({ _, _ -> mutableListOf<Pair<Int, Boolean>>()}) { _, acc, element ->
            acc.also { acc += element.second }
        }
        .toMutableMap()
    blueEdges.asSequence().forEach { (a, b) -> graph.getOrPut(a) { mutableListOf() } += b to false }

    val result = MutableList<Int?>(n) { null }
    val visited = MutableList(n * 2) { false }
    var q = listOf(0 to false, 0 to true)
    result[0] = 0
    visited[0] = true
    visited[n] = true
    var i = 0

    while (q.isNotEmpty()) {
        i++
        q = q.asSequence()
            .flatMap { (j, flag) ->
                graph[j]
                    ?.let { edges ->
                        edges.asSequence()
                            .filter { !visited[it.first + if (it.second) n else 0] && it.second != flag }
                            .onEach {
                                result[it.first] = minOf(result[it.first] ?: Int.MAX_VALUE, i)
                                visited[it.first + if (it.second) n else 0] = true
                            }
                    }
                    .orEmpty()
            }
            .toList()
    }

    return result.asSequence().map { it ?: -1 }.toList().toIntArray()
}
