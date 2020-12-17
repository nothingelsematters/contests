import kotlin.math.pow

fun Int.pow(n: Int) = toDouble().pow(n).toInt()

fun conwoyCubes(iterations: Int, dimensions: Int, initialGrid: List<List<Boolean>>): Int {
    val gridSize = initialGrid.size + 2 * (iterations + 1)
    val margin = iterations + 1
    val defaultValue = false

    var grid = (0 until gridSize.pow(dimensions))
        .map { mask -> List(dimensions) { mask / gridSize.pow(it) % gridSize } to defaultValue }
        .toMap()

    grid = grid + initialGrid.flatMapIndexed { i, it ->
        it.mapIndexed { j, p -> (List(dimensions - 2) { gridSize / 2 } + listOf(margin + i, margin + j)) to p }
    }

    repeat(iterations) {
        grid = grid.mapValues { (key, value) ->
            if (key.any { it == 0 || it == gridSize - 1 }) return@mapValues value
            List(3.pow(dimensions)) { mask ->
                    key.toMutableList().mapIndexed { index, i -> i - 1 + mask / 3.pow(index) % 3 }.let { grid[it]!! }
                }
                .count { it }
                .let { step(value, it) }
        }
    }

    return grid.values.count { it }
}

fun step(state: Boolean, actives: Int) = !state && actives == 3 || state && actives in 3..4

fun main() {
    val initialGrid = mapLines { it.map { it == '#' } }
    val first = conwoyCubes(6, 3, initialGrid)
    val second = conwoyCubes(6, 4, initialGrid)
    println("$first $second")
}
