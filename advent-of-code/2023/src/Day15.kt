import java.util.LinkedList

private fun String.hash(): Int = fold(0) { acc, c -> (acc + c.code) * 17 % 256 }

fun main() {
    val parts = getFullInput().split(',')

    val first = parts.sumOf { it.hash() }

    val boxes = Array(256) { LinkedList<Pair<String, Int>>() }
    parts.forEach { instruction ->
        val newLens = '=' in instruction
        val labelString = instruction.substringBefore(if (newLens) '=' else '-')
        val box = boxes[labelString.hash()]

        if (!newLens) {
            box.removeIf { it.first == labelString }
            return@forEach
        }

        val focalLength = instruction.substringAfter('=').toInt()
        val lens = labelString to focalLength
        val index = box.indexOfFirst { it.first == labelString }

        if (index == -1) {
            box += lens
        } else {
            box[index] = lens
        }
    }

    val second = boxes.sumOfIndexed { i, box ->
        box.sumOfIndexed { j, lens -> (i + 1) * (j + 1) * lens.second }
    }

    println("$first $second")
}
