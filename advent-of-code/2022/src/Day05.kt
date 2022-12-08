private data class Action(val count: Int, val from: Int, val to: Int)

private fun readColumnsAndMoves(): Pair<MutableList<ArrayDeque<Char>>, List<Action>> {
    val columns: MutableList<ArrayDeque<Char>> = mutableListOf()

    while (true) {
        val line = readln()
        if ("[" !in line) break

        line.asSequence()
            .filterIndexed { index, _ -> index % 4 == 1 }
            .withIndex()
            .onEach { (index, _) -> if (columns.getOrNull(index) == null) columns.add(ArrayDeque()) }
            .filter { (_, i) -> !i.isWhitespace() }
            .forEach { (index, i) -> columns[index].addFirst(i) }
    }

    readln()

    val moves = mapLines {  action ->
        val (move, from, to) = """move (\d+) from (\d+) to (\d+)""".toRegex()
            .matchEntire(action)
            .unwrap()
            .destructured
            .toList()
            .map { it.toInt() }
        Action(move, from - 1, to - 1)
    }

    return columns to moves
}

private fun assembleOutput(columns: Iterable<List<Char>>) = columns.joinToString("") { it.last().toString() }

private fun <T> ArrayDeque<T>.removeLast(count: Int) = (0 until count).map { removeLast() }

private fun part1(columns: MutableList<ArrayDeque<Char>>, moves: List<Action>): String {
    moves.forEach { (count, from, to) -> columns[to].addAll(columns[from].removeLast(count)) }
    return assembleOutput(columns)
}

private fun part2(columns: MutableList<ArrayDeque<Char>>, moves: List<Action>): String {
    moves.forEach { (count, from, to) -> columns[to].addAll(columns[from].removeLast(count).asReversed()) }
    return assembleOutput(columns)
}

fun main() {
    val (columns, moves) = readColumnsAndMoves()
    val columnsDeepCopy = columns.asSequence().map { ArrayDeque(it) }.toMutableList()

    println(part1(columnsDeepCopy, moves))
    println(part2(columns, moves))
}
