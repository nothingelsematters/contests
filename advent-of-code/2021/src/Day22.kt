import kotlin.math.max
import kotlin.math.min

private operator fun IntRange.contains(other: IntRange) = other.first >= first && other.last <= last

private data class Turning(val on: Boolean, val hyperRange: HyperRange)

private data class HyperRange(val x: IntRange, val y: IntRange, val z: IntRange) {

    val size = (x.last - x.first + 1).toLong() * (y.last - y.first + 1).toLong() * (z.last - z.first + 1).toLong()

    fun isEmpty() = x.isEmpty() || y.isEmpty() || z.isEmpty()

    operator fun minus(other: HyperRange): List<HyperRange> {
        val commonX = max(x.first, other.x.first)..min(x.last, other.x.last)
        val commonY = max(y.first, other.y.first)..min(y.last, other.y.last)

        return sequenceOf(
            HyperRange(x.first..other.x.first - 1, y, z),
            HyperRange(other.x.last + 1..x.last, y, z),
            HyperRange(commonX, y.first..other.y.first - 1, z),
            HyperRange(commonX, other.y.last + 1..y.last, z),
            HyperRange(commonX, commonY, z.first..other.z.first - 1),
            HyperRange(commonX, commonY, other.z.last + 1..z.last),
        )
            .filter { !it.isEmpty() && it != this }
            .toList()
    }

    infix fun intersects(other: HyperRange): Boolean =
        x intersects other.x && y intersects other.y && z intersects other.z

    override fun toString() = "[(${x.first}..${x.last}), (${y.first}..${y.last}), (${z.first}..${z.last})]"

    private infix fun IntRange.intersects(other: IntRange): Boolean = first in other || last in other || other in this
}

private fun getCoverage(turnings: List<Turning>): Long {
    var on = mutableListOf<HyperRange>()

    for ((isOn, newHyperRange) in turnings) {
        if (isOn) {
            var newHyperRanges = listOf(newHyperRange)

            for (previousHyperRange in on) {
                newHyperRanges = newHyperRanges.flatMap { newHyperRangeSegment ->
                    if (newHyperRangeSegment intersects previousHyperRange) {
                        (newHyperRangeSegment - previousHyperRange)
                    } else {
                        listOf(newHyperRangeSegment)
                    }
                }
            }

            on += newHyperRanges
        } else {
            on = on.asSequence()
                .flatMap { previousHyperRange ->
                    if (newHyperRange intersects previousHyperRange) {
                        (previousHyperRange - newHyperRange).asSequence()
                    } else {
                        sequenceOf(previousHyperRange)
                    }
                }
                .toMutableList()
        }
    }

    return on.sumOf { it.size }
}

private fun part1(turnings: List<Turning>) =
    getCoverage(
        turnings.filter { sequenceOf(it.hyperRange.x, it.hyperRange.y, it.hyperRange.z).all {  it in -50..50 } }
    )

private val part2 = ::getCoverage

fun main() {
    val turnings = mapLines {
        val groupValues = """(\w{2,3}) x=(-?\d+)..(-?\d+),y=(-?\d+)..(-?\d+),z=(-?\d+)..(-?\d+)"""
            .toRegex()
            .matchEntire(it)
            .expect()
            .groupValues

        val (x, y, z) = groupValues
            .asSequence()
            .drop(2)
            .map { it.toInt() }
            .windowed(2, 2)
            .map { (a, b) -> a..b }
            .toList()

        Turning(groupValues[1] == "on", HyperRange(x, y, z))
    }

    println(part1(turnings))
    println(part2(turnings))
}
