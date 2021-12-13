private fun fold(dots: List<Pair<Int, Int>>, folding: Pair<Boolean, Int>) =
    dots.asSequence()
        .map { (x, y) ->
            val coordinate = folding.second

            if (folding.first) {
                (if (x > coordinate) 2 * coordinate - x else x) to y
            } else {
                x to if (y > coordinate) 2 * coordinate - y else y
            }
        }
        .distinct()
        .toList()

private fun part1(dots: List<Pair<Int, Int>>, foldings: List<Pair<Boolean, Int>>) = fold(dots, foldings[0]).size

private fun part2(dots: List<Pair<Int, Int>>, foldings: List<Pair<Boolean, Int>>): String {
    val result = foldings.fold(dots, ::fold)

    val sb = StringBuilder()

    for (j in 0..result.maxOf { it.second }) {
        for (i in 0..result.maxOf { it.first }) {
            sb.append(if (i to j in result) '#' else '.')
        }
        sb.append('\n')
    }

    return sb.toString()
}

fun main() {
    val (dots, foldings) = mapBlocks { it }.let { (dots, folding) ->
        dots.map { line -> line.toInts().toPair() } to
            folding.map { (it["fold along ".length] == 'x') to it.substringAfter('=').toInt() }
    }

    println(part1(dots, foldings))
    println(part2(dots, foldings))
}
