sealed class DfaNode {

    abstract fun match(string: String): List<String>

    protected fun match(strings: List<String>): List<String> = strings.asSequence().flatMap(::match).distinct().toList()

    protected fun List<DfaNode>.match(string: String) = fold(listOf(string)) { acc, node -> node.match(acc) }
}

data class Leaf(val char: Char) : DfaNode() {

    override fun match(string: String): List<String> =
        if (!string.startsWith(char)) emptyList() else listOf(string.drop(1))
}

data class Node(val rules: MutableList<List<DfaNode>> = mutableListOf()) : DfaNode() {

    override fun match(string: String): List<String> = rules.flatMap { it.match(string) }
}

fun dfa(rules: Map<Int, String>, current: Int = 0, nodes: MutableMap<Int, DfaNode> = mutableMapOf()): DfaNode {
    var node = nodes[current]
    if (node != null) {
        return node
    }
    val rule = rules[current]!!

    if ('"' in rule) {
        node = Leaf(rule.replace("\"", "").singleOrNull().expect())
        nodes[current] = node
        return node
    }

    node = Node()
    nodes[current] = node
    node.rules.addAll(rule.split(" | ").map { it.split(' ').map { dfa(rules, it.toInt(), nodes) } })
    return node
}

fun countValid(rules: Map<Int, String>, messages: List<String>): Int {
    val root = dfa(rules)
    return messages.count { root.match(it).contains("") }
}

fun main() {
    val (ruleStrings, messages) = readBlocks().map { it.split('\n') }
    var rules = ruleStrings.map {
            val (number, restrictions) = it.split(": ")
            number.toInt() to restrictions
        }
        .toMap()

    val first = countValid(rules, messages)
    val second = countValid(rules + mapOf(8 to "42 | 42 8", 11 to "42 31 | 42 11 31"), messages)
    println("$first $second")
}
