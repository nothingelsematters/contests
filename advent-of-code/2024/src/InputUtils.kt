// Reading

private fun inputBufferedReader() = System.`in`.bufferedReader()

fun getFullInput(): String = inputBufferedReader().readText().trimEnd()

fun lines(): List<String> = mapLines { it }

fun blocks(): List<List<String>> = mapBlocks { it }

fun <T> mapLines(mapper: (String) -> T): List<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun <T> mapLinesIndexed(mapper: (IndexedValue<String>) -> T): List<T> =
    inputBufferedReader().useLines { lines -> lines.withIndex().map(mapper).toMutableList() }

fun <T> mapBlocks(mapper: (List<String>) -> T): List<T> =
    getFullInput().splitToSequence("\n\n").map { mapper(it.lines()) }.toMutableList()

// Parsing

fun String.toInts(vararg delimiters: String = arrayOf(" ", ",")): List<Int> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toInt() }.toMutableList()

fun String.toLongs(vararg delimiters: String = arrayOf(" ", ",")): List<Long> =
    splitToSequence(*delimiters).filter { it.isNotEmpty() }.map { it.toLong() }.toMutableList()

// Error handling

fun <T> T?.unwrap(): T = this ?: expect()

fun expect(): Nothing = error("Invalid input / Unreachable")
