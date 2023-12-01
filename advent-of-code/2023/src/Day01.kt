private fun findCalibrationValue(lines: List<String>, words: List<String>): Int =
    lines.sumOf {
        val left = it.findAnyOf(words).unwrap().second
        val right = it.findLastAnyOf(words).unwrap().second
        words.indexOf(left) % 10 * 10 + words.indexOf(right) % 10
    }

fun main() {
    val digits = (0..9).map { it.toString() }
    val digitsAndWords = digits + listOf(
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
    )

    val lines = mapLines { it }
    val first = findCalibrationValue(lines, digits)
    val second = findCalibrationValue(lines, digitsAndWords)

    println("$first $second")
}
