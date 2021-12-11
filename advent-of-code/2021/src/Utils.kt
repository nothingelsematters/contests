// Input reading utilities

private fun inputBufferedReader() = System.`in`.bufferedReader()

fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun <T> mapBlocks(mapper: (String) -> T): MutableList<List<T>> =
    inputBufferedReader()
        .readText()
        .trim('\n')
        .splitToSequence("\n\n")
        .map { it.lineSequence().map(mapper).toList() }
        .toMutableList()

fun String.toInts(delimiter: String = " "): List<Int> =
    splitToSequence(delimiter).filter { it.isNotEmpty() }.map { it.toInt() }.toList()

// Error handling utilities

fun <T> T?.expect(): T = this ?: error("Invalid input")

fun expect(): Nothing = error("Invalid Input")

// List utilities

val <T> List<List<T>>.innerIndices: Sequence<Pair<Int, Int>>
    get() = indices.asSequence().flatMap { i -> this[i].indices.map { j -> i to j } }

operator fun <T> List<List<T>>.get(index: Pair<Int, Int>) = this[index.first][index.second]

operator fun <T> List<MutableList<T>>.set(index: Pair<Int, Int>, value: T) {
    this[index.first][index.second] = value
}
