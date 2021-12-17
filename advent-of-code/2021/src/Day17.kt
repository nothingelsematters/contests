private data class Trench(val xLeft: Int, val xRight: Int, val yBottom: Int, val yTop: Int) {

    operator fun contains(point: Point) = point.first in xLeft..xRight && point.second in yBottom..yTop

    fun testTrajectory(trajectory: Point): Boolean {
        var (xVelocity, yVelocity) = trajectory
        var x = 0
        var y = 0

        while (x <= xRight && y >= yBottom) {
            if (x to y in this) {
                return true
            }

            x += xVelocity
            y += yVelocity

            xVelocity = when {
                xVelocity > 0 -> xVelocity - 1
                xVelocity < 0 -> xVelocity + 1
                else -> xVelocity
            }
            yVelocity -= 1
        }

        return false
    }

    fun findTrajectories(): Sequence<Point> =
        (yBottom..1000)
            .asSequence()
            .flatMap { j ->
                (0..xRight)
                    .asSequence()
                    .filter { testTrajectory(it to j) }
                    .map { it to j }
            }
}

private fun part1(trench: Trench) =
    trench.findTrajectories()
        .maxByOrNull { it.second }
        .expect()
        .let { (_, n) -> n * (n + 1) / 2 }

private fun part2(trench: Trench) = trench.findTrajectories().count()

fun main() {
    val trench = """target area: x=(-?\d+)..(-?\d+), y=(-?\d+)..(-?\d+)""".toRegex()
        .matchEntire(readln())
        .expect()
        .groupValues
        .subList(1, 5)
        .map { it.toInt() }
        .let { (xLeft, xRight, yBottom, yTop) -> Trench(xLeft, xRight, yBottom, yTop) }

    println(part1(trench))
    println(part2(trench))
}
