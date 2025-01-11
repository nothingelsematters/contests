private fun simulate(robots: List<Pair<Point, Point>>, sizeConstrains: Point, seconds: Int): List<Point> =
    robots.map { (position, velocity) ->
        val (x, y) = (position + velocity * seconds)
        Point(x within sizeConstrains.x, y within sizeConstrains.y)
    }

private fun part1(robots: List<Pair<Point, Point>>, sizeConstraint: Point): Int {
    val positions = robots.map { (position, velocity) ->
        val (x, y) = (position + velocity * 100)
        Point(x within sizeConstraint.x, y within sizeConstraint.y)
    }

    return listOf(0..<sizeConstraint.x / 2, sizeConstraint.x / 2 + 1..<sizeConstraint.x)
        .cartesian(listOf(0..<sizeConstraint.y / 2, sizeConstraint.y / 2 + 1..<sizeConstraint.y))
        .map { (xRange, yRange) -> positions.count { it.x in xRange && it.y in yRange } }
        .product()
}

private fun part2(robots: List<Pair<Point, Point>>, sizeConstrains: Point): Int {
    var i = 0

    while (true) {
        i++
        val positions = simulate(robots, sizeConstrains, i).toSet()

        val condition = (0..sizeConstrains.y).any { y ->
            val string = buildString {
                (0..sizeConstrains.x).forEach { x ->
                    append(if (Point(x, y) in positions) '#' else ' ')
                }
            }
            string.contains("######")
        }

        if (condition) break
    }

    return i
}

fun main() {
    val robots = mapLines { line ->
        """p=(\d+),(\d+) v=([-\d]+),([-\d]+)""".toRegex()
            .matchEntire(line)!!
            .groupValues
            .drop(1)
            .map { it.toInt() }
            .let { (p1, p2, v1, v2) ->
                Point(p1, p2) to Point(v1, v2)
            }
    }

    val sizeConstraint = Point(101, 103)
    val first = part1(robots, sizeConstraint)
    val second = part2(robots, sizeConstraint)

    println("$first, $second")
}
