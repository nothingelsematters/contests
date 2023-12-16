private data class Pipe(val directions: Pair<Direction, Direction>) {

    companion object {
        fun parse(representation: Char): Pipe? {
            val directions = when (representation) {
                '|' -> Direction.Up to Direction.Down
                'J' -> Direction.Up to Direction.Left
                'L' -> Direction.Up to Direction.Right
                '7' -> Direction.Down to Direction.Left
                'F' -> Direction.Down to Direction.Right
                '-' -> Direction.Left to Direction.Right
                else -> return null
            }
            return Pipe(directions)
        }
    }
}

private fun readPipes(): Pair<List<List<Pipe?>>, Index2> {
    val lines = mapLines { it }
    var starting: Index2? = null

    val pipes = lines.asSequence()
        .mapIndexed { i, row ->
            row.asSequence()
                .mapIndexed { j, char ->
                    if (char == 'S') starting = Index2(i, j)
                    Pipe.parse(char)
                }
                .toMutableList()
        }
        .toMutableList()

    val trueStarting = starting.unwrap()

    pipes[trueStarting] = Direction.entries.asSequence()
        .filter {
            pipes.getOrNull(it.index2 + trueStarting)
                ?.directions
                ?.toList()
                ?.contains(it.reversed()) == true
        }
        .toList()
        .toPair()
        .let { Pipe(it) }

    return pipes to trueStarting
}

private fun getLoop(pipes: List<List<Pipe?>>, starting: Index2): List<Indexed2Value<Pipe>> {
    val loop = mutableListOf<Indexed2Value<Pipe>>()
    var previous: Index2? = null
    var current = starting

    while (previous == null || current != starting) {
        loop += Indexed2Value(current, pipes[current].unwrap())

        val next = pipes[current]
            ?.directions
            ?.let { (a, b) -> sequenceOf(a, b) }
            .orEmpty()
            .map { it.index2 + current }
            .filter { it != previous }
            .first()
        previous = current
        current = next
    }

    return loop
}

private fun part1(loop: List<Indexed2Value<Pipe>>): Int = loop.size / 2

private fun part2(loop: List<Indexed2Value<Pipe>>): Int =
    loop.groupBy { it.index2.i }
        .values
        .asSequence()
        .map { indexPipes -> indexPipes.sortedBy { it.index2.j } }
        .sumOf { indexPipes ->
            var previous: Index2? = null
            var sum = 0
            var i = 0

            while (i < indexPipes.size) {
                if (previous != null) {
                    sum += indexPipes[i].index2.j - previous.j - 1
                }

                val startPipe = indexPipes[i].value.directions

                if (startPipe == Direction.Up to Direction.Down) {
                    previous = if (previous == null) indexPipes[i].index2 else null
                    i++
                    continue
                }

                i++
                while (indexPipes[i].value.directions == Direction.Left to Direction.Right) {
                    i++
                }

                val endPipe = indexPipes[i].value.directions
                previous = if (
                    previous == null && startPipe.first != endPipe.first
                    || previous != null && startPipe.first == endPipe.first
                ) {
                    indexPipes[i].index2
                } else {
                    null
                }

                i++
            }

            sum
        }

fun main() {
    val (pipes, starting) = readPipes()
    val loop = getLoop(pipes, starting)

    val first = part1(loop)
    val second = part2(loop)

    println("$first $second")
}
