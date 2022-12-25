import kotlin.math.abs

private fun part1(cubes: List<List<Int>>): Int =
    cubes.size * 6 - 2 * cubes.asSequence().withIndex().sumOf { (index, i) ->
        cubes.subList(index + 1, cubes.size).count { j ->
            i.asSequence().zip(j.asSequence()).count { (a, b) -> a == b } == 2
                && i.asSequence().zip(j.asSequence()).filter { (a, b) -> a != b }.first()
                .let { (a, b) -> abs(a - b) == 1 }
        }
    }

private fun part2(cubes: List<List<Int>>): Int {
    val cubeSet = cubes.toSet()
    val maxes = (0..2).map { index -> listOf(cubes.minOf { it[index] }, cubes.maxOf { it[index] }) }

    return cubes.sumOf { startingCube ->
        (0..2).sumOf { coordinate ->
            sequenceOf(-1, 1).count { diff ->
                val newCube = startingCube.toMutableList().also { it[coordinate] += diff }

                if (newCube in cubeSet) return@count false

                val used = mutableSetOf(newCube)
                val queue = ArrayDeque(listOf(newCube))

                while (queue.isNotEmpty()) {
                    val currentCube = queue.removeFirst()
                    if (currentCube.asSequence().zip(maxes.asSequence()).any { (c, m) -> c in m }) return@count true

                    (0..2).forEach { coordinate ->
                        sequenceOf(-1, 1)
                            .map { diff -> currentCube.toMutableList().also { it[coordinate] += diff } }
                            .filter { it !in used && it !in cubeSet }
                            .forEach {
                                used += it
                                queue += it
                            }
                    }
                }

                return@count false
            }
        }
    }
}

fun main() {
    val cubes = mapLines { it.toInts() }
    println(part1(cubes))
    println(part2(cubes))
}
