private const val INPUT_SIZE = 14

private sealed class Argument {

    abstract fun get(array: Array<Int>): Int

    companion object {
        fun fromString(string: String) =
            if (string.singleOrNull() in 'w'..'z') Variable(string.single().code - 'w'.code)
            else Literal(string.toInt())
    }
}

private data class Literal(val number: Int) : Argument() {
    override fun get(array: Array<Int>) = number
}

private data class Variable(val index: Int) : Argument() {
    override fun get(array: Array<Int>) = array[index]
}

private sealed class Instruction(val action: (Int, Int) -> Int) {

    abstract val firstArgument: Variable
    abstract val secondArgument: Argument

    fun apply(array: Array<Int>): Array<Int> {
        array[firstArgument.index] = action(firstArgument.get(array), secondArgument.get(array))
        return array
    }

    companion object {
        fun fromString(string: String): Instruction {
            val (action, variable, argument) = string.split(' ')
            val instruction: (Variable, Argument) -> Instruction = when (action) {
                "add" -> ::Add
                "mul" -> ::Mul
                "div" -> ::Div
                "mod" -> ::Mod
                "eql" -> ::Eql
                else -> error("Invalid instruction: $string")
            }
            return instruction(Argument.fromString(variable) as Variable, Argument.fromString(argument))
        }
    }
}

private data class Add(override val firstArgument: Variable, override val secondArgument: Argument) :
    Instruction(Int::plus)

private data class Mul(override val firstArgument: Variable, override val secondArgument: Argument) :
    Instruction(Int::times)

private data class Div(override val firstArgument: Variable, override val secondArgument: Argument) :
    Instruction(Int::div)

private data class Mod(override val firstArgument: Variable, override val secondArgument: Argument) :
    Instruction(Int::mod)

private data class Eql(override val firstArgument: Variable, override val secondArgument: Argument) :
    Instruction({ a, b -> (a == b).toInt() })

private fun depthFirstSearch(
    instructions: List<List<Instruction>>,
    numbers: IntProgression,
    index: Int = 0,
    array: Array<Int> = Array(4) { 0 },
    visited: MutableSet<Pair<Int, Int>> = mutableSetOf(),
): Long? {
    when {
        index == INPUT_SIZE && array[3] == 0 -> return 0
        index == INPUT_SIZE || array[3] to index in visited -> return null
    }

    visited += array[3] to index
    val instructionIteration = instructions[index]

    return numbers.firstNotNullOfOrNull { number ->
        val nextVector = instructionIteration
            .fold(array.copyOf().also { it[0] = number }) { acc, value -> value.apply(acc) }

        val trailingDigits = depthFirstSearch(instructions, numbers, index + 1, nextVector, visited)
            ?: return@firstNotNullOfOrNull null

        trailingDigits + number * 10L.pow(INPUT_SIZE - 1 - index)
    }
}

private fun part1(instructions: List<List<Instruction>>) = depthFirstSearch(instructions, (1..9).reversed())

private fun part2(instructions: List<List<Instruction>>) = depthFirstSearch(instructions, 1..9)

fun main() {
    val instructions: List<List<Instruction>> = lines().let { lines ->
        var currentLines = lines.toMutableList()
        buildList {
            while (currentLines.isNotEmpty()) {
                currentLines.removeFirst()
                val firstIndex = currentLines.indexOfFirst { it.startsWith("inp") }
                val index = if (firstIndex == -1) currentLines.size else firstIndex
                add(currentLines.subList(0, index).map { Instruction.fromString(it) })
                currentLines = currentLines.subList(index, currentLines.size)
            }
        }
    }

    // runs for 13s
    println(part1(instructions))

    // runs for 2m 16s
    println(part2(instructions))
}
