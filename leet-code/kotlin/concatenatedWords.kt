data class Automaton(
    var isTerminal: Boolean = false,
    val transitions: MutableMap<Char, Automaton> = mutableMapOf(),
)

fun findAllConcatenatedWordsInADict(words: Array<String>, words2: Array<String> = words): List<String> {
    val root = Automaton()
    words.forEach { updateAutomaton(root, it) }
    return words2.filter { depthFirstSearch(it, root).let { it != null && it >= 2 } }
}

private tailrec fun updateAutomaton(node: Automaton, string: String) {
    if (string.isEmpty()) {
        node.isTerminal = true
        return
    }

    updateAutomaton(
        node.transitions.getOrPut(string[0]) { Automaton() },
        string.substring(1),
    )
}

private fun depthFirstSearch(string: String, root: Automaton, node: Automaton = root): Int? {
    if (string.isEmpty()) return if (node.isTerminal) 1 else null

    val terminal = if (!node.isTerminal) null else depthFirstSearch(string, root)?.let { it + 1 }
    val nonTerminal = node.transitions[string[0]]?.let { depthFirstSearch(string.substring(1), root, it) }
    return sequenceOf(terminal, nonTerminal).filterNotNull().maxOrNull()
}
