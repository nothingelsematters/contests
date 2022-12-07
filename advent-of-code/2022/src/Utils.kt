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

fun String.toInts(vararg delimiters: String = arrayOf(" ", ",")): List<Int> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toInt() }.toList()

// Error handling utilities

fun <T> T?.unwrap(): T = this ?: expect()

fun expect(): Nothing = error("Invalid input / Unreachable")

// Other useful

fun <T> List<T>.toPair() = component1() to component2()

operator fun <T> List<T>.get(range: IntRange) = subList(range.first, range.last + 1)
