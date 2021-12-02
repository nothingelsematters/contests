data class Coordinates(val horizontal: Int, val depth: Int, val aim: Int)

private fun part1(moves: List<Pair<Int, Int>>): Int {
    val result = moves.reduce { a, b -> a.first + b.first to a.second + b.second }
    return result.first * result.second
}

private fun part2(moves: List<Pair<Int, Int>>): Int {
    val result = moves.fold(Coordinates(0, 0, 0)) { (horizontal, depth, aim), (x, y) ->
        Coordinates(horizontal + x, depth + x * aim, aim + y)
    }
    return result.horizontal * result.depth
}

fun main() {
    val moves = mapLines {
        val (direction, distanceString) = it.split(" ")
        val distance = distanceString.toInt()
        when (direction) {
            "forward" -> distance to 0
            "up" -> 0 to -distance
            "down" -> 0 to distance
            else -> error("Illegal input")
        }
    }

    println(part1(moves))
    println(part2(moves))
}
