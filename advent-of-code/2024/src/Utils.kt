// Structures

data class Point(val x: Int, val y: Int) {

    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    operator fun minus(other: Point) = Point(x - other.x, y - other.y)

    operator fun times(other: Int) = Point(x * other, y * other)
}

infix fun <A, B> Iterable<A>.cartesian(other: Iterable<B>): Sequence<Pair<A, B>> =
    asSequence().flatMap { i -> other.asSequence().map { i to it } }

enum class Direction(val point: Point) {
    Up(Point(-1, 0)),
    Down(Point(1, 0)),
    Left(Point(0, -1)),
    Right(Point(0, 1));

    fun clockwise() = when (this) {
        Up -> Right
        Right -> Down
        Down -> Left
        Left -> Up
    }

    fun isVertical(): Boolean = this == Up || this == Down

    companion object {
        fun parse(input: Char): Direction? = when (input) {
            '^' -> Up
            'v' -> Down
            '<' -> Left
            '>' -> Right
            else -> null
        }
    }
}
