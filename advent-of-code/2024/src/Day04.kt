fun main() {
    val text = mapLines { it.toList() }

    val diagonals = listOf(Index2(-1, -1), Index2(-1, 1), Index2(1, 1), Index2(1, -1))

    val first = text.indexed2Sequence()
        .sumOf { (index, _) ->
            Direction.entries.asSequence()
                .map { it.point }
                .plus(diagonals)
                .count { direction ->
                    "XMAS".asSequence().withIndex().all { (i, char) ->
                        char == text.getOrNull(index + direction * i)
                    }
                }
        }

    val second = text.indexed2Sequence()
        .filter { (_, char) -> char == 'A' }
        .count { (index, _) ->
            diagonals.asSequence()
                .plus(diagonals.first())
                .windowed(2)
                .any { directions ->
                    directions.all { direction ->
                        'M' == text.getOrNull(index + direction)
                            && 'S' == text.getOrNull(index + direction * -1)
                    }
                }
        }

    println("$first $second")
}
