private enum class Cell {
    East,
    South,
    Empty,
}

private fun iterate(cells: List<List<Cell>>): List<List<Cell>> {
    val temporaryCells = cells.map {
        it.indices.map { i ->
            val previous = it[(i + it.size - 1) % it.size]
            val next = it[(i + 1) % it.size]

            when {
                it[i] == Cell.Empty && previous == Cell.East -> Cell.East
                it[i] == Cell.East && next == Cell.Empty -> Cell.Empty
                else -> it[i]
            }
        }
    }

    val newCells = temporaryCells.indices.map { i ->
        temporaryCells[i].indices.map { j ->
            val previous = temporaryCells[(i + temporaryCells.size - 1) % temporaryCells.size][j]
            val next = temporaryCells[(i + 1) % temporaryCells.size][j]
            val current = temporaryCells[i][j]

            when {
                current == Cell.Empty && previous == Cell.South -> Cell.South
                current == Cell.South && next == Cell.Empty -> Cell.Empty
                else -> current
            }
        }
    }

    return newCells
}

private fun findStop(cells: List<List<Cell>>): Int {
    var currentCells = cells
    var i = 0

    while (true) {
        val newCells = iterate(currentCells)
        i++
        if (newCells == currentCells) return i
        currentCells = newCells
    }
}

fun main() {
    val cells = mapLines {
        it.map {
            when (it) {
                '.' -> Cell.Empty
                'v' -> Cell.South
                '>' -> Cell.East
                else -> expect()
            }
        }
    }

    println(findStop(cells))
}
