private fun countAntinodes(
    field: List<List<Char>>,
    antennas: Collection<List<Index2>>,
    generateAntinodes: (Index2, Index2) -> List<Index2>,
): Int =
    antennas.asSequence()
        .flatMap { indices ->
            indices.asSequence()
                .flatMapIndexed { i, index1 ->
                    indices.asSequence()
                        .take(i)
                        .flatMap { index2 -> generateAntinodes(index1, index2) }
                        .filter { field.getOrNull(it) != null }
                }
        }
        .distinct()
        .count()

private fun generateAntinodes(field: List<List<Char>>, antenna1: Index2, antenna2: Index2): List<Index2> {
    val next = antenna1 * 2 - antenna2
    if (field.getOrNull(next) == null) return listOf(antenna1)

    return generateAntinodes(field, next, antenna1) + antenna1
}

fun main() {
    val field = mapLines { it.toList() }

    val antennas = field.indexed2Sequence()
        .groupBy({ it.value }, { it.index2 })
        .minus('.')
        .values

    val first = countAntinodes(field, antennas) { a, b -> listOf(a * 2 - b, b * 2 - a) }
    val second = countAntinodes(field, antennas) { a, b -> generateAntinodes(field, a, b) + generateAntinodes(field, b, a) }

    println("$first $second")
}
