import kotlin.math.abs

fun main() {
    val directions = mapLines { it[0] to it.drop(1).toInt() }

    var first = 0
    var angle = 0

    for ((direction, distance) in directions) {
        when {
            direction in "NW" || direction == 'F' && angle in 180..270 -> first += distance
            direction in "SE" || direction == 'F' && angle in 0..90 -> first -= distance
            direction == 'L' -> angle = (angle - distance + 360) % 360
            direction == 'R' -> angle = (angle + distance) % 360
        }
    }

    first = abs(first)

    var shipX = 0
    var shipY = 0
    var waypointX = -10
    var waypointY = 1

    for ((command, distance) in directions) {
        when {
            command == 'N' -> waypointY += distance
            command == 'S' -> waypointY -= distance
            command == 'E' -> waypointX -= distance
            command == 'W' -> waypointX += distance

            (command to distance).let { it == 'L' to 90 || it == 'R' to 270 } ->
                waypointX = waypointY.also { waypointY = -waypointX }

            (command to distance).let { it == 'L' to 270 || it == 'R' to 90 } ->
                waypointX = -waypointY.also { waypointY = waypointX }

            command == 'F' -> {
                shipX += distance * waypointX
                shipY += distance * waypointY
            }
            else -> {
                waypointX *= -1
                waypointY *= -1
            }
        }
    }

    val second = abs(shipX) + abs(shipY)

    println("$first $second")
}
