data class Point(var x: Int, var y: Int) {

    override fun toString() = "($x, $y)"

    operator fun plus(rhs: Point) = Point(x + rhs.x, y + rhs.y)

    operator fun minus(rhs: Point) = Point(x - rhs.x, y - rhs.y)
}
