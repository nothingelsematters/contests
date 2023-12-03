private infix fun IntRange.cartesian(other: IntRange): Sequence<Pair<Int, Int>> =
    asSequence().flatMap { f -> other.asSequence().map { s -> f to s } }

private fun processNumbers(
    lines: List<String>,
    adjacentCondition: (Char) -> Boolean,
    action: (Int, Pair<Int, Int>) -> Unit,
) {
    lines.forEachIndexed { i, line ->
        var j = -1

        while (++j < line.length) {
            if (!line[j].isDigit()) continue

            val numberString = line.substring(j).takeWhile { it.isDigit() }

            val symbol = (i - 1..i + 1 cartesian j - 1..j + numberString.length)
                .find { (a, b) -> lines.getOrNull(a)?.getOrNull(b)?.let(adjacentCondition) == true }
                ?: continue
            action(numberString.toInt(), symbol)

            j += numberString.length - 1
        }
    }
}

fun main() {
    val lines = lines()

    var first = 0
    processNumbers(lines, { !it.isDigit() && it != '.' }) { number, _ -> first += number }

    val gears = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
    processNumbers(lines, { it == '*' }) { number, symbol ->
        gears[symbol] = gears[symbol]?.let { (n, m) -> n + 1 to m * number } ?: (1 to number)
    }
    val second = gears.values.asSequence().filter { it.first == 2 }.sumOf { it.second }

    println("$first $second")
}
