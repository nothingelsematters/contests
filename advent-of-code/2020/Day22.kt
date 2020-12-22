fun recursiveCombat(decks: List<MutableList<Int>>, recursionSafety: MutableSet<List<List<Int>>> = mutableSetOf()): Int {
    if (decks.any { it.isEmpty() }) return decks.indexOfFirst { it.isNotEmpty() }

    return combat(decks) { currentDeck, firsts ->
        val copy = currentDeck.map { it.toList() }

        if (copy in recursionSafety) return@combat null

        recursionSafety.add(copy)

        if (currentDeck.zip(firsts).all { (d, f) -> d.size >= f }) {
            recursiveCombat(currentDeck.zip(firsts).map { (d, f) -> d.take(f).toMutableList() })
        } else {
            firsts.asSequence().withIndex().maxByOrNull { it.value }!!.index
        }
    }
}

fun combat(decks: List<MutableList<Int>>, winning: (decks: List<MutableList<Int>>, firsts: List<Int>) -> Int?): Int {
    while (decks.none { it.isEmpty() }) {
        var firsts = decks.map { it.removeFirst() }
        val winner = winning(decks, firsts)

        if (winner == null) break

        firsts = firsts.drop(winner) + firsts.take(winner)
        decks[winner].addAll(firsts)
    }

    return decks.indexOfFirst { it.isNotEmpty() }.let { if (it == -1) 0 else it }
}

fun combat(decks: List<List<Int>>, winner: (decks: List<MutableList<Int>>) -> Int): Int {
    val copyDeck = decks.map { it.toMutableList() }
    return copyDeck[winner(copyDeck)].reversed().mapIndexed { i, it -> it * (i + 1) }.sum()
}

fun main() {
    val decks = readBlocks().map {
        it.splitToSequence('\n').drop(1).filter { it.isNotEmpty() }.map { it.toInt() }.toList()
    }

    val first = combat(decks) { combat(it) { _, d -> d.asSequence().withIndex().maxByOrNull { it.value }!!.index } }
    val second = combat(decks, ::recursiveCombat)
    println("$first $second")
}
