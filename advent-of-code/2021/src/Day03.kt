private fun List<String>.countOnes(position: Int) = count { it[position] == '1' }

private fun part1(numbers: List<String>): Int {
    val gamma = numbers[0].indices
        .asSequence()
        .map { numbers.countOnes(it) }
        .map { if (it > numbers.size / 2) 1 else 0 }
        .fold(0) { result, i -> (result shl 1) or i  }

    val epsilon = gamma.inv() and ((1 shl numbers[0].length) - 1)
    return gamma * epsilon
}

private fun part2(numbers: List<String>): Int {

    fun ratingWith(keep: Boolean): Int {
        val currentNumbers = numbers.toMutableList()

        for (i in numbers[0].indices) {
            if (currentNumbers.size == 1) break
            val ones = currentNumbers.countOnes(i)
            currentNumbers.removeIf { (it[i] == '1') == (keep == ones < currentNumbers.size - ones) }
        }

        return currentNumbers[0].toInt(2)
    }

    val oxygenRating = ratingWith(false)
    val co2Rating = ratingWith(true)

    return oxygenRating * co2Rating
}

fun main() {
    val lines = mapLines { it }
    println(part1(lines))
    println(part2(lines))
}
