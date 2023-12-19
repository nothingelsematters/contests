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

/**
 * Eratosthenes Sieve
 */
private fun primes(n: Int): Set<Int> {
    val primes = (2..n).toMutableSet()

    for (i in 2..n) {
        if (i !in primes) continue
        primes.removeAll(2 * i..n step i)
    }

    return primes
}

private fun lowestCommonMultiplier(ints: List<Int>): Long {
    val primes = primes(ints.max())
    val multipliers = mutableMapOf<Int, Int>()

    for (i in ints) {
        var current = i

        for (j in primes) {
            var count = 0
            while (current % j == 0) {
                count++
                current /= j
            }

            if (multipliers.getOrDefault(j, 0) < count) {
                multipliers[j] = count
            }
        }
    }

    return multipliers.asSequence().multiplicationOf { (multiplier, number) -> multiplier.toLong() * number }
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

    return lowestCommonMultiplier(cycles)
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
