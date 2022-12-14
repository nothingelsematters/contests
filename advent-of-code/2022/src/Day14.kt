private fun tryToMove(
    current: Point,
    horizontalAttempt: Int,
    horizontalLines: ArrayDeque<Pair<Int, IntRange>>,
    leftVerticalLines: ArrayDeque<Pair<Int, IntRange>>,
    rightVerticalLines: ArrayDeque<Pair<Int, IntRange>>,
): Boolean {
    val attempt = current + Point(horizontalAttempt, 1)

    fun List<Pair<Int, IntRange>>.dropTakeCountAny(dropFunction: (Int) -> Boolean) =
        asSequence()
            .withIndex()
            .dropWhile { dropFunction(it.value.first) }
            .takeWhile { it.value.first == attempt.x }
            .fold(0 to false) { (_, flag), (index, i) -> index to (flag || attempt.y in i.second) }

    val currentHorizontalLines = horizontalLines.takeWhile { it.first == attempt.y }
    if (currentHorizontalLines.any { attempt.x in it.second }) return false

    when (horizontalAttempt) {
        1 -> {
            val (skipped, result) = rightVerticalLines.dropTakeCountAny { it < attempt.x }
            if (result) return false
            repeat(skipped) { leftVerticalLines.addLast(rightVerticalLines.removeFirst()) }
        }
        -1 -> {
            val (skipped, result) = leftVerticalLines.asReversed().dropTakeCountAny { it > attempt.x }
            if (result) return false
            repeat(skipped) { rightVerticalLines.addFirst(leftVerticalLines.removeLast()) }
        }
    }

    repeat(currentHorizontalLines.size) { horizontalLines.removeFirst() }
    current.y += 1
    current.x += horizontalAttempt
    return true
}

private val START = Point(500, 0)

private fun sandAvalanche(lines: List<Pair<Point, Point>>): Int {
    val horizontalLines = lines.asSequence()
        .filter { (a, b) -> a.y == b.y }
        .map { (a, b) -> a.y to minOf(a.x, b.x)..maxOf(a.x, b.x)  }
        .toMutableList()

    val verticalLines = lines.asSequence()
        .filter { (a, b) -> a.x == b.x }
        .map { (a, b) -> a.x to minOf(a.y, b.y)..maxOf(a.y, b.y)  }
        .toMutableList()

    var count = 0

    while (true) {
        val sandBlock = START.copy()
        val linesDown = ArrayDeque(horizontalLines.sortedBy { it.first })
        val (linesLeft, linesRight) = verticalLines
            .asSequence()
            .sortedBy { it.first }
            .partition { it.first < START.x }
            .toList()
            .map { ArrayDeque(it) }

        do {
            if (linesDown.isEmpty()) return count
        } while (sequenceOf(0, -1, 1).any { tryToMove(sandBlock, it, linesDown, linesLeft, linesRight) })

        count += 1

        horizontalLines += sandBlock.y to sandBlock.x..sandBlock.x
        verticalLines += sandBlock.x to sandBlock.y..sandBlock.y

        if (sandBlock == START) return count
    }
}

private fun part1(lines: List<Pair<Point, Point>>): Int = sandAvalanche(lines)

private fun part2(lines: List<Pair<Point, Point>>): Int {
    val floorY = lines.asSequence().flatMap { sequenceOf(it.first, it.second) }.maxOf { it.y } + 2
    return sandAvalanche(lines + (Point(Int.MIN_VALUE, floorY) to Point(Int.MAX_VALUE, floorY)))
}

fun main() {
    val lines = mapLines { it.toInts(",", " -> ").chunked(2).map { (x, y) -> Point(x, y) } }
        .asSequence()
        .flatMap {
            it.asSequence().windowed(2) + it.asSequence().map { point -> List(2) { point } }
        }
        .map { it.toPair() }
        .toList()

    println(part1(lines))
    println(part2(lines))
}
