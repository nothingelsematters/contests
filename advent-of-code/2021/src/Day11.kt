private fun flash(octopuses: List<List<Int>>, process: (octopuses: List<List<Int>>, iteration: Int) -> Int?): Int {
    val currentOctopuses = octopuses.map { it.toMutableList() }.toMutableList()

    repeat(Int.MAX_VALUE) { iteration ->
        currentOctopuses.innerIndices.forEach { currentOctopuses[it] += 1 }

        val flashed = mutableSetOf<Pair<Int, Int>>()

        cycle@ while (true) {
            for (index in currentOctopuses.innerIndices) {
                if (currentOctopuses[index] > 9) {
                    flashed += index
                    currentOctopuses[index] = 0

                    val (i, j) = index
                    (i - 1..i + 1)
                        .flatMap { currentI -> (j - 1..j + 1).map { currentI to it } }
                        .filter { (i, j) -> i in currentOctopuses.indices && j in currentOctopuses[i].indices }
                        .filter { it !in flashed }
                        .forEach { currentOctopuses[it] += 1 }
                    continue@cycle
                }
            }
            break
        }

        return process(currentOctopuses, iteration) ?: return@repeat
    }

    error("Invalid branch")
}

private fun part1(octopuses: List<List<Int>>): Int {
    var result = 0
    return flash(octopuses) { currentOctopuses, i ->
        result += currentOctopuses.sumOf { line -> line.count { it == 0 } }
        if (i == 99) result else null
    }
}

private fun part2(octopuses: List<List<Int>>) =
    flash(octopuses) { currentOctopuses, i ->
        if (currentOctopuses.all { line -> line.all { it == 0 } }) i + 1 else null
    }

fun main() {
    val octopuses = mapLines { line -> line.map { it.toString().toInt() } }
    println(part1(octopuses))
    println(part2(octopuses))
}
