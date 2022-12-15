import kotlin.math.abs
import kotlin.math.absoluteValue

private infix fun Point.manhattan(other: Point) = (this - other).let { it.x.absoluteValue + it.y.absoluteValue }

private fun part1(sensors: List<Pair<Point, Point>>): Int {
    val neededY = 2_000_000
    val noBeacon = mutableSetOf<Int>()

    sensors.asSequence()
        .map { (sensor, beacon) -> sensor to (sensor manhattan beacon) }
        .filter { (sensor, manhattan) -> neededY in (sensor.y - manhattan)..(sensor.y + manhattan) }
        .forEach { (sensor, manhattan) ->
            val range = manhattan - abs(sensor.y - neededY)
            noBeacon.addAll((sensor.x - range)..(sensor.x + range))
        }
    sensors.asSequence().map { it.second }.filter { it.y == neededY }.forEach { noBeacon.remove(it.x) }

    return noBeacon.size
}

private fun part2(sensors: List<Pair<Point, Point>>): Long? {
    val coordinateRange = 0..4_000_000

    for (x in coordinateRange) {
        var y = coordinateRange.first

        while (y <= coordinateRange.last) {
            val (sensor, beacon) = sensors
                .find { (sensor, beacon) -> sensor manhattan Point(x, y) <= sensor manhattan beacon }
                ?: return coordinateRange.last.toLong() * x.toLong() + y.toLong()

            y = (sensor manhattan beacon) - abs(x - sensor.x) + sensor.y + 1
        }
    }

    return null
}

fun main() {
    val sensors = mapLines { line ->
        """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""".toRegex()
            .matchEntire(line)!!
            .groupValues
            .asSequence()
            .drop(1)
            .map { it.toInt() }
            .chunked(2)
            .map { (a, b) -> Point(a, b) }
            .toList()
            .toPair()
    }

    println(part1(sensors))
    println(part2(sensors))
}
