// Structures

data class Point(val x: Int, val y: Int) {

    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    operator fun minus(other: Point) = Point(x - other.x, y - other.y)

    operator fun times(other: Int) = Point(x * other, y * other)
}

enum class Direction(val point: Point) {
    Up(Point(-1, 0)),
    Down(Point(1, 0)),
    Left(Point(0, -1)),
    Right(Point(0, 1));

    fun reversed() = when (this) {
        Up -> Down
        Down -> Up
        Left -> Right
        Right -> Left
    }

    fun clockwise() = when (this) {
        Up -> Right
        Right -> Down
        Down -> Left
        Left -> Up
    }
}
