private typealias Display = List<Set<Char>>

private fun deduce(train: Display): Map<Set<Char>, Int> {

    fun find(f: (Set<Char>) -> Boolean) = train.find(f).expect()

    val one = find { it.size == 2 }
    val seven = find { it.size == 3 }
    val four = find { it.size == 4 }

    val transitions = mutableMapOf(
        one to 1,
        seven to 7,
        four to 4,
        find { it.size == 7 } to 8,
    )

    transitions[find { it.size == 5 && it.containsAll(one intersect seven) }] = 3
    transitions[find { it.size == 5 && it.containsAll(four subtract  seven) }] = 5
    transitions[find { it.size == 5 && it !in transitions }] = 2
    transitions[find { it.size == 6 && !it.containsAll(one) }] = 6
    transitions[find { it.size == 6 && !it.containsAll(four subtract one) }] = 0
    transitions[find { it !in transitions }] = 9

    return transitions
}

private fun findDisplaySum(displays: List<Pair<Display, Display>>, summing: Sequence<Int>.() -> Int) =
    displays.sumOf { (train, test) ->
        val transitions = deduce(train)
        test.asSequence()
            .map { transitions[it].expect() }
            .summing()
    }

private fun part1(displays: List<Pair<Display, Display>>) =
    findDisplaySum(displays) { count { it in listOf(1, 4, 7, 8) } }

private fun part2(displays: List<Pair<Display, Display>>) =
    findDisplaySum(displays) { joinToString("").toInt() }

fun main() {
    val displays = mapLines { line ->
        val (deduce, test) = line.splitToSequence(" | ")
            .map { displays -> displays.splitToSequence(' ').map { it.toSet() }.toList() }
            .toList()
        deduce to test
    }

    println(part1(displays))
    println(part2(displays))
}
