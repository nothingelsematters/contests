private fun onInstructionIteration(instructions: List<Int?>, action: (i: Int, x: Int) -> Unit) {
    instructions.fold(1 to 0) { pair, addV ->
        var (x, i) = pair
        action(i, x)

        if (addV != null) {
            i += 1
            action(i, x)
            x += addV
        }

        x to i + 1
    }
}

private fun part1(instructions: List<Int?>): Int {
    var sum = 0
    onInstructionIteration(instructions) { i, x ->
        if (i + 1 == 20 || (i + 1 - 20) % 40 == 0) {
            sum += (i + 1) * x
        }
    }
    return sum
}

private fun part2(instructions: List<Int?>): String {
    val pixels = mutableSetOf<Pair<Int, Int>>()
    val width = 40

    onInstructionIteration(instructions) { i, x ->
        if ((i % width) + 1 in x..x + 2) {
            pixels += i / width to i % width
        }
    }

    val levels = pixels.maxOf { it.first }
    return (0..levels).joinToString("\n") { i ->
        (0 until width).joinToString("") { j -> if (i to j in pixels) "#" else "." }
    }
}

fun main() {
    val instructions = mapLines { if (it != "noop") it.split(' ')[1].toInt() else null }
    println(part1(instructions))
    println(part2(instructions))
}
