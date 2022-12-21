private sealed interface MonkeyNumber {
    val number: Long
}

private data class MonkeyLiteral(override val number: Long) : MonkeyNumber

private class MonkeyOperation(
    val lhs: String,
    val rhs: String,
    val operationString: String,
    private val monkeys: Map<String, MonkeyNumber>,
) : MonkeyNumber {

    val operation = operationString.toOperation()

    override val number: Long
        get() = operation(monkeys[lhs]!!.number, monkeys[rhs]!!.number)
}

private fun find(monkeys: Map<String, MonkeyNumber>, current: String, number: Long): Long? {
    if (current == "humn") return number

    return when (val currentMonkey = monkeys[current]!!) {
        is MonkeyLiteral -> null
        is MonkeyOperation -> {
            val lhs = monkeys[currentMonkey.lhs]!!.number
            val rhs = monkeys[currentMonkey.rhs]!!.number

            when (currentMonkey.operationString) {
                "+" -> find(monkeys, currentMonkey.lhs, number - rhs)
                    ?: find(monkeys, currentMonkey.rhs, number - lhs)
                "-" -> find(monkeys, currentMonkey.lhs, number + rhs)
                    ?: find(monkeys, currentMonkey.rhs, lhs - number)
                "*" -> find(monkeys, currentMonkey.lhs, number / rhs)
                    ?: find(monkeys, currentMonkey.rhs, number / lhs)
                "/" -> find(monkeys, currentMonkey.lhs, number * rhs)
                    ?: find(monkeys, currentMonkey.rhs, lhs / number)
                else -> expect()
            }
        }
    }
}

private fun part1(monkeys: Map<String, MonkeyNumber>): Long = monkeys["root"]!!.number

private fun part2(monkeys: Map<String, MonkeyNumber>): Long {
    val root = monkeys["root"]!! as MonkeyOperation
    val lhs = monkeys[root.lhs]!!.number
    val rhs = monkeys[root.rhs]!!.number
    return find(monkeys, root.lhs, rhs) ?: find(monkeys, root.rhs, lhs)!!
}

fun main() {
    val monkeys = mapLines { it }.fold(mutableMapOf<String, MonkeyNumber>()) { monkeys, line ->
        val (name, other) = line.split(": ")

        monkeys[name] = if (line.any { it.isDigit() }) {
            MonkeyLiteral(other.toLong())
        } else {
            val (lhs, operationString, rhs) = other.split(' ')
            MonkeyOperation(lhs, rhs, operationString, monkeys)
        }

        monkeys
    }

    println(part1(monkeys))
    println(part2(monkeys))
}
