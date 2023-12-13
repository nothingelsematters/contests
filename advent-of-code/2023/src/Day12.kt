private fun countPermutations(arrangement: String, values: List<Int>): Long {
    data class Index3(val i: Int, val j: Int, val k: Int)

    fun innerCount(i: Int, j: Int, sharps: Int, memory: MutableMap<Index3, Long>): Long {
        if (i >= arrangement.length) {
            return if (j >= values.size || j == values.size - 1 && sharps == values[j]) 1 else 0
        }

        return memory.getOrPut(Index3(i, j, sharps)) {
            val char = arrangement[i]
            var result = 0L

            if ((char == '#' || char == '?') && j < values.size && sharps < values[j]) {
                result += innerCount(i + 1, j, sharps + 1, memory)
            }

            if (char == '.' || char == '?') {
                if (sharps > 0 && values[j] == sharps) {
                    result += innerCount(i + 1, j + 1, 0, memory)
                } else if (sharps == 0) {
                    result += innerCount(i + 1, j, 0, memory)
                }
            }

            result
        }
    }

    return innerCount(0, 0, 0, mutableMapOf())
}

fun main() {
    val rows = mapLines {
        val (arrangement, valuesString) = it.split(" ")
        arrangement to valuesString.toInts()
    }

    val first = rows.sumOf { (arrangement, values) -> countPermutations(arrangement, values) }

    val second = rows.sumOf { (arrangement, values) ->
        countPermutations(
            List(5) { arrangement }.joinToString("?"),
            List(5) { values }.flatten(),
        )
    }

    println("$first $second")
}
