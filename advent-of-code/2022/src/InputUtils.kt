private fun inputBufferedReader() = System.`in`.bufferedReader()

fun getFullInput(): String = inputBufferedReader().readText()

fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun <T> mapBlocks(mapper: (List<String>) -> T): MutableList<T> =
    getFullInput()
        .trim('\n')
        .splitToSequence("\n\n")
        .map { mapper(it.lines()) }
        .toMutableList()

// Parsing utilities

fun String.toInts(vararg delimiters: String = arrayOf(" ", ",")): MutableList<Int> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()

fun String.toLongs(vararg delimiters: String = arrayOf(" ", ",")): MutableList<Long> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toLong() }.toMutableList()

fun String.toOperation(): (Long, Long) -> Long = when (this) {
    "+" -> Long::plus
    "-" -> Long::minus
    "*" -> Long::times
    "/" -> Long::div
    else -> expect()
}

// Error handling utilities

fun <T> T?.unwrap(): T = this ?: expect()

fun expect(): Nothing = error("Invalid input / Unreachable")
