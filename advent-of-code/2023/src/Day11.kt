import kotlin.math.abs

private infix fun Int.minMaxRangeTo(rhs: Int) = minOf(this, rhs)..maxOf(this, rhs)

private fun galaxyDistances(
    galaxies: List<Index2>,
    emptyRows: Set<Int>,
    emptyColumns: Set<Int>,
    expansionRate: Long,
): Long =
    galaxies.asSequence().withIndex().sumOf { (i, galaxyI) ->
        galaxies.asSequence().take(i).sumOf { galaxyJ ->
            abs(galaxyI.i - galaxyJ.i) + abs(galaxyI.j - galaxyJ.j) +
                (expansionRate - 1) * (
                (galaxyI.i minMaxRangeTo galaxyJ.i).count { it in emptyRows } +
                    (galaxyI.j minMaxRangeTo galaxyJ.j).count { it in emptyColumns }
                )
        }
    }

fun main() {
    val map = mapLines { line -> line.map { it == '#' } }
    val galaxies = map.innerIndexedSequence().filter { it.value }.map { it.index2 }.toList()

    val emptyRows = map.indices.toMutableSet()
    galaxies.forEach { emptyRows -= it.i }
    val emptyColumns = map.first().indices.toMutableSet()
    galaxies.forEach { emptyColumns -= it.j }

    val first = galaxyDistances(galaxies, emptyRows, emptyColumns, 2)
    val second = galaxyDistances(galaxies, emptyRows, emptyColumns, 1_000_000)

    println("$first $second")
}
