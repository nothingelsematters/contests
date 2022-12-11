// Input reading utilities

private fun inputBufferedReader() = System.`in`.bufferedReader()

fun getFullInput(): String = inputBufferedReader().readText()

fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun <T> mapBlocks(transformer: (List<String>) -> T): MutableList<T> =
    inputBufferedReader()
        .readText()
        .trim('\n')
        .splitToSequence("\n\n")
        .map { transformer(it.lines()) }
        .toMutableList()

// Parsing utilities

fun String.toInts(vararg delimiters: String = arrayOf(" ", ",")): MutableList<Int> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()

fun String.toLongs(vararg delimiters: String = arrayOf(" ", ",")): MutableList<Long> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toLong() }.toMutableList()

// Error handling utilities

fun <T> T?.unwrap(): T = this ?: expect()

fun expect(): Nothing = error("Invalid input / Unreachable")

// List utilities

fun <T> List<T>.toPair() = component1() to component2()

operator fun <T> List<T>.get(range: IntRange) = subList(range.first, range.last + 1)

fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? =
    indexOfFirst(predicate).let { if (it == -1) null else it }

// Other useful

data class Point(var x: Int, var y: Int) {

    override fun toString() = "($x, $y)"

    operator fun plus(rhs: Point) = Point(x + rhs.x, y + rhs.y)

    operator fun minus(rhs: Point) = Point(x - rhs.x, y - rhs.y)
}
