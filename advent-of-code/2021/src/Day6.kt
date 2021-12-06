private fun getPopulationSize(timers: Map<Int, Long>, days: Int): Long {
    var currentTimers = timers.toMutableMap()

    repeat(days) {
        val new = currentTimers[0] ?: 0L

        currentTimers = currentTimers
            .asSequence()
            .filter { it.key != 0 }
            .map { (key, value) -> key - 1 to value }
            .toMap()
            .toMutableMap()

        currentTimers[8] = new
        currentTimers.merge(6, new, Long::plus)
    }

    return currentTimers.values.sum()
}

fun main() {
    val timers = readln()
        .toInts(",")
        .groupBy { it }
        .map { (key, value) -> key to value.size.toLong() }
        .toMap()

    println(getPopulationSize(timers, 80))
    println(getPopulationSize(timers, 256))
}
