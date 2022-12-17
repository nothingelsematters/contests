private data class Rock(val points: MutableList<Point>) {
    constructor(vararg points: Point) : this(points.toMutableList())

    operator fun plus(point: Point) = Rock(points.asSequence().map { it + point }.toMutableList())
}

private val rocks = listOf(
    Rock(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)),
    Rock(Point(1, 0), Point(0, 1), Point(1, 1), Point(2, 1), Point(1, 2)),
    Rock(Point(0, 0), Point(1, 0), Point(2, 0), Point(2, 1), Point(2, 2)),
    Rock(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)),
    Rock(Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1)),
)

private fun simulateTetris(pushes: List<Int>, tiles: Long): Long {
    val grid = List(7) { mutableListOf<Boolean>() }
    var pushIndex = 0
    var rockIndex = 0

    val heights = mutableListOf(-1)
    val cycle = 10

    while (true) {
        var rock = rocks[rockIndex++ % rocks.size] + Point(2, heights.last() + 4)
        repeat(rock.points.maxOf { it.y } - grid[0].size + 1) { grid.forEach { it += false } }

        while (true) {
            val tryToPush = rock + Point(pushes[pushIndex++ % pushes.size], 0)
            if (tryToPush.points.all { it.x in grid.indices && !grid[it] }) rock = tryToPush

            val tryToFall = rock + Point(0, -1)
            if (tryToFall.points.any { it.y < 0 || grid[it] }) break
            rock = tryToFall
        }

        rock.points.forEach { grid[it] = true }
        heights += maxOf(heights.last(), rock.points.maxOf { it.y })

        if (heights.size > cycle * (cycle - 1) && heights.size % cycle == 0) {
            val cycled = (1 until cycle)
                .asSequence()
                .map { heights[(heights.size / cycle + 1) * it - 1] }
                .windowed(2)
                .map { (a, b) -> b - a }
                .distinct()
                .count() == 1

            if (cycled) break
        }
    }

    val period = heights.size / cycle + 1
    return tiles / period * (heights[period * 2] - heights[period]) + heights[(tiles % period).toInt()] + 1
}


fun main() {
    val pushes = readln().map { if(it == '<') -1 else 1 }
    println(simulateTetris(pushes, 2022))
    println(simulateTetris(pushes, 1_000_000_000_000))
}
