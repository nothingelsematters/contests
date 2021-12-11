private typealias Point = Pair<Int, Int>

private fun neighbours(point: Point): Sequence<Point> {
    val (i, j) = point
    return sequenceOf(i - 1 to j, i + 1 to j, i to j - 1, i to j + 1)
}

private fun findBasinSize(lists: List<List<Int>>, point: Point, basin: MutableSet<Point> = mutableSetOf()): Int {
    val current = lists.getOrNull(point.first)?.getOrNull(point.second)

    if (point in basin || current == null || current == 9) return 0

    basin.add(point)
    return 1 + neighbours(point).sumOf { findBasinSize(lists, it, basin) }
}

fun main() {
    val lists = mapLines { line -> line.map { it.toString().toInt() } }

    val optimums = mutableListOf<Point>()

    lists.innerIndices.forEach { index ->
        neighbours(index)
            .mapNotNull { (neighbourI, neighbourJ) -> lists.getOrNull(neighbourI)?.getOrNull(neighbourJ) }
            .all { it > lists[index] }
            .let { if (it) optimums += index }
    }

    val first = optimums.sumOf { (i, j) -> 1 + lists[i][j] }
    val second = optimums
        .asSequence()
        .map { findBasinSize(lists, it) }
        .sortedDescending()
        .take(3)
        .reduce(Int::times)

    println(first)
    println(second)
}
