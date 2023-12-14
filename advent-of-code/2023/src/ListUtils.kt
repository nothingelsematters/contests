fun <T> List<T>.toPair() = component1() to component2()

// Two dimensional utilities

fun <T> List<List<T>>.deepCopy(): List<List<T>> = map { it.toList() }

data class Index2(val i: Int, val j: Int) {

    operator fun plus(rhs: Index2) = Index2(i + rhs.i, j + rhs.j)
}

operator fun <T> List<List<T>>.get(index: Index2) = this[index.i][index.j]

operator fun <T> List<MutableList<T>>.set(index: Index2, value: T) {
    this[index.i][index.j] = value
}

fun <T> List<List<T>>.getOrNull(index: Index2) = getOrNull(index.i)?.getOrNull(index.j)

data class Indexed2Value<T>(val index2: Index2, val value: T)

fun <T> Iterable<Iterable<T>>.innerIndexedSequence(): Sequence<Indexed2Value<T>> =
    asSequence().flatMapIndexed { i, row ->
        row.mapIndexed { j, value -> Indexed2Value(Index2(i, j), value) }
    }
