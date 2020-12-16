fun List<Int>.getViolator(rules: List<Pair<String, (Int) -> Boolean>>): Int? =
    find { number -> rules.none { it.second(number) } }

fun String.splitInts(): List<Int> = splitToSequence(',').map { it.toInt() }.toList()

fun <K, V> Sequence<Pair<K, V>>.group(): MutableMap<K, MutableSet<V>> = groupBy({ it.first }) { it.second }
    .mapValues { it.value.toMutableSet() }
    .toMutableMap()

fun main() {
    val (ruleStrings, myString, otherStrings) = System.`in`.bufferedReader()
        .readText()
        .splitToSequence("\n\n")
        .map { it.split('\n') }
        .toList()

    val mine = myString[1].splitInts()
    val others = otherStrings.drop(1).dropLast(1).map { it.splitInts() }
    val rules = ruleStrings.map {
        val (name, value) = it.split(": ")
        val ranges = value.splitToSequence(" or ")
            .map {
                val (from, to) = it.split('-').map { it.toInt() }
                from..to
            }
            .toList()
        name to { n: Int -> ranges.any { n in it } }
    }

    val first = others.sumBy { it.getViolator(rules) ?: 0 }

    val valid = others.filter { it.getViolator(rules) == null }

    val indexMap = mine.asSequence()
        .flatMapIndexed { index, number -> rules.asSequence().filter { it.second(number) }.map { it.first to index } }
        .group()

    for (ticket in valid) {
        for ((index, number) in ticket.withIndex()) {
            for ((name, range) in rules) {
                val indexSet = indexMap[name] ?: continue
                if (index !in indexSet) continue
                if (!range(number)) indexSet.remove(index)
            }
        }
    }

    val reverseIndexMap = indexMap.asSequence().flatMap { (key, value) -> value.asSequence().map { it to key } }.group()

    while (reverseIndexMap.any { it.value.size != 1 }) {
        for ((index, names) in reverseIndexMap) {
            val name = names.singleOrNull() ?: continue
            val indices = indexMap[name]!!
            indices.remove(index)
            indices.forEach { reverseIndexMap[it]!!.remove(name) }
        }
    }

    val second = reverseIndexMap.asSequence()
        .map { (key, value) -> value.singleOrNull()!! to key }
        .filter { it.first.startsWith("departure") }
        .map { mine[it.second].toLong() }
        .reduce(Long::times)

    println("$first $second")
}
