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

// List utilities

val <T> List<List<T>>.innerIndices: Sequence<Pair<Int, Int>>
    get() = indices.asSequence().flatMap { i -> this[i].indices.map { j -> i to j } }

operator fun <T> List<List<T>>.get(index: Pair<Int, Int>) = this[index.first][index.second]

operator fun <T> List<MutableList<T>>.set(index: Pair<Int, Int>, value: T) {
    this[index.first][index.second] = value
}

fun <T> List<T>.toPair() = component1() to component2()

infix fun <F, S> List<F>.cartesian(second: List<S>): Sequence<Pair<F, S>> =
    asSequence().flatMap { f -> second.asSequence().map { s -> f to s } }

infix fun <F, S> Sequence<F>.cartesian(other: Sequence<S>): Sequence<Pair<F, S>> =
    flatMap { f -> other.map { s -> f to s } }

infix fun IntRange.cartesian(other: IntRange): Sequence<Pair<Int, Int>> =
    asSequence().flatMap { f -> other.asSequence().map { s -> f to s } }

fun cartesian(intRange: IntRange, vararg rest: IntRange): Sequence<List<Int>> =
    when (val first = rest.firstOrNull()) {
        null -> intRange.asSequence().map { listOf(it) }
        else -> {
            val newRest = rest.drop(1).toTypedArray()
            intRange.asSequence().flatMap { ir -> cartesian(first, *newRest).map { listOf(ir) + it } }
        }
    }

typealias Point = Pair<Int, Int>

// Bit vector utilities

fun Boolean.toInt() = if (this) 1 else 0

fun List<Boolean>.toInt() = fold(0) { acc, i -> (acc shl 1) or i.toInt() }
