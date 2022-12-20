private fun decrypt(originalNumbers: List<Long>, iterations: Int): Long {
    val numbers = originalNumbers.asSequence().withIndex().toMutableList()

    repeat(iterations) {
        repeat(numbers.size) { i ->
            val index = numbers.indexOfFirst { it.index == i }

            val (_, number) = numbers.removeAt(index)
            var newIndex = index + number
            if (newIndex < 0) {
                newIndex += numbers.size * (-newIndex / numbers.size + 1)
            }
            newIndex %= numbers.size

            numbers.add(newIndex.toInt(), IndexedValue(i, number))
        }
    }

    val zeroIndex = numbers.indexOfFirst { it.value == 0L }
    return sequenceOf(1000, 2000, 3000).sumOf { numbers[(zeroIndex + it) % numbers.size].value }
}

fun main() {
    val numbers = mapLines { it.toLong() }

    println(decrypt(numbers, 1))
    println(decrypt(numbers.map { it * 811589153 }, 10))
}
