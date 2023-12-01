fun <T> List<T>.toPair() = component1() to component2()

operator fun <T> List<T>.get(range: IntRange) = subList(range.first, range.last + 1)

// Two dimensional utilities

data class InnerIndexedValue<T>(val i: Int, val j: Int, val value: T)

fun <T> Iterable<Iterable<T>>.innerIndexedSequence(): Sequence<InnerIndexedValue<T>> =
    asSequence().flatMapIndexed { i, row -> row.mapIndexed { j, value -> InnerIndexedValue(i, j, value) } }
