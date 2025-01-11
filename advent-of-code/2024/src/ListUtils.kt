fun <T> List<T>.toPair() = component1() to component2()

fun <T> List<T>.indexOfOrNull(element: T): Int? {
    val index = indexOf(element)
    return if (index == -1) null else index
}

infix fun Int.within(size: Int): Int {
    var index = this
    if (index < 0) {
        index += size * (-index / size + 1)
    }
    return index % size
}

// Sequence

fun Sequence<Int>.product(): Int = fold(1) { acc, i -> acc * i }

// Two-dimensional

fun <T> List<List<T>>.mutableDeepCopy(): List<MutableList<T>> = map { it.toMutableList() }

// Two-dimensional. Index

typealias Index2 = Point

val Index2.i: Int
    get() = x

val Index2.j: Int
    get() = y

operator fun <T> List<List<T>>.get(index: Index2) = this[index.i][index.j]

operator fun <T> List<MutableList<T>>.set(index: Index2, value: T) {
    this[index.i][index.j] = value
}

fun <T> List<List<T>>.getOrNull(index: Index2): T? = getOrNull(index.i)?.getOrNull(index.j)

data class Indexed2Value<T>(val index2: Index2, val value: T)

fun <T> Iterable<Iterable<T>>.indexed2Sequence(): Sequence<Indexed2Value<T>> =
    asSequence().flatMapIndexed { i, row ->
        row.mapIndexed { j, value -> Indexed2Value(Index2(i, j), value) }
    }
