fun launch(instructions: List<Pair<String, Int>>): Pair<Boolean, Int> {
    var acc = 0
    var index = 0
    val visited = mutableSetOf<Int>()

    while (true) {
        when (index) {
            instructions.size -> return true to acc
            in visited -> return false to acc
        }

        visited.add(index)

        val (name, argument) = instructions[index++]
        when (name) {
            "acc" -> acc += argument
            "jmp" -> index += argument - 1
        }
    }
}

fun main() {
    val instructions = mapLines {
        val (name, argument) = it.split(' ')
        name to argument.toInt()
    }

    val first = launch(instructions).second
    var second = instructions.indices
        .asSequence()
        .filter { instructions[it].first != "acc" }
        .mapNotNull { i ->
            var pair = instructions[i]
            instructions[i] = (if (pair.first == "jmp") "nop" else "jmp") to pair.second
            launch(instructions).also { instructions[i] = pair }
        }
        .filter { it.first }
        .map { it.second }
        .firstOrNull()

    println("$first $second")
}
