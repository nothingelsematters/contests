const val PREAMBLE_SIZE = 25

fun <T> T?.expect(): T = this ?: throw IllegalArgumentException("failed to find weakness")

fun main() {
    val numbers = mapLines { it.toLong() }

    val first = numbers.withIndex()
        .drop(PREAMBLE_SIZE)
        .find { (i, number) ->
            val range = numbers.slice(i - PREAMBLE_SIZE until i)
            range.all { j -> range.all { k -> j + k != number } }
        }
        .expect()
        .value

    var i = 0
    var j = 0
    var sum = 0L

    while (sum != first && j != numbers.size) {
        while (sum < first) sum += numbers[j++]
        if (sum > first) sum -= numbers[i++]
    }

    val range = numbers.slice(i until j)
    val second = range.maxOrNull().expect() + range.minOrNull().expect()

    println("$first $second")
}
