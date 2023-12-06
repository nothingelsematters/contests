private fun countWinningWays(time: Long, distance: Long): Int =
    (1..<time).count { it * (time - it) > distance }

fun main() {
    val (timeStrings, distanceStrings) = mapLines { it.substringAfter(":") }

    val first = timeStrings.toLongs().asSequence()
        .zip(distanceStrings.toLongs().asSequence())
        .map { (t, d) -> countWinningWays(t, d) }
        .reduce(Int::times)

    val second = countWinningWays(
        timeStrings.replace(" ", "").toLong(),
        distanceStrings.replace(" ", "").toLong(),
    )

    println("$first $second")
}
