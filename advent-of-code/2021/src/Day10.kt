private val MATCHING = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>',
)

private fun part1(lines: List<String>) = lines.sumOf { line ->
    val stack = ArrayDeque<Char>()

    for (i in line) {
        if (i in MATCHING) {
            stack.addFirst(i)
            continue
        }

        val matching = stack.removeFirstOrNull()
        if (MATCHING[matching] == i) continue

        return@sumOf when (i) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> expect()
        }
    }

    0L
}

private fun part2(lines: List<String>) = lines
    .asSequence()
    .mapNotNull { line ->
        val stack = ArrayDeque<Char>()

        for (i in line) {
            if (i in MATCHING) {
                stack.addFirst(i)
                continue
            }

            val matching = stack.removeFirstOrNull()
            if (MATCHING[matching] != i) return@mapNotNull null
        }

        stack.fold(0L) { count, i -> count * 5 + 1 + "([{<".indexOf(i) }
    }
    .sorted()
    .toList()
    .let { it[it.size / 2] }

fun main() {
    val lines = mapLines { it }
    println(part1(lines))
    println(part2(lines))
}
