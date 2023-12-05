private data class Interval(val first: Long, val last: Long = first) {

    val size = last - first + 1

    operator fun plus(rhs: Long): Interval = Interval(first + rhs, last + rhs)

    operator fun minus(rhs: Long): Interval = Interval(first - rhs, last - rhs)

    operator fun contains(rhs: Long) = rhs in first..last

    operator fun contains(rhs: Interval) = rhs.first in first..last && rhs.last in first..last
}

private fun foldMappings(
    maps: List<List<Pair<Long, Interval>>>,
    initialIntervals: List<Interval>,
): Long =
    maps.fold(initialIntervals) { intervals, mappings ->
        intervals.flatMap { initialFromRange ->
            val resultRanges = mutableListOf<Interval>()

            val leftRanges = mappings.fold(listOf(initialFromRange)) { fromRanges, (destination, source) ->
                fromRanges.flatMap { current ->
                    when {
                        current in source -> {
                            resultRanges += current - source.first + destination
                            sequenceOf()
                        }

                        source in current -> {
                            resultRanges += Interval(0, source.size) + destination
                            sequenceOf(
                                Interval(current.first, source.first - 1),
                                Interval(source.last + 1, current.last),
                            )
                        }

                        current.first in source -> {
                            resultRanges += Interval(current.first - source.first, source.size) + destination
                            sequenceOf(Interval(source.last + 1, current.last))
                        }

                        current.last in source -> {
                            resultRanges += Interval(0, current.last - source.first) + destination
                            sequenceOf(Interval(current.first, source.first - 1))
                        }

                        else -> sequenceOf(current)
                    }

                }
            }

            resultRanges + leftRanges
        }
    }.minOf { it.first }

fun main() {
    val (seedsString, mapString) = getFullInput().split("\n\n", limit = 2)
    val seeds = seedsString.substringAfter(":").toLongs()
    val maps = mapString.splitToSequence("\n\n")
        .map { block ->
            block.lines()
                .asSequence()
                .drop(1)
                .map {
                    val (destination, source, range) = it.toLongs()
                    destination to Interval(source, source + range - 1)
                }
                .toList()
        }
        .toList()

    val first = foldMappings(maps, seeds.map { Interval(it) })

    val seedIntervals = seeds.asSequence().chunked(2).map { (a, b) -> Interval(a, a + b - 1) }.toList()
    val second = foldMappings(maps, seedIntervals)

    println("$first $second")
}
