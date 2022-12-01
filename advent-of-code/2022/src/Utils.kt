// Input reading utilities

private fun inputBufferedReader() = System.`in`.bufferedReader()

fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun lines() = mapLines { it }

fun <T> mapBlocks(mapper: (String) -> T): MutableList<List<T>> =
    inputBufferedReader()
        .readText()
        .trim('\n')
        .splitToSequence("\n\n")
        .map { it.lineSequence().map(mapper).toList() }
        .toMutableList()

fun <T> withBlocks(transformer: (List<String>) -> T): MutableList<T> =
    inputBufferedReader()
        .readText()
        .trim('\n')
        .splitToSequence("\n\n")
        .map { transformer(it.lines()) }
        .toMutableList()

fun String.toInts(vararg delimiters: String = arrayOf(" ", ",")): List<Int> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toInt() }.toList()

// Error handling utilities

fun <T> T?.expect(): T = this ?: error("Invalid input")

fun expect(): Nothing = error("Invalid Input")

// Other useful

operator fun <T> List<T>.get(range: IntRange) = subList(range.first, range.last + 1)
