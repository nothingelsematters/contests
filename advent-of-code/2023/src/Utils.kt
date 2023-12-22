// Sequence

fun <T> Sequence<T>.multiplicationOf(function: (T) -> Long): Long =
    fold(1L) { acc, i -> acc * function(i) }

fun <T> Iterable<T>.multiplicationOf(function: (T) -> Long): Long = asSequence().multiplicationOf(function)

fun <T> Sequence<T>.sumOfIndexed(selector: (Int, T) -> Int): Int =
    withIndex().sumOf { (i, it) -> selector(i, it) }

fun <T> Iterable<T>.sumOfIndexed(selector: (Int, T) -> Int): Int = asSequence().sumOfIndexed(selector)

fun <T> Array<T>.sumOfIndexed(selector: (Int, T) -> Int): Int = asSequence().sumOfIndexed(selector)

// Structures

enum class Direction(val index2: Index2) {
    Up(Index2(-1, 0)),
    Down(Index2(1, 0)),
    Left(Index2(0, -1)),
    Right(Index2(0, 1));

    fun reversed() = when (this) {
        Up -> Down
        Down -> Up
        Left -> Right
        Right -> Left
    }
}

data class Interval(override val start: Long, val end: Long = start) : ClosedRange<Long> {

    constructor(start: Int, end: Int = start) : this(start.toLong(), end.toLong())

    override val endInclusive = end

    val size = end - start + 1

    operator fun plus(other: Long): Interval = Interval(start + other, end + other)

    operator fun minus(other: Long): Interval = Interval(start - other, end - other)

    operator fun contains(other: Interval) = other.start in start..end && other.end in start..end

    override fun toString(): String = "[$start, $end]"

    infix fun intersect(other: Interval): Interval {
        if (this.start !in other && this.end !in other &&
            other.start !in this && other.end !in this
        ) return EMPTY

        return Interval(maxOf(start, other.start), minOf(end, other.end))
    }

    companion object {
        val EMPTY = Interval(0, -1)
    }
}

// Algorithms

/**
 * Eratosthenes Sieve
 */
private fun primes(n: Int): Set<Int> {
    val primes = (2..n).toMutableSet()

    for (i in 2..n) {
        if (i !in primes) continue
        primes.removeAll(2 * i..n step i)
    }

    return primes
}

fun List<Int>.lowestCommonMultiplier(): Long {
    val primes = primes(maxOrNull() ?: return 0)
    val multipliers = mutableMapOf<Int, Int>()

    for (i in this) {
        var current = i

        for (j in primes) {
            var count = 0
            while (current % j == 0) {
                count++
                current /= j
            }

            if (multipliers.getOrDefault(j, 0) < count) {
                multipliers[j] = count
            }
        }
    }

    return multipliers.asSequence()
        .multiplicationOf { (multiplier, number) -> multiplier.toLong() * number }
}
