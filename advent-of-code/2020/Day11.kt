import java.lang.Integer.max
import java.lang.Integer.min

enum class Status {
    FLOOR, EMPTY, OCCUPIED
}

fun <T> mutate(grid: List<List<T>>, step: (List<List<T>>, T, Int, Int) -> T): Int {
    var gridCopy = grid

    while (true) {
        val newSeats = gridCopy.mapIndexed { i, row -> row.mapIndexed { j, seat -> step(gridCopy, seat, i, j) } }
        if (newSeats == gridCopy) break
        gridCopy = newSeats
    }

    return gridCopy.sumBy { it.count { it == Status.OCCUPIED } }
}

fun step(status: Status, adjacent: List<Status>) =
    when {
        status == Status.EMPTY && adjacent.all { it != Status.OCCUPIED } -> Status.OCCUPIED
        status == Status.OCCUPIED && adjacent.count { it == Status.OCCUPIED } >= 5 -> Status.EMPTY
        else -> status
    }

fun <T> neighbours(element: Collection<T>, i: Int, f: (Sequence<T>) -> Sequence<Status?>) =
    sequenceOf(
        element.asSequence().run(f).take(i).findLast { it != null && it != Status.FLOOR },
        element.asSequence().run(f).drop(i + 1).find { it != null && it != Status.FLOOR }
    )
    .mapNotNull { it }

fun main() {
    val seats = mapLines { it.map { if (it == 'L') Status.EMPTY else Status.FLOOR } }

    val first = mutate(seats) { grid, seat, i, j ->
        val adjacent = grid.slice(max(i - 1, 0)..min(i + 1, grid.lastIndex))
            .flatMap { it.slice(max(j - 1, 0)..min(j + 1, it.lastIndex)) }
        step(seat, adjacent)
    }

    val second = mutate(seats) { grid, seat, i, j ->
        if (seat == Status.FLOOR) return@mutate seat

        val adjacent = sequenceOf(
                neighbours(grid[i], j) { it },
                neighbours(grid, i) { it.map { it[j] } },
                neighbours(grid, i) { it.mapIndexed { index, s -> s.getOrNull(index - i + j) } },
                neighbours(grid, i) { it.mapIndexed { index, s -> s.getOrNull(-index + i + j) } }
            )
            .flatMap { it }
            .toList()
        step(seat, adjacent)
    }

    println("$first $second")
}
