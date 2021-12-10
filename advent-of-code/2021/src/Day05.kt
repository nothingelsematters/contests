private data class Line(val fromX: Int, val fromY: Int, val toX: Int, val toY: Int)

private infix fun Int.rangeTo(to: Int) = if (this >= to) to..this else (this..to).reversed()

private fun <K> MutableMap<K, Int>.increment(key: K) = merge(key, 1, Int::plus)

private fun findOverlapping(lines: List<Line>, useDiagonal: Boolean): Int {
    val covered = mutableMapOf<Pair<Int, Int>, Int>()

    for (line in lines) {
        when {
            line.fromX == line.toX -> (line.fromY rangeTo line.toY).forEach { covered.increment(line.fromX to it) }
            line.fromY == line.toY -> (line.fromX rangeTo line.toX).forEach { covered.increment(it to line.fromY) }
            useDiagonal -> (line.fromX rangeTo line.toX)
                .zip(line.fromY rangeTo line.toY)
                .forEach { covered.increment(it) }
        }
    }

    return covered.values.count { it > 1 }
}

fun main() {
    val lines = mapLines {
        val (fromX, fromY, toX, toY) = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex().matchEntire(it).expect().destructured
        Line(fromX.toInt(), fromY.toInt(), toX.toInt(), toY.toInt())
    }

    println(findOverlapping(lines, false))
    println(findOverlapping(lines, true))
}
