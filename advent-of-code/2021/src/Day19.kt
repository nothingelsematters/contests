import kotlin.math.abs

private class Transformation(private val matrix: List<List<Int>>) {

    init {
        check(matrix.size == 3)
        check(matrix.all { it.size == 3 })
    }

    constructor(rotation: List<Int>, permutation: List<Int>) : this(
        List(3) { i ->
            List(3) { j ->
                if (permutation[j] == i) rotation[i] else 0
            }
        }
    )

    constructor() : this(List(3) { 1 }, List(3) { it })

    // vector * matrix
    infix fun apply(beaconCoordinates: BeaconCoordinates): BeaconCoordinates {
        val (x, y, z) = beaconCoordinates
        val list = listOf(x, y, z)

        val (newX, newY, newZ) = List(3) { j ->
            matrix
                .asSequence()
                .map { it[j] }
                .zip(list.asSequence())
                .map { (a, b) -> a * b }
                .sum()
        }
        return BeaconCoordinates(newX, newY, newZ)
    }

    // matrix * matrix
    infix fun compose(other: Transformation): Transformation {
        val matrix = List(3) { i ->
            List(3) { j ->
                (0 until 3).asSequence()
                    .map { k -> matrix[i][k] * other.matrix[k][j] }
                    .sum()
            }
        }
        return Transformation(matrix)
    }

    override fun toString() = matrix.joinToString("\n") { it.joinToString(" ", "(", ")") }

    companion object {
        private val ROTATIONS =
            sequenceOf(-1, 1).flatMap { i ->
                sequenceOf(-1, 1).flatMap { j ->
                    sequenceOf(-1, 1).map { k -> listOf(i, j, k) }
                }
            }
            .toList()

        private val PERMUTATIONS = listOf(
            listOf(0, 1, 2),
            listOf(0, 2, 1),
            listOf(1, 0, 2),
            listOf(1, 2, 0),
            listOf(2, 0, 1),
            listOf(2, 1, 0),
        )

        val ALL = (ROTATIONS cartesian PERMUTATIONS)
            .map { (rotation, permutation) -> Transformation(rotation, permutation) }
    }
}

private data class BeaconCoordinates(val x: Int, val y: Int, val z: Int) {

    operator fun minus(other: BeaconCoordinates) = BeaconCoordinates(x - other.x, y - other.y, z - other.z)

    operator fun plus(other: BeaconCoordinates) = BeaconCoordinates(x + other.x, y + other.y, z + other.z)

    override fun toString() = "($x, $y, $z)"

    companion object {
        fun parse(string: String) = string.toInts().let { (x, y, z) -> BeaconCoordinates(x, y, z) }
    }
}

private data class Scanner(val beaconCoordinates: List<BeaconCoordinates>) {

    fun findOverlapping(other: Scanner): Pair<BeaconCoordinates, Transformation>? {
        for ((thisPoint, otherPoint) in beaconCoordinates cartesian other.beaconCoordinates) {
            for (transformation in Transformation.ALL) {

                val base = thisPoint - (transformation apply otherPoint)
                val rest = other.beaconCoordinates
                    .asSequence()
                    .map { transformation apply it }
                    .map { it + base }
                    .toSet()
                    .union(beaconCoordinates)
                    .size

                if (beaconCoordinates.size + other.beaconCoordinates.size - rest >= 12) {
                    return base to transformation
                }
            }
        }

        return null
    }
}

private fun findOverlappings(scanners: List<Scanner>): List<Pair<BeaconCoordinates, Transformation>> {
    val transformations: MutableList<Pair<BeaconCoordinates, Transformation>?> = MutableList(scanners.size) { null }
    transformations[0] = BeaconCoordinates(0, 0, 0) to Transformation()
    val used = MutableList(scanners.size) { false }

    var lastScanner = 0
    while (transformations.any { it == null }) {
        scanners
            .asSequence()
            .withIndex()
            .filter { (index, _) -> transformations[index] == null }
            .map { (index, value) -> index to scanners[lastScanner].findOverlapping(value) }
            .filter { (_, value) -> value != null }
            .forEach { (index, overlapping) ->
                val (lastCoordinates, lastTransformation) = transformations[lastScanner]!!
                val (newCoordinates, newTransformation) = overlapping!!
                transformations[index] =
                    lastCoordinates + (lastTransformation apply newCoordinates) to
                        (newTransformation compose lastTransformation)
            }

        used[lastScanner] = true
        lastScanner =
            scanners.indices.asSequence().filter { transformations[it] != null && !used[it] }.firstOrNull() ?: 0
    }

    return transformations.map { it!! }
}

private fun findUniquePoints(scanners: List<Scanner>, overlappings: List<Pair<BeaconCoordinates, Transformation>>) =
    scanners
        .asSequence()
        .zip(overlappings.asSequence())
        .flatMap { (scanner, overlapping) ->
            val (base, transformation) = overlapping
            scanner.beaconCoordinates.asSequence().map { base + (transformation apply it) }
        }
        .distinct()
        .count()

private fun findFurthestScanners(overlappings: List<Pair<BeaconCoordinates, Transformation>>): Int {
    val bases = overlappings.map { it.first }
    return (bases cartesian bases)
        .map { (a, b) ->
            val (x, y, z) = a - b
            sequenceOf(x, y, z).map { abs(it) }.sum()
        }
        .maxOrNull()
        .expect()
}

fun main() {
    val scanners = withBlocks { block ->
        val beaconCoordinates = block.asSequence().drop(1).map { BeaconCoordinates.parse(it) }.toList()
        Scanner(beaconCoordinates)
    }

    val overlappings = findOverlappings(scanners)
    println(findUniquePoints(scanners, overlappings))
    println(findFurthestScanners(overlappings))
}
