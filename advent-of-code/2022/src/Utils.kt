// Input reading utilities

private fun inputBufferedReader() = System.`in`.bufferedReader()

fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun <T> withBlocks(transformer: (List<String>) -> T): MutableList<T> =
    inputBufferedReader()
        .readText()
        .trim('\n')
        .splitToSequence("\n\n")
        .map { transformer(it.lines()) }
        .toMutableList()

fun String.toInts(vararg delimiters: String = arrayOf(" ", ",")): List<Int> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toInt() }.toList()

// Other useful

fun <T> T?.expect(): T = this ?: error("Invalid input / Unreachable")

fun <T> List<T>.toPair() = component1() to component2()

operator fun <T> List<T>.get(range: IntRange) = subList(range.first, range.last + 1)
