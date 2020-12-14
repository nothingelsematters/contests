sealed class WriteInstruction
data class WriteMask(val bits: List<Boolean?>) : WriteInstruction()
data class WriteMemory(val index: Long, val value: Long) : WriteInstruction()

fun Long.with(pos: Int, bit: Boolean) = if (bit) or(1L shl pos) else and(Long.MAX_VALUE - (1L shl pos))

fun processProgram(
    instructions: List<WriteInstruction>,
    processWriteMemory: (instruction: WriteMemory, mask: List<Boolean?>, memory: MutableMap<Long, Long>) -> Unit
): Long {
    var mask = listOf<Boolean?>()
    val memory = mutableMapOf<Long, Long>()

    for (instruction in instructions) {
        when (instruction) {
            is WriteMask -> mask = instruction.bits
            is WriteMemory -> processWriteMemory(instruction, mask, memory)
        }
    }

    return memory.values.sum()
}

fun main() {
    val instructions = mapLines {
        val splitted = it.split(" ")
        if (splitted[0] != "mask") {
            return@mapLines WriteMemory(splitted[0].drop(4).dropLast(1).toLong(), splitted[2].toLong())
        }

        val bits = splitted[2].reversed().map {
            when (it) {
                '0' -> false
                '1' -> true
                else -> null
            }
        }
        WriteMask(bits)
    }

    val first = processProgram(instructions) { instruction, mask, memory ->
        memory[instruction.index] = mask.asSequence()
            .withIndex()
            .mapNotNull { it }
            .fold(instruction.value) { acc, (pos, bit) -> if (bit == null) acc else acc.with(pos, bit) }
    }

    val second = processProgram(instructions) { instruction, mask, memory ->
        var index = mask.asSequence()
            .withIndex()
            .filter { it.value == true }
            .fold(instruction.index) { acc, (index, _) -> acc.with(index, true) }
        val floating = mask.asSequence().withIndex().filter { it.value == null }.map { it.index }.toList()

        for (i in 0 until (1L shl floating.size)) {
            val ix = (0 until floating.size).fold(index) { acc, j -> acc.with(floating[j], (i shr j) and 1L == 1L) }
            memory[ix] = instruction.value
        }
    }

    println("$first $second")
}
