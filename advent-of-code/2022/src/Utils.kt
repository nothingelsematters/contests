data class Point(var x: Int, var y: Int) {

    override fun toString() = "($x, $y)"

    operator fun plus(rhs: Point) = Point(x + rhs.x, y + rhs.y)

    operator fun plusAssign(rhs: Point) {
        x += rhs.x
        y += rhs.y
    }

    operator fun minus(rhs: Point) = Point(x - rhs.x, y - rhs.y)

    operator fun times(rhs: Int) = Point(x * rhs, y * rhs)
}

enum class Direction(val point: Point) {
    Left(Point(-1, 0)),
    Right(Point(1, 0)),
    Up(Point(0, 1)),
    Down(Point(0, -1)),
}
