typealias Grid = List<List<Boolean>>

fun Grid.rotateSequence() =
    sequenceOf(
            this,
            rotateClockwise(),
            rotateClockwise().rotateClockwise(),
            rotateCounterClockwise(),
            reversed(),
            reversed().rotateClockwise(),
            reversed().rotateClockwise().rotateClockwise(),
            reversed().rotateCounterClockwise()
        )

fun Grid.rotateCounterClockwise() = indices.map { i -> indices.map { j -> this[j][lastIndex - i] } }

fun Grid.rotateClockwise() = indices.map { i -> indices.map { j -> this[lastIndex - j][i] } }

class Tile(val id: Long, val grid: Grid) {

    val sides = listOf(grid.first(), grid.last(), grid.map { it.first() }, grid.map { it.last() }).map { it.toLong() }

    val variants by lazy { sides + sides.map { it.flip() } }

    private val sideSize = grid.size

    private fun List<Boolean>.toLong() = mapIndexed { ix, i -> if (i) 1L shl ix else 0 }.sum()

    private fun Long.flip() = (0 until sideSize).map { shr(it).and(1L) == 1L }.reversed().toLong()
}

fun oppositeSide(index: Int) = if (index % 2 == 0) index + 1 else index - 1

fun flipped(index: Int) = (index + 4) % 8

fun counterClockwise(index: Int) = when (index) {
    0 -> 2
    1 -> 3
    2 -> 1
    3 -> 0
    4 -> 6
    5 -> 7
    6 -> 5
    7 -> 4
    else -> throw IllegalArgumentException()
}

fun String.parseRow() = map { it == '#' }

val MONSTER = """
    ..................#.
    #....##....##....###
    .#..#..#..#..#..#...
    """
    .trimIndent()
    .lines()
    .map { it.parseRow() }

fun buildRow(
    tiles: List<Tile>,
    index: Int,
    sideIndex: Int,
    visited: MutableSet<Int> = mutableSetOf()
): List<Pair<Int, Int>> {
    if (visited.contains(index)) return emptyList()
    visited.add(index)

    val side = tiles[index].variants[sideIndex]
    val maxPath = (tiles.indices - visited).asSequence()
        .map { it to tiles[it].variants.indexOf(side) }
        .filter { it.second != -1 }
        .map { (i, si) -> buildRow(tiles, i, oppositeSide(si), visited) }
        .maxByOrNull { it.size } ?: emptyList()

    visited.remove(index)
    return listOf(index to sideIndex) + maxPath
}

fun normalizeRelative(fixed: List<Boolean>, flow: Grid, side: (Grid) -> List<Boolean>): Grid =
    flow.rotateSequence().filter { side(it) == fixed }.first()

fun normalize(grid: MutableList<MutableList<Grid>>) {
    for (i in grid.indices) {
        j@ for (j in grid.indices) {
            when {
                i == 0 && j == 0 -> continue@j
                j == 0 -> grid[i][j] = normalizeRelative(grid[i - 1][0].last(), grid[i][j]) { it.first() }
                else ->
                    grid[i][j] =
                        normalizeRelative(grid[i][j - 1].map { it.last() }, grid[i][j]) { it.map { it.first() } }
            }
        }
    }
}

fun main() {
    val tiles = readBlocks().dropLast(1).map {
        val splitted = it.split('\n')
        Tile(splitted.first().drop(5).dropLast(1).toLong(), splitted.drop(1).map { it.parseRow() })
    }

    val cornerTiles = tiles
        .filter { tile -> tile.sides.count { side -> (tiles - tile).any { it.variants.contains(side) } } == 2 }

    val first = cornerTiles.asSequence().map { it.id }.reduce(Long::times)
    val startTile = cornerTiles.first()
    val firstSideIndex = cornerTiles.first().sides.withIndex()
        .filter { (_, side) -> (tiles - startTile).any { it.variants.contains(side) } }
        .map { it.index }
        .first()

    val firstSide = buildRow(tiles, tiles.indexOf(startTile), firstSideIndex)

    var grid = firstSide.asSequence()
        .map { (index, sideIndex) ->
            val newSideIndex = counterClockwise(sideIndex) // TODO hardcode
            val opposite = oppositeSide(newSideIndex)
            sequenceOf(newSideIndex, flipped(newSideIndex), opposite, flipped(opposite))
                .map { buildRow(tiles, index, it) }
                .maxByOrNull { it.size }!!
        }
        .map { it.map { tiles[it.first].grid }.toMutableList() }
        .toList()
        .toMutableList()

    grid[0][0] = grid[0][0].rotateClockwise().rotateClockwise() // TODO hardcode
    normalize(grid)

    val tileSize = startTile.grid.size
    val squashedGrid = MutableList(grid.size * (tileSize - 2)) { mutableListOf<Boolean>() }

    for (i in grid.indices) {
        for (j in grid[i].indices) {
            for (k in grid[i][j].indices.drop(1).dropLast(1)) {
                squashedGrid[i * (tileSize - 2) + k - 1].addAll(grid[i][j][k].drop(1).dropLast(1))
            }
        }
    }

    val monsterIndices = MONSTER.indices.flatMap { i -> MONSTER[i].indices.filter { MONSTER[i][it] }.map { i to it } }

    val second = squashedGrid.rotateSequence()
        .map { squashedRotated ->
            val copy = MutableList(squashedGrid.size) { MutableList(squashedGrid[it].size) { false } }

            (0..squashedRotated.lastIndex - MONSTER.size).forEach { i ->
                (0..squashedRotated[i].lastIndex - MONSTER.first().size).forEach j@{ j ->
                    if (monsterIndices.all { (k, h) -> squashedRotated[i + k][j + h] }) {
                        monsterIndices.forEach { (k, h) -> copy[i + k][j + h] = true }
                    }
                }
            }

            squashedRotated.indices.asSequence()
                .map { i -> squashedRotated[i].indices.count { j -> squashedRotated[i][j] && !copy[i][j] } }
                .sum()
        }
        .minOrNull()

    println("$first $second")
}
