private fun <T> Sequence<Pair<T, T>>.allEquals(): Boolean = all { (a, b) -> a == b }

private fun <T> equalsColumns(block: List<List<T>>, i: Int, j: Int): Boolean =
    block.asSequence()
        .map { it[i] }
        .zip(block.asSequence().map { it[j] })
        .allEquals()

private fun findReflectionLineScore(block: List<List<Boolean>>): Sequence<Int> {
    val horizontal = block.asSequence()
        .withIndex()
        .zipWithNext()
        .filter { (t, b) -> t.value == b.value }
        .map { it.second.index }
        .filter {
            block.subList(0, it)
                .asReversed()
                .asSequence()
                .zip(block.subList(it, block.size).asSequence())
                .allEquals()
        }
        .map { it * 100 }

    val vertical = block.first().indices.asSequence()
        .zipWithNext()
        .filter { (l, r) -> equalsColumns(block, l, r) }
        .map { it.first }
        .filter { index ->
            (0..index).reversed().asSequence()
                .zip((index + 1..<block.first().size).asSequence())
                .all { (a, b) -> equalsColumns(block, a, b) }
        }
        .map { it + 1 }

    return horizontal + vertical
}

fun main() {
    val rockBlocks = mapBlocks { block -> block.map { line -> line.map { it == '#' } } }

    val first = rockBlocks.sumOf { findReflectionLineScore(it).single() }

    val second = rockBlocks.sumOf { block ->
        val initial = findReflectionLineScore(block).single()

        block.indexed2Sequence()
            .flatMap { (index, _) ->
                val copy = block.mutableDeepCopy()
                copy[index] = !copy[index]
                findReflectionLineScore(copy)
            }
            .filter { it != initial }
            .first()
    }

    println("$first $second")
}
