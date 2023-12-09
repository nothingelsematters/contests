private fun extrapolationSequences(logs: List<Int>): List<List<Int>> {
    val sequences = mutableListOf(logs)

    while (sequences.last().any { it != 0 }) {
        sequences += sequences.last().asSequence().zipWithNext { a, b -> b - a }.toList()
    }

    return sequences
}

fun main() {
    val history = mapLines { it.toInts() }

    val first = history.sumOf { logs ->
        extrapolationSequences(logs).sumOf { it.last() }
    }

    val second = history.sumOf { logs ->
        extrapolationSequences(logs).foldRight(0L) { sequence, acc ->
            sequence.first() - acc
        }
    }

    println("$first $second")
}
