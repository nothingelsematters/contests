private fun part1(directions: List<Boolean>, connections: Map<String, Pair<String, String>>): Int {
    var steps = 0
    var current = "AAA"

    while (current != "ZZZ") {
        val (left, right) = connections[current]!!
        current = if (directions[steps % directions.size]) left else right
        steps++
    }

    return steps
}

private fun part2(directions: List<Boolean>, connections: Map<String, Pair<String, String>>): Long {
    val cycles = connections.keys.asSequence()
        .filter { it.endsWith("A") }
        .map {
            val visited = mutableMapOf<Pair<String, Int>, Int>()
            var current = it

            while (true) {
                val index = visited.size % directions.size

                if (current to index in visited) break
                visited[current to index] = visited.size

                val (left, right) = connections[current]!!
                current = if (directions[index]) left else right
            }

            visited.size - visited[current to visited.size % directions.size]!!
        }
        .toList()

    return cycles.lowestCommonMultiplier()
}

fun main() {
    val (directionString, connectionsStrings) = mapBlocks { it }
    val directions = directionString.single().map { it == 'L' }
    val connections = connectionsStrings.asSequence()
        .map { it.substring(0, 3) to (it.substring(7, 10) to it.substring(12, 15)) }
        .toMap()

    val first = part1(directions, connections)
    val second = part2(directions, connections)

    println("$first $second")
}
