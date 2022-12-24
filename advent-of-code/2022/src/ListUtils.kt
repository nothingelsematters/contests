fun <T> List<T>.toPair() = component1() to component2()

operator fun <T> List<T>.get(range: IntRange) = subList(range.first, range.last + 1)

fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? =
    indexOfFirst(predicate).let { if (it == -1) null else it }

// Two dimensional utilities

data class InnerIndexedValue<T>(val i: Int, val j: Int, val value: T)

fun <T> Iterable<Iterable<T>>.innerIndexedSequence(): Sequence<InnerIndexedValue<T>> =
    asSequence().flatMapIndexed { i, row -> row.mapIndexed { j, value -> InnerIndexedValue(i, j, value) } }

operator fun <T> List<List<T>>.get(point: Point): T = this[point.x][point.y]

fun <T> List<List<T>>.getOrNull(point: Point): T? = this.getOrNull(point.x)?.getOrNull(point.y)

operator fun <T> List<MutableList<T>>.set(point: Point, t: T) {
    this[point.x][point.y] = t
}

infix fun Long.within(size: Int): Int {
    var index = this
    if (index < 0) {
        index += size * (-index / size + 1)
    }
    return (index % size).toInt()
}

/**
 * Mapping of every Int into [0..<size] to use it as an index.
 */
infix fun Int.within(size: Int): Int = toLong() within size

fun <T> List<T>.getModulo(index: Int) = get(index within size)
