fun <T> List<T>.toPair() = component1() to component2()

fun <T> List<T>.indexOfOrNull(element: T): Int? {
    val index = indexOf(element)
    return if (index == -1) null else index
}

fun <T> Sequence<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
    val index = indexOfFirst(predicate)
    return if (index == -1) null else index
}

// Two dimensional

fun <T> List<List<T>>.deepCopy(): List<List<T>> = map { it.toList() }

fun <T> List<List<T>>.mutableDeepCopy(): List<MutableList<T>> = map { it.toMutableList() }

// Two dimensional. Index

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

val <T> List<List<T>>.lastIndex2: Index2
    get() = Index2(lastIndex, last().lastIndex)

data class Indexed2Value<T>(val index2: Index2, val value: T)

fun <T> Iterable<Iterable<T>>.indexed2Sequence(): Sequence<Indexed2Value<T>> =
    asSequence().flatMapIndexed { i, row ->
        row.mapIndexed { j, value -> Indexed2Value(Index2(i, j), value) }
    }
