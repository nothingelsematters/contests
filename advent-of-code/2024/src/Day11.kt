import kotlin.math.pow

private fun <K> MutableMap<K, Long>.addWithDefault(key: K, value: Long) {
    this[key] = this.getOrDefault(key, 0) + value
}

private fun oneBlink(stones: Map<Long, Long>): Map<Long, Long> = buildMap {
    for ((stone, count) in stones) {
        val digits = stone.toString().length

        when {
            stone == 0L -> addWithDefault(1, count)
            digits % 2 == 0 -> {
                val half = 10.0.pow(digits / 2).toInt()
                addWithDefault(stone / half, count)
                addWithDefault(stone % half, count)
            }
            else -> addWithDefault(stone * 2024, count)
        }
    }
}

private fun blink(stones: Map<Long, Long>, times: Int): Long =
    (1..times).fold(stones) { acc, _ -> oneBlink(acc) }.values.sum()

fun main() {
    val stones = readln().toLongs().groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    val first = blink(stones, 25)
    val second = blink(stones, 75)

    println("$first $second")
}
