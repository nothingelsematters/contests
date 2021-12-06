import java.util.Collections

private const val BREED_TIMER = 6
private const val BIRTH_TIMER = 8

private fun getPopulationSize(timers: List<Long>, days: Int): Long {
    val currentTimers = timers.toMutableList()

    repeat(days) {
        Collections.rotate(currentTimers, -1)
        currentTimers[BREED_TIMER] += currentTimers[BIRTH_TIMER]
    }

    return currentTimers.sum()
}

fun main() {
    val timers = readln().toInts(",")
    val timerNumbers = List(BIRTH_TIMER + 1) { index -> timers.count { it == index }.toLong() }

    println(getPopulationSize(timerNumbers, 80))
    println(getPopulationSize(timerNumbers, 256))
}
