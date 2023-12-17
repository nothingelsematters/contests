private enum class Cell {
    RoundedRock,
    CubeRock,
    Empty,
}

private fun rollNorthIndex(fallenMap: List<List<Cell>>, initialIndex: Index2): Index2 {
    var row = initialIndex.i
    while (row != 0 && fallenMap[row - 1][initialIndex.j] == Cell.Empty) row--
    return initialIndex.copy(i = row)
}

private fun rollSouthIndex(fallenMap: List<List<Cell>>, initialIndex: Index2): Index2 {
    var row = initialIndex.i
    while (row != fallenMap.lastIndex && fallenMap[row + 1][initialIndex.j] == Cell.Empty) row++
    return initialIndex.copy(i = row)
}

private fun rollWestIndex(fallenMap: List<List<Cell>>, initialIndex: Index2): Index2 {
    var column = initialIndex.j
    while (column != 0 && fallenMap[initialIndex.i][column - 1] == Cell.Empty) column--
    return initialIndex.copy(j = column)
}

private fun rollEastIndex(fallenMap: List<List<Cell>>, initialIndex: Index2): Index2 {
    var column = initialIndex.j
    while (column != fallenMap.first().lastIndex && fallenMap[initialIndex.i][column + 1] == Cell.Empty) column++
    return initialIndex.copy(j = column)
}

private fun Sequence<Indexed2Value<Cell>>.roll(
    fallenMap: List<MutableList<Cell>>,
    getIndex: (List<List<Cell>>, Index2) -> Index2,
) {
    filter { it.value == Cell.RoundedRock }.map { it.index2 }.forEach { index ->
        fallenMap[index] = Cell.Empty
        fallenMap[getIndex(fallenMap, index)] = Cell.RoundedRock
    }
}

private fun rollRocks(
    fallenMap: List<MutableList<Cell>>,
    getIndex: (List<List<Cell>>, Index2) -> Index2,
) {
    fallenMap.indexed2Sequence().roll(fallenMap, getIndex)
}

private fun rollRocksBackwards(
    fallenMap: List<MutableList<Cell>>,
    getIndex: (List<List<Cell>>, Index2) -> Index2,
) {
    fallenMap.asReversed().asSequence()
        .flatMapIndexed { i, row ->
            row.asReversed().mapIndexed { j, value ->
                Indexed2Value(Index2(fallenMap.lastIndex - i, fallenMap.first().lastIndex - j), value)
            }
        }
        .roll(fallenMap, getIndex)
}

private fun calculateLoad(map: List<List<Cell>>): Int =
    map.asReversed().sumOfIndexed { i, row ->
        (i + 1) * row.asSequence().filter { it == Cell.RoundedRock }.count()
    }

private fun part1(map: List<List<Cell>>): Int {
    val fallenMap = map.mutableDeepCopy()
    rollRocks(fallenMap, ::rollNorthIndex)
    return calculateLoad(fallenMap)
}

private fun part2(map: List<List<Cell>>): Int {
    val fallenMap = map.mutableDeepCopy()
    val visited = mutableMapOf<List<List<Cell>>, Int>()

    var i = 0
    while (true) {
        if (fallenMap in visited) break
        visited[fallenMap.deepCopy()] = i++

        rollRocks(fallenMap, ::rollNorthIndex)
        rollRocks(fallenMap, ::rollWestIndex)
        rollRocksBackwards(fallenMap, ::rollSouthIndex)
        rollRocksBackwards(fallenMap, ::rollEastIndex)
    }

    val cycleStart = visited[fallenMap]!!
    val index = cycleStart + 1_000_000_000 % (i - cycleStart - 1) - 1
    val targetMap = visited.asSequence().find { it.value == index }!!.key

    return calculateLoad(targetMap)
}

fun main() {
    val map = mapLines { line ->
        line.map {
            when (it) {
                'O' -> Cell.RoundedRock
                '#' -> Cell.CubeRock
                '.' -> Cell.Empty
                else -> expect()
            }
        }
    }

    val first = part1(map)
    val second = part2(map)

    println("$first $second")
}
