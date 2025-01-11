private fun countTokens(machines: List<List<Pair<Long, Long>>>): Long =
    machines.sumOf { (a, b, prize) ->
        val y = (prize.second * a.first - prize.first * a.second) / ((b.second * a.first) - (b.first * a.second))
        val x = (prize.first - b.first * y) / a.first
        if (a.first * x + b.first * y != prize.first || a.second * x + b.second * y != prize.second) 0 else x * 3 + y
    }

fun main() {
    val machines = mapBlocks { block ->
        block.map { string ->
            """(Button [AB]|Prize): X[+=](\d+), Y[+=](\d+)""".toRegex()
                .matchEntire(string)!!
                .groupValues
                .let { it[2].toLong() to it[3].toLong() }
        }
    }

    val first = countTokens(machines)
    val second = machines
        .map { machine ->
            machine.toMutableList().also { it[2] = it[2].toList().map { it + 10000000000000 }.toPair() }
        }
        .let(::countTokens)

    println("$first $second")
}
