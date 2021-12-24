

private infix fun Int.rangeTo(to: Int) = if (this >= to) to..this else this..to

private operator fun <T> List<T>.get(intRange: IntRange) = subList(intRange.first, intRange.last + 1)

private fun getRoomExit(roomNumber: Int) = (roomNumber + 1) * 2

private val STANDING_PLACES = (0..4).map { getRoomExit(it) - 1 } + 0 + 10

private data class Position(
    val rooms: List<List<Int>>,
    val hall: List<Int?> = List(11) { null },
    val points: Int,
) {
    init {
        check(hall.size == 11)
    }
}

private fun findMoves(position: Position, roomDepth: Int): List<Position> = buildList {
    val (rooms, hall, points) = position

    // room enter
    hall.asSequence()
        .withIndex()
        .filter { (_, value) -> value != null && rooms[value].all { it == value } }
        .forEach { (index, value) ->
            val roomExit = getRoomExit(value!!)
            val hallPath = if (roomExit > index) (index + 1) rangeTo roomExit else roomExit rangeTo (index - 1)

            if (hall[hallPath].all { it == null }) {
                add(Position(
                    rooms.map { it.toMutableList() }.also { it[value].add(value) },
                    hall.toMutableList().also { it[index] = null },
                    points + 10.pow(value) * (hallPath.count() + roomDepth - rooms[value].size),
                ))
            }
        }

    if (isNotEmpty()) return@buildList

    // room exit
    rooms.asSequence()
        .withIndex()
        .filter { (i, room) -> room.isNotEmpty() || room.any { it != i } }
        .forEach { (index, room) ->
            for (place in STANDING_PLACES) {
                val hallPath = getRoomExit(index) rangeTo place

                if (hall[hallPath].all { it == null }) {
                    val value = room.first()

                    add(Position(
                        rooms.map { it.toMutableList() }.also { it[index].removeFirst() },
                        hall.toMutableList().also { it[place] = value },
                        points + 10.pow(value) * (hallPath.count() + roomDepth - room.size),
                    ))
                }
            }
        }
}

/**
 * Brute force with heuristics:
 *   * amphipod stays only between the room or in the hall end ([STANDING_PLACES])
 *   * score < [scoreLimit]
 *   * amphipod enters the room, once has an opportunity
*/
private fun findShuffling(
    position: Position,
    visited: MutableSet<Pair<List<List<Int>>, List<Int?>>> = mutableSetOf(),
    scoreLimit: Int,
    roomDepth: Int = position.rooms.first().size,
): Int {
    when {
        position.hall.all { it == null } && position.rooms.withIndex().all { (index, it) -> it.all { it == index } } ->
            return position.points
        position.points > scoreLimit -> return Int.MAX_VALUE
    }

    visited += position.rooms to position.hall
    val result = findMoves(position, roomDepth)
        .asSequence()
        .filter { (it.rooms to it.hall) !in visited }
        .minOfOrNull { findShuffling(it, visited, scoreLimit, roomDepth) }
        ?: Int.MAX_VALUE
    visited -= position.rooms to position.hall
    return result
}

private fun part1(rooms: List<List<Int>>) =
    findShuffling(Position(rooms, points = 0), scoreLimit = 19_000)

private fun part2(rooms: List<List<Int>>): Int {
    val newRooms = listOf(
        listOf(rooms[0][0], 3, 3, rooms[0][1]),
        listOf(rooms[1][0], 2, 1, rooms[1][1]),
        listOf(rooms[2][0], 1, 0, rooms[2][1]),
        listOf(rooms[3][0], 0, 2, rooms[3][1]),
    )
    return findShuffling(Position(newRooms, points = 0), scoreLimit = 60_000)
}

fun main() {
    val rooms = lines()
        .asSequence()
        .drop(2)
        .take(2)
        .map { string -> listOf(3, 5, 7, 9).map { string[it].code - 'A'.code } }
        .toList()
        .let { lists -> (0..3).map { i -> lists.map { it[i] } } }

    // both run for approximately 1 minute
    println(part1(rooms))
    println(part2(rooms))
}
