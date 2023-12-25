private fun findPath(
    wires: Map<String, Collection<String>>,
    current: String,
    end: String,
    visitedEdges: MutableList<String> = mutableListOf(),
    visitedVertices: MutableSet<String> = mutableSetOf(),
): Boolean {
    if (current in visitedVertices) return false
    visitedVertices += current
    visitedEdges += current

    if (current == end) return true

    wires[current]?.forEach {
        if (findPath(wires, it, end, visitedEdges, visitedVertices)) return true
    }

    visitedEdges.removeLast()
    return false
}

private fun traverse(wires: Map<String, Collection<String>>, current: String, visited: MutableSet<String>) {
    if (current in visited) return
    visited += current
    wires[current]?.forEach { traverse(wires, it, visited) }
}

private fun findThreeBridges(wires: Map<String, Set<String>>): Int {
    val sameComponent = mutableSetOf<Pair<String, String>>()

    wires.keys.forEachIndexed { i, start ->
        wires.keys.asSequence().take(i).forEachIndexed { j, end ->
            val currentWires = wires.mapValues { it.value.toMutableSet() }.toMutableMap()

            repeat(3) {
                val visitedEdges = mutableListOf<String>()
                findPath(currentWires, start, end, visitedEdges)

                visitedEdges.asSequence().zipWithNext().forEach { (f, t) ->
                    currentWires[f]!! -= t
                    currentWires[t]!! -= f
                }
            }

            if (findPath(currentWires, start, end)) sameComponent += start to end
        }
    }

    val components = buildMap<String, MutableList<String>> {
        sameComponent.forEach { (f, t) ->
            getOrPut(f) { mutableListOf() } += t
            getOrPut(t) { mutableListOf() } += f
        }
    }
    val visited = mutableSetOf<String>()

    return components.keys.asSequence()
        .map {
            val prevSize = visited.size
            traverse(components, it, visited)
            visited.size - prevSize
        }
        .filter { it != 0 }
        .toList()
        .let { (a, b) -> a * b }
}

fun main() {
    val wires: Map<String, Set<String>> = buildMap<String, MutableSet<String>> {
        lines().forEach { line ->
            val (left, rights) = line.split(": ")
            rights.splitToSequence(' ').forEach { right ->
                getOrPut(left) { mutableSetOf() } += right
                getOrPut(right) { mutableSetOf() } += left
            }
        }
    }

    println(findThreeBridges(wires))
}
