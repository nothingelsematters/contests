private fun dijkstra(grid: Map<String, Pair<Int, List<String>>>, from: String): Map<String, Int> {
    val distances = grid.asSequence().map { it.key to Int.MAX_VALUE }.toMap().toMutableMap()
    val used = mutableSetOf<String>()

    distances[from] = 0

    for (iteration in 0 until grid.size) {
        val v = distances.asSequence().filter { it.key !in used }.minBy { it.value }.key

        if (distances[v] == Int.MAX_VALUE) break
        used += v
        grid[v]!!.second.forEach { distances[it] = minOf(distances[it]!!, distances[v]!! + 1) }
    }

    return distances
}

private fun part1(flows: Map<String, Int>, dijkstras: Map<String, Map<String, Int>>): Int {
    fun weightedDepthFirstSearch(
        flows: Map<String, Int>,
        dijkstras: Map<String, Map<String, Int>>,
        currentVertex: String,
        iterateLimit: Int,
        currentWeight: Int = 0,
        used: MutableSet<String> = mutableSetOf(),
    ): Int {
        if (currentVertex in used || used.size == flows.size || iterateLimit <= 0) return currentWeight

        used += currentVertex
        val result = dijkstras[currentVertex]!!.maxOf { (vertex, path) ->
            weightedDepthFirstSearch(
                flows,
                dijkstras,
                vertex,
                iterateLimit - path - 1,
                currentWeight + (flows[currentVertex] ?: 0) * iterateLimit,
                used,
            )
        }

        used -= currentVertex
        return result
    }

    return weightedDepthFirstSearch(flows, dijkstras, "AA", 30)
}

private fun <T> Pair<T, T>.map(first: Boolean, value: T) = if (first) copy(first = value) else copy(second = value)
private fun <T> Pair<T, T>.get(first: Boolean) = if (first) this.first else second

private fun part2(flows: Map<String, Int>, dijkstras: Map<String, Map<String, Int>>): Int {
    fun doubleWeightedDepthFirstSearch(
        flows: Map<String, Int>,
        dijkstras: Map<String, Map<String, Int>>,
        currentVertices: Pair<String, String>,
        iterateLimits: Pair<Int, Int>,
        currentWeight: Int = 0,
        used: MutableSet<String> = mutableSetOf(),
    ): Int {
        if (used.size == flows.size || iterateLimits.toList().all { it <= 0 }) return currentWeight

        val iterateFirst = iterateLimits.first >= iterateLimits.second

        return dijkstras[currentVertices.get(iterateFirst)]!!
            .asSequence()
            .filter { it.key !in used }
            .maxOfOrNull { (vertex, path) ->
                used += vertex
                val newIterationLimit = iterateLimits.get(iterateFirst) - path - 1

                doubleWeightedDepthFirstSearch(
                    flows,
                    dijkstras,
                    currentVertices.map(iterateFirst, vertex),
                    iterateLimits.map(iterateFirst, newIterationLimit),
                    currentWeight + (flows[vertex] ?: 0) * newIterationLimit,
                    used,
                ).also { used -= vertex }
            }
            ?: 0
    }

    return doubleWeightedDepthFirstSearch(flows, dijkstras, "AA" to "AA", 26 to 26)
}

fun main() {
    val graph = buildMap {
        mapLines {
            val (from, flow, tos) = """Valve (\w+) has flow rate=(\d+); tunnels? leads? to valves? (.*)""".toRegex()
                .matchEntire(it)!!
                .destructured
            this[from] = flow.toInt() to tos.split(", ")
        }
    }

    val flows = graph.asSequence().filter { it.value.first != 0 }.map { it.key to it.value.first }.toMap()
    val dijkstras = graph.asSequence()
        .filter { it.key in flows || it.key == "AA" }
        .map { (vertex, _) -> vertex to dijkstra(graph, vertex).filterKeys { it in flows } }
        .toMap()

    println(part1(flows, dijkstras))
    println(part2(flows, dijkstras)) // Runs for 3m24s 🤷
}
