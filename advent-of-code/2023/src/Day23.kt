private sealed interface PathTile {
    data object Path : PathTile
    data object Forest : PathTile
    data class Slope(val direction: Direction) : PathTile
}

private fun shortenGraph(graph: Map<Index2, List<Index2>>): Pair<List<Pair<Int, List<Int>>>, Int> {
    val newGraph = mutableListOf<MutableList<Int>>()
    val weights = mutableListOf<Int>()
    val mapping = mutableMapOf<Index2, Int>()

    for ((v, children) in graph) {
        if (v !in mapping || children.size > 2) {
            if (v in mapping) weights[mapping[v]!!]--
            mapping[v] = newGraph.size
            newGraph += mutableListOf<Int>()
            weights += 1
        }
        val parentMapping = mapping[v]!!

        if (children.size <= 2) {
            children.forEach {
                val childMapping = mapping[it]

                if (childMapping == null) {
                    mapping[it] = mapping[v]!!
                    weights[parentMapping]++
                } else if (childMapping != parentMapping) {
                    newGraph[childMapping] += parentMapping
                    newGraph[parentMapping] += childMapping
                }
            }
            continue
        }

        children.asSequence().mapNotNull { mapping[it] }.forEach {
            newGraph[parentMapping] += it
            newGraph[it] += parentMapping
        }
    }

    val newEnd = mapping.asSequence().maxBy { it.key.i }.value
    return weights.zip(newGraph) to newEnd
}

private fun makeGraph(
    map: List<List<PathTile>>,
    start: Index2,
): Pair<List<Pair<Int, List<Int>>>, Int> {
    fun internalMakeGraph(
        current: Index2,
        graph: MutableMap<Index2, List<Index2>>,
    ) {
        if (current in graph) return

        val children = getChildrenNoSlopes(map, current)
        graph[current] = children
        children.forEach { internalMakeGraph(it, graph) }
    }

    val graph = mutableMapOf<Index2, List<Index2>>()
    internalMakeGraph(start, graph)
    return shortenGraph(graph)
}

private fun longestHike(
    graph: List<Pair<Int, List<Int>>>,
    end: Int,
    current: Int,
    visited: MutableSet<Int>,
): Int? {
    if (current == end) return graph[current].first - 1

    if (current in visited) return null
    visited += current

    return graph[current].second.asSequence()
        .mapNotNull { longestHike(graph, end, it, visited) }
        .maxOrNull()
        ?.plus(graph[current].first)
        .also { visited -= current }
}

private fun getChildrenNoSlopes(map: List<List<PathTile>>, current: Index2): List<Index2> =
    Direction.entries.asSequence()
        .map { current + it.index2 }
        .filter { map.getOrNull(it) != null && map[it] != PathTile.Forest }
        .toList()

private fun part1(
    map: List<List<PathTile>>,
    current: Index2,
    visited: MutableSet<Index2> = mutableSetOf(),
): Int? {
    if (current.i == map.lastIndex) return 0
    if (current in visited) return null

    val currentVisited = mutableSetOf(current)
    var next = listOf(current)
    var distance = 0

    while (next.size == 1) {
        val i = next.single()
        if (i.i == map.lastIndex) {
            visited -= currentVisited
            return distance
        }

        currentVisited += i
        distance++
        visited += i

        next = when (val tile = map[i]) {
            is PathTile.Slope -> listOf(i + tile.direction.index2)
            else -> getChildrenNoSlopes(map, i)
        }.filter { it !in visited }
    }

    val result = next.asSequence().mapNotNull { part1(map, it, visited) }.maxOrNull()?.plus(distance)
    visited -= currentVisited
    return result
}

private fun part2(map: List<List<PathTile>>, start: Index2): Int? {
    val (graph, end) = makeGraph(map, start)
    return longestHike(graph, end, 0, mutableSetOf())
}

fun main() {
    val map = mapLines { line ->
        line.map {
            when (it) {
                '.' -> PathTile.Path
                '#' -> PathTile.Forest
                '^' -> PathTile.Slope(Direction.Up)
                '>' -> PathTile.Slope(Direction.Right)
                '<' -> PathTile.Slope(Direction.Left)
                'v' -> PathTile.Slope(Direction.Down)
                else -> expect()
            }
        }
    }

    val start = Index2(0, 1)
    val first = part1(map, start)
    val second = part2(map, start)

    println("$first $second")
}
