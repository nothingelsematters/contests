private fun findUseDifference(templateString: String, transitions: Map<Pair<Char, Char>, Char>, steps: Int): Long {
    var current = templateString
        .asSequence()
        .zipWithNext()
        .groupBy { it }
        .asSequence()
        .map { it.key to it.value.size.toLong() }
        .toMap()
        .toMutableMap()

    repeat(steps) {
        val newCurrent = current.toMutableMap()

        for ((pair, number) in current) {
            val newChar = transitions[pair] ?: continue
            val (a, b) = pair
            newCurrent.merge(a to newChar, number, Long::plus)
            newCurrent.merge(newChar to b, number, Long::plus)

            val previous = newCurrent[pair].expect()
            if (previous == number) newCurrent.remove(pair) else newCurrent[pair] = previous - number
        }

        current = newCurrent
    }

    val appearances = current
        .asSequence()
        .flatMap {
            val (a, b) = it.key
            sequenceOf(a to it.value, b to it.value)
        }
        .groupBy({ it.first }) { it.second}
        .map { it.key to it.value.sum() / 2 }
        .toMap()
        .toMutableMap()
    appearances.merge(templateString.first(), 1, Long::plus)
    appearances.merge(templateString.last(), 1, Long::plus)

    return appearances.maxOf { it.value } - appearances.minOf { it.value }
}

fun main() {
    val (template, transitions) = mapBlocks { it }.let { (templateList, transitionStrings) ->
        templateList.single() to transitionStrings.associate {
            val (a, b) = it.split(" -> ").toPair()
            a.toList().toPair() to b.single()
        }
    }

    println(findUseDifference(template, transitions, 10))
    println(findUseDifference(template, transitions, 40))
}
