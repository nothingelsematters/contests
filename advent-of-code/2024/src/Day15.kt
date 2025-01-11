private enum class Cell {
    Empty,
    Wall,
    Box,
}

private enum class ExtendedCell {
    Empty,
    Wall,
    BoxLeft,
    BoxRight;

    fun isBox(): Boolean = this == BoxLeft || this == BoxRight
}

private fun parse(): Triple<List<List<Cell>>, Index2, List<Direction>> {
    val (firstInputBlock, secondInputBlock) = mapBlocks { it }

    var robotPosition = Index2(0, 0)

    val field = firstInputBlock.mapIndexed { i, line ->
        line.mapIndexed { j, char ->
            when (char) {
                '#' -> Cell.Wall
                'O' -> Cell.Box
                '.' -> Cell.Empty
                '@' -> {
                    robotPosition = Index2(i, j)
                    Cell.Empty
                }
                else -> expect()
            }
        }
    }

    val moves = secondInputBlock.flatMap { line -> line.map { Direction.parse(it)!! } }

    return Triple(field, robotPosition, moves)
}

private fun move(field: List<MutableList<Cell>>, robotPosition: Index2, move: Direction): Index2 {
    val newPosition = robotPosition + move.point

    return when (field[newPosition]) {
        Cell.Empty -> newPosition
        Cell.Wall -> robotPosition
        Cell.Box -> {
            var end = newPosition
            while (field[end] == Cell.Box) end += move.point

            if (field[end] != Cell.Empty) {
                robotPosition
            } else {
                field[end] = Cell.Box
                field[newPosition] = Cell.Empty
                newPosition
            }
        }
    }
}

private fun extendedMove(field: List<MutableList<ExtendedCell>>, robotPosition: Index2, move: Direction): Index2 {
    fun push(position: Index2) {
        when (field[position]) {
            ExtendedCell.Empty -> return
            ExtendedCell.Wall -> expect()
            ExtendedCell.BoxLeft -> {
                val left = position + move.point
                val right = position + move.point + Direction.Right.point

                push(left)
                push(right)

                field[left] = ExtendedCell.BoxLeft
                field[right] = ExtendedCell.BoxRight
                field[position] = ExtendedCell.Empty
                field[position + Direction.Right.point] = ExtendedCell.Empty
            }
            ExtendedCell.BoxRight -> {
                val left = position + move.point + Direction.Left.point
                val right = position + move.point

                push(left)
                push(right)

                field[left] = ExtendedCell.BoxLeft
                field[right] = ExtendedCell.BoxRight
                field[position + Direction.Left.point] = ExtendedCell.Empty
                field[position] = ExtendedCell.Empty
            }
        }
    }

    val newPosition = robotPosition + move.point

    return when (field[newPosition]) {
        ExtendedCell.Empty -> newPosition
        ExtendedCell.Wall -> robotPosition
        else -> {
            if (!move.isVertical()) {
                var current = newPosition
                while (field[current].isBox()) current += move.point

                if (field[current] != ExtendedCell.Empty) {
                    robotPosition
                } else {
                    while (current != newPosition) {
                        field[current] = field[current - move.point]
                        current -= move.point
                    }
                    field[newPosition] = ExtendedCell.Empty
                    newPosition
                }
            } else {
                var q = listOf(
                    newPosition,
                    newPosition +
                        (if (field[newPosition] == ExtendedCell.BoxLeft) Direction.Right else Direction.Left).point,
                )

                while (q.any { field[it].isBox() } && q.none { field[it] == ExtendedCell.Wall }) {
                    q = q.asSequence()
                        .filter { field[it].isBox() }
                        .flatMap {
                            listOf(
                                it + move.point,
                                it + move.point +
                                    (if (field[it] == ExtendedCell.BoxLeft) Direction.Right else Direction.Left).point,
                            )
                        }
                        .distinct()
                        .toList()
                }

                if (q.any { field[it] == ExtendedCell.Wall }) {
                    robotPosition
                } else {
                    push(newPosition)
                    newPosition
                }
            }
        }
    }
}

private fun part1(field: List<List<Cell>>, robotPosition: Index2, moves: List<Direction>): Int {
    val fieldCopy = field.mutableDeepCopy()
    moves.fold(robotPosition) { acc, move -> move(fieldCopy, acc, move) }

    return fieldCopy.indexed2Sequence()
        .filter { it.value == Cell.Box }
        .sumOf { it.index2.i * 100 + it.index2.j }
}

private fun part2(field: List<List<Cell>>, robotPosition: Index2, moves: List<Direction>): Int {
    val extendedField = field.map { row ->
        row.asSequence()
            .flatMap { cell ->
                when (cell) {
                    Cell.Empty -> listOf(ExtendedCell.Empty, ExtendedCell.Empty)
                    Cell.Wall -> listOf(ExtendedCell.Wall, ExtendedCell.Wall)
                    Cell.Box -> listOf(ExtendedCell.BoxLeft, ExtendedCell.BoxRight)
                }
            }
            .toMutableList()
    }

    val initialRobotPosition = Index2(robotPosition.i, robotPosition.j * 2)
    moves.fold(initialRobotPosition) { acc, move ->
        extendedMove(extendedField, acc, move)
    }

    return extendedField.indexed2Sequence()
        .filter { it.value == ExtendedCell.BoxLeft }
        .sumOf { it.index2.i * 100 + it.index2.j }
}

fun main() {
    val (field, robotPosition, moves) = parse()

    val first = part1(field, robotPosition, moves)
    val second = part2(field, robotPosition, moves)

    println("$first $second")
}
