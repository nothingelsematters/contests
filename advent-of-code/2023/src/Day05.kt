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
                            resultRanges += current - source.start + destination
                            sequenceOf()
                        }

                        source in current -> {
                            resultRanges += Interval(0, source.size) + destination
                            sequenceOf(
                                Interval(current.start, source.start - 1),
                                Interval(source.end + 1, current.end),
                            )
                        }

                        current.start in source -> {
                            resultRanges += Interval(current.start - source.start, source.size) + destination
                            sequenceOf(Interval(source.end + 1, current.end))
                        }

                        current.end in source -> {
                            resultRanges += Interval(0, current.end - source.start) + destination
                            sequenceOf(Interval(current.start, source.start - 1))
                        }

                        else -> sequenceOf(current)
                    }

                }
            }

            resultRanges + leftRanges
        }
    }.minOf { it.start }

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
