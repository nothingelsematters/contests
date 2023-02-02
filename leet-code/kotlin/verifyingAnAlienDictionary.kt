fun isAlienSorted(words: Array<String>, order: String): Boolean {
    val charToOrder = order.asSequence().withIndex().map { it.value to it.index }.toMap()

    return words.asSequence().windowed(2).all { (l, r) ->
        l.asSequence()
            .zip(r.asSequence())
            .map { (a, b) -> charToOrder[a]!! to charToOrder[b]!! }
            .find { (a, b) -> a != b }
            ?.let { (a, b) -> a < b }
            ?: (l.length <= r.length)
    }
}
