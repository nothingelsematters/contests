private fun numberOfTrails(
    topographicMap: List<List<Int>>,
    index: Index2,
    countUnique: Boolean,
    visited: MutableSet<Index2> = mutableSetOf(),
): Int {
    if (countUnique && index in visited) return 0

    visited += index
    if (topographicMap[index] == 9) return 1

    return Direction.entries
        .map { it.point + index }
        .filter {
            val next = topographicMap.getOrNull(it) ?: return@filter false
            next - topographicMap[index] == 1
        }
        .sumOf { numberOfTrails(topographicMap, it, countUnique, visited) }
}

fun main() {
    val topographicMap = mapLines { line -> line.map { it.digitToInt() } }

    val first = topographicMap.indexed2Sequence()
        .filter { it.value == 0 }
        .sumOf { numberOfTrails(topographicMap, it.index2, true) }

    val second = topographicMap.indexed2Sequence()
        .filter { it.value == 0 }
        .sumOf { numberOfTrails(topographicMap, it.index2, false) }

    println("$first $second")
}
